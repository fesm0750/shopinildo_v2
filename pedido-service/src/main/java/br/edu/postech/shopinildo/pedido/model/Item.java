package br.edu.postech.shopinildo.pedido.model;

public record Item(
        String itemId,
        String name,
        double price,
        int quantity) {
}
