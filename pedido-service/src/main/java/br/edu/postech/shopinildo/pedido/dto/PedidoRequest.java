package br.edu.postech.shopinildo.pedido.dto;

import java.math.BigDecimal;

import br.edu.postech.shopinildo.pedido.model.PaymentDetails;
import br.edu.postech.shopinildo.pedido.model.ShippingDetails;

public record PedidoRequest(
        String userId,
        BigDecimal total,
        ShippingDetails shipping,
        PaymentDetails payment
) {}