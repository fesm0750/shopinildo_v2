package br.edu.postech.shopinildo.estoque;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record EstoqueDTO(
    @NotNull @Min(value = 0, message = "Quantidade deve ser maior ou igual a zero") Integer quantity) {
}

