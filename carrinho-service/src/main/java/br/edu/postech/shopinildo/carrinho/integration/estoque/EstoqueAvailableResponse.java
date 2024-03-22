package br.edu.postech.shopinildo.carrinho.integration.estoque;

import java.util.List;

public record EstoqueAvailableResponse(List<ItemAvailableDTO> availability) {
    
}