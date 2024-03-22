package br.edu.postech.shopinildo.estoque.response;

import br.edu.postech.shopinildo.estoque.Estoque;

public record ItemAvailableDTO(String itemId, boolean isAvailable) {
    public ItemAvailableDTO(Estoque estoque) {
        this(estoque.getItemId(), estoque.getQuantity() > 0);
    }
}
