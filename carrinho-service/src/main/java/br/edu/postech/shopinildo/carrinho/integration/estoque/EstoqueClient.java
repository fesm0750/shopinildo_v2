package br.edu.postech.shopinildo.carrinho.integration.estoque;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "estoque-service", url = "${app.estoque-service.url}")
public interface EstoqueClient {

    @GetMapping("estoque/availability")
    EstoqueAvailableResponse available(@RequestParam List<String> itemIds);
}
