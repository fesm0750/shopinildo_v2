package br.edu.postech.shopinildo.carrinho.integration.estoque;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "estoque-service", url = "${app.estoque-service.url}")
public interface EstoqueClient {

    @GetMapping("estoque/availability")
    ResponseEntity<EstoqueAvailableResponse> available(@RequestParam List<String> itemIds);

    @PostMapping("estoque/availability/order")
    ResponseEntity<EstoqueAvailableResponse> availableByOrderResponse(@RequestBody List<ItemQuantityDTO> items);
}
