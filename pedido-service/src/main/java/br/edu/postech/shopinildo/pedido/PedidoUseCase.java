package br.edu.postech.shopinildo.pedido;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import br.edu.postech.shopinildo.pedido.dto.PedidoRequest;
import br.edu.postech.shopinildo.pedido.dto.PedidoResponse;
import br.edu.postech.shopinildo.pedido.integration.carrinho.CarrinhoClient;
import br.edu.postech.shopinildo.pedido.integration.carrinho.CarrinhoResponse;
import br.edu.postech.shopinildo.pedido.integration.estoque.EstoqueClient;
import br.edu.postech.shopinildo.pedido.integration.estoque.ItemQuantityDTO;
import br.edu.postech.shopinildo.pedido.integration.pagamento.PagamentoClient;
import br.edu.postech.shopinildo.pedido.model.PaymentReceipt;
import br.edu.postech.shopinildo.pedido.model.Pedido;

public class PedidoUseCase {
    
    // process order
    public static PedidoResponse placeOrder(PedidoRequest pedidoRequest, CarrinhoClient carrinhoClient, EstoqueClient estoqueClient, PagamentoClient pagamentoClient, PedidoRepository pedidoRepository) {
        // get items from carrinho
        CarrinhoResponse order = carrinhoClient.getCarrinho(pedidoRequest.userId());
        if (order == null || order.total != pedidoRequest.total())
        {
            throw new RuntimeException("Cart is stale");
        }

        // update estoque
        List<ItemQuantityDTO> itemQuantities = order.items.stream().map(it -> new ItemQuantityDTO(it.itemId(), it.quantity())).toList();
        ResponseEntity<Void> estoqueResponse = estoqueClient.updateEstoqueFromOrder(itemQuantities);
        if (estoqueResponse == null ||estoqueResponse.getStatusCode().isError())
        {
            throw new RuntimeException("Failed to update stock");
            
        }

        // save order
        Pedido savedOrder = pedidoRepository.save(new Pedido(pedidoRequest, order.items));

        // clean cart
        carrinhoClient.clearCarrinho(pedidoRequest.userId());

        // contact payment system
        ResponseEntity<String> paymentResponse = pagamentoClient.pay(savedOrder.getPayment());
        if (paymentResponse == null || paymentResponse.getStatusCode().isError())
        {
            savedOrder.setStatus(PedidoStatus.REJECTED);
            pedidoRepository.save(savedOrder);
            throw new RuntimeException("Payment failed");
            
        }

        savedOrder.setReceipt(new PaymentReceipt(UUID.fromString(paymentResponse.getBody())));
        savedOrder.setStatus(PedidoStatus.APPROVED);
        savedOrder = pedidoRepository.save(savedOrder);
        return new PedidoResponse(savedOrder);
    }
}
