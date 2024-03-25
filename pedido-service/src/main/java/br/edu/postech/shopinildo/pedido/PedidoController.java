package br.edu.postech.shopinildo.pedido;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.postech.shopinildo.pedido.dto.PedidoRequest;
import br.edu.postech.shopinildo.pedido.dto.PedidoResponse;
import br.edu.postech.shopinildo.pedido.integration.carrinho.CarrinhoClient;
import br.edu.postech.shopinildo.pedido.integration.estoque.EstoqueClient;
import br.edu.postech.shopinildo.pedido.integration.pagamento.PagamentoClient;
import br.edu.postech.shopinildo.pedido.model.Pedido;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CarrinhoClient carrinhoClient;

    @Autowired
    private EstoqueClient estoqueClient;

    @Autowired
    private PagamentoClient paymentClient;


    @GetMapping
    public Page<Pedido> findAll(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    @GetMapping("/user/{userId}")
    public Page<Pedido> findByUserId(@PathVariable String userId, Pageable pageable) {
        return pedidoRepository.findByUserId(userId, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable String id) {
        return pedidoRepository.findById(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> create(@RequestBody PedidoRequest pedido) {
        var response = PedidoUseCase.placeOrder(pedido, carrinhoClient, estoqueClient, paymentClient, pedidoRepository);
        return ResponseEntity.ok(response);
    }
}


