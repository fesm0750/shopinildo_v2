package br.edu.postech.shopinildo.pedido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.postech.shopinildo.pedido.model.Pedido;

public interface PedidoRepository extends MongoRepository<Pedido, String> {

    Page<Pedido> findByUserId(String userId, Pageable pageable);

}


