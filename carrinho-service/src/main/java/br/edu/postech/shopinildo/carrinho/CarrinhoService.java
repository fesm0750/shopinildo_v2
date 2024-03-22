package br.edu.postech.shopinildo.carrinho;

import br.edu.postech.shopinildo.carrinho.dto.CarrinhoResponse;
import br.edu.postech.shopinildo.carrinho.integration.estoque.EstoqueClient;
import br.edu.postech.shopinildo.carrinho.integration.item.ItemClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CarrinhoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ItemClient itemClient;

    @Autowired
    private EstoqueClient estoqueClient;

    public CarrinhoResponse getCarrinhoByUserId(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        var carrinho = mongoTemplate.findOne(query, Carrinho.class);
        if (carrinho == null) {
            carrinho = new Carrinho(userId);
        }
        return CarrinhoUseCase.returnCompleteCart(carrinho, itemClient, estoqueClient);
    }

    // cria um carrinho se o mesmo não existir para um user válido
    // TODO: verificar comportamento se o usuário não existir: criar mesmo assim ou retornar erro?
    // TODO: validar se usuario existe
    public void putItemInCarrinho(String userId, String itemId, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que 0");
        }

        Query query = new Query(Criteria.where("userId").is(userId));
        var carrinho = mongoTemplate.findOne(query, Carrinho.class);
        if (carrinho == null) {
            carrinho = new Carrinho(userId);
        } 

        carrinho.setItemQuantity(itemId, quantity);
        mongoTemplate.save(carrinho);
    }

    public void deleteItemInCarrinho(String userId, String itemId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        Update update = new Update();
        update.unset("items." + itemId);
        mongoTemplate.updateFirst(query, update, Carrinho.class);
    }

    public void clearCarrinho(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        Update update = new Update();
        update.set("items", new HashMap<>());
        mongoTemplate.updateFirst(query, update, Carrinho.class);
    }
}

