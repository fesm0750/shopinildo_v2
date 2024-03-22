package br.edu.postech.shopinildo.carrinho.dto;

import java.math.BigDecimal;

import br.edu.postech.shopinildo.carrinho.integration.estoque.ItemAvailableDTO;
import br.edu.postech.shopinildo.carrinho.integration.item.ItemResponse;

public record ItemDTO(
        String itemId,
        String name,
        String description,
        int quantity,
        boolean isAvailable,
        BigDecimal price) {

    public ItemDTO(String itemId, int quantity, ItemAvailableDTO estoqueResponse, ItemResponse itemResponse) {
        this(itemId, itemResponse.name(), itemResponse.description(), quantity, estoqueResponse.isAvailable(), itemResponse.price());
    }
}
