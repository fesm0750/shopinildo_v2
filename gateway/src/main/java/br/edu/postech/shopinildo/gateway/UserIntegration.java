package br.edu.postech.shopinildo.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "${app.usuario-service.url}")
public interface UserIntegration {
    @PostMapping(value = "/users/api/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    UserResponse login(@RequestBody LoginRequest loginRequest);
}
