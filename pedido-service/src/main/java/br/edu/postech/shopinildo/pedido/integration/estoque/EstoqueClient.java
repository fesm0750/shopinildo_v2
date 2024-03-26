package br.edu.postech.shopinildo.pedido.integration.estoque;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "estoque-service", url = "${app.estoque-service.url}")
public interface EstoqueClient {
    @PostMapping("/estoque/api/update/order")
    public ResponseEntity<Void> updateEstoqueFromOrder(@RequestBody List<ItemQuantityDTO> order);
}
