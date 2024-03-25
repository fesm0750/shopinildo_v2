package br.edu.postech.shopinildo.pedido.dto;

import br.edu.postech.shopinildo.pedido.model.Pedido;
import br.edu.postech.shopinildo.pedido.PedidoStatus;
import br.edu.postech.shopinildo.pedido.model.PaymentReceipt;

public record PedidoResponse(
        String id,
        PedidoStatus status,
        PaymentReceipt receipt
) {
    public PedidoResponse(Pedido pedido) {
        this(pedido.getId(), pedido.getStatus(), pedido.getReceipt());
    }
}
