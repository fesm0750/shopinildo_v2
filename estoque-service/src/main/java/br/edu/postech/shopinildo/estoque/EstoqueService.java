package br.edu.postech.shopinildo.estoque;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.edu.postech.shopinildo.estoque.request.EstoqueRequest;
import br.edu.postech.shopinildo.estoque.response.AvailabilityResponse;
import br.edu.postech.shopinildo.estoque.response.ItemAvailableDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@Service
@Validated
public class EstoqueService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Validator validator;

    public List<Estoque> findAll(Pageable pageable) {
        Query query = new Query().with(pageable);
        return mongoTemplate.find(query, Estoque.class);
    }

    public Estoque findByItemId(String itemId) {
        Query query = new Query(Criteria.where("itemId").is(itemId));
        Estoque estoque = mongoTemplate.findOne(query, Estoque.class);
        if (estoque == null) {
            throw new NoSuchElementException(String.format("Estoque não encontrado para o itemId %s", itemId));
        }
        return estoque;
    }

    public AvailabilityResponse checkInventory(List<String> itemIds) {
        List<ItemAvailableDTO> list = estoques.stream().map(estoque -> new ItemAvailableDTO(estoque)).toList();
        return new AvailabilityResponse(list);
    }

    public Estoque save(String itemId, EstoqueRequest dto) {
        return mongoTemplate.save(new Estoque(itemId, dto.quantity()));
    }

    public Estoque increment(String itemId, int delta) {
        Query query = new Query(Criteria.where("itemId").is(itemId));
        Estoque estoque = mongoTemplate.findOne(query, Estoque.class);
        if (estoque == null) {
            throw new NoSuchElementException(String.format("Estoque não encontrado para o itemId %s", itemId));
        }
        estoque.setQuantity(estoque.getQuantity() + delta);
        validateEstoque(estoque);
        return mongoTemplate.save(estoque);
    }

    public Estoque increase(String itemId, EstoqueRequest dto) {
        return increment(itemId, dto.quantity());
    }

    public Estoque decrease(String itemId, EstoqueRequest dto) {
        return increment(itemId, -dto.quantity());
    }

    public void delete(String itemId) {
        Query query = new Query(Criteria.where("itemId").is(itemId));
        mongoTemplate.remove(query, Estoque.class);
    }

    //-----
    // Helper Methods
    //-----

    private void validateEstoque(Estoque estoque) {
        // Validate the Estoque object using the validator
        if (validator != null) {
            var violations = validator.validate(estoque);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException("Validation failed", violations);
            }
        }
    }


}
