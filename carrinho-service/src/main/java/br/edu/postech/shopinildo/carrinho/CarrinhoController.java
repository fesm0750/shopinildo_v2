package br.edu.postech.shopinildo.carrinho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.postech.shopinildo.carrinho.dto.CarrinhoResponse;

@RestController
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping("/{userId}")
    public CarrinhoResponse getCarrinhoByUserId(@PathVariable String userId) {
        return carrinhoService.getCarrinhoByUserId(userId);
    }

    @PostMapping("/{userId}/{itemId}")
    public ResponseEntity<Void> addItemIntoCarrinho(@PathVariable String userId,
                                                 @PathVariable String itemId,
                                                 @RequestParam Integer quantity) {
        carrinhoService.putItemInCarrinho(userId, itemId, quantity);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/{itemId}")
    public ResponseEntity<Void> deleteItemInCarrinho(@PathVariable String userId,
                                                     @PathVariable String itemId) {
        carrinhoService.deleteItemInCarrinho(userId, itemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> clearCarrinho(@PathVariable String userId) {
        carrinhoService.clearCarrinho(userId);
        return ResponseEntity.noContent().build();
    }
}
