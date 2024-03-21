package br.edu.postech.shopinildo.estoque;

import jakarta.validation.constraints.Min;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "estoque")
public class Estoque {

    @Id
    private String itemId;

    @Min(value = 0, message = "Estoque deve ser maior ou igual a zero")
    private Integer quantity;

    public Estoque() {}

    public Estoque(String itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


