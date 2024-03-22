package br.edu.postech.shopinildo.estoque.response;

import java.util.List;

public record AvailabilityResponse(List<ItemAvailableDTO> availability) {
    
}
