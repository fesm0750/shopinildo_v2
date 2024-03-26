package br.edu.postech.shopinildo.carrinho.integration.item;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "item-gestao-service", url = "${app.item-service.url}")
public interface ItemClient {

    @GetMapping("/items/api/list")
    List<ItemResponse> findByIds(@RequestParam List<String> ids);
}
