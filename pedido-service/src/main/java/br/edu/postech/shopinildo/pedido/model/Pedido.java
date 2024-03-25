package br.edu.postech.shopinildo.pedido.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import br.edu.postech.shopinildo.pedido.PedidoStatus;
import br.edu.postech.shopinildo.pedido.dto.PedidoRequest;

@Document(collection = "pedidos")
public class Pedido {
    @Id
    private String id;

    @Indexed
    private String userId;

    private BigDecimal total;

    private PedidoStatus status;

    private ShippingDetails shipping;

    private PaymentDetails payment;

    private PaymentReceipt receipt;

    private List<Item> items = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(String userId, BigDecimal total, PedidoStatus status, ShippingDetails shipping, PaymentDetails payment, PaymentReceipt receipt, List<Item> items) {
        this.userId = userId;
        this.total = total;
        this.status = status;
        this.shipping = shipping;
        this.payment = payment;
        this.receipt = receipt;
        this.items = items;
    }

    public Pedido(PedidoRequest pedido, List<Item> items) {
        this.userId = pedido.userId();
        this.total = pedido.total();
        this.status = PedidoStatus.PENDING;
        this.shipping = pedido.shipping();
        this.payment = pedido.payment();
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    public ShippingDetails getShipping() {
        return shipping;
    }

    public void setShipping(ShippingDetails shipping) {
        this.shipping = shipping;
    }

    public PaymentDetails getPayment() {
        return payment;
    }

    public void setPayment(PaymentDetails payment) {
        this.payment = payment;
    }

    public PaymentReceipt getReceipt() {
        return receipt;
    }

    public void setReceipt(PaymentReceipt receipt) {
        this.receipt = receipt;
    }
}


