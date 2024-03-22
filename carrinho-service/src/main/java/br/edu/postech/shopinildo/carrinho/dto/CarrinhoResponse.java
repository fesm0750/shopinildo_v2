package br.edu.postech.shopinildo.carrinho.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoResponse {
    public BigDecimal total;
    public List<ItemDTO> items = new ArrayList<>();

    public CarrinhoResponse(List<ItemDTO> items) {
        this.items = items;
        this.total = items.stream()
                .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
