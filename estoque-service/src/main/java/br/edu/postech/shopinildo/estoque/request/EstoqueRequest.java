package br.edu.postech.shopinildo.estoque.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record EstoqueRequest(
    @NotNull @Min(value = 0, message = "Quantidade deve ser maior ou igual a zero") Integer quantity) {
}

