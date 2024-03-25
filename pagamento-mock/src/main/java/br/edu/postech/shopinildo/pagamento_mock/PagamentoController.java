package br.edu.postech.shopinildo.pagamento_mock;

import java.time.Duration;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class PagamentoController {

    @PostMapping
    public Mono<ResponseEntity<String>> pay(@RequestBody PagamentoDetalhes pagamentoDetalhes) {
        return Mono.just(UUID.randomUUID().toString())
                .delayElement(Duration.ofSeconds(5))
                .map(id -> ResponseEntity.ok(id));
    }    
}
