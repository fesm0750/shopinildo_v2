package br.edu.postech.shopinildo.estoque.request;

import jakarta.validation.constraints.Min;

public record ItemDTO(String itemId, @Min(value = 0, message = "Quantidade deve ser maior ou igual a zero") int quantity) {
    
}
