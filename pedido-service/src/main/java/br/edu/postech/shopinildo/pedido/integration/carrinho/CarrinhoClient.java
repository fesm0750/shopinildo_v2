package br.edu.postech.shopinildo.pedido.integration.carrinho;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "carrinho-service", url = "${app.carrinho-service.url}")
public interface CarrinhoClient {

    @GetMapping("/{userId}")
    public CarrinhoResponse getCarrinho(@PathVariable String userId);

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> clearCarrinho(@PathVariable String userId);
}