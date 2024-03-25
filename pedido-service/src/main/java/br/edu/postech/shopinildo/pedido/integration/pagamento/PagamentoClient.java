package br.edu.postech.shopinildo.pedido.integration.pagamento;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.edu.postech.shopinildo.pedido.model.PaymentDetails;

@FeignClient(name = "pagamento-mock", url = "${app.pagamento-mock.url}")
public interface PagamentoClient {
    @PostMapping
    public ResponseEntity<String> pay(@RequestBody PaymentDetails pagamentoDetalhes);
}
