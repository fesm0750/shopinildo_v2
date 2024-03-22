package br.edu.postech.shopinildo.carrinho;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "carrinhos")
public class Carrinho {

    @Id
    private String userId;

    // (itemId, quantity)
    @Field("items")
    private Map<String, Integer> items = new HashMap<String, Integer>();

    public Carrinho() {
    }

    public Carrinho(String userId) {
        this.userId = userId;
    }

    public Carrinho(String userId, HashMap<String, Integer> items) {
        this.userId = userId;
        this.items = items;
    }

    //-----
    // Getters and Setters
    //-----

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Integer> items) {
        this.items = items;
    }

    //-----
    // Helper Methods
    //-----

    public Integer getItemQuantity(String itemId) {
        return items.get(itemId);
    }

    public void setItemQuantity(String itemId, int quantity) {
        items.put(itemId, quantity);
    }

    public void removeItem(String itemId) {
        items.remove(itemId);
    }

}
