package br.edu.postech.shopinildo.carrinho.integration.item;

import java.math.BigDecimal;

public record ItemResponse(String id, String name, String description, BigDecimal price) {
}
