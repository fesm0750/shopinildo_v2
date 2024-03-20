package br.edu.postech.shopinildo.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "itens")
public class Item {

    @Id
    private String id;

    @NotBlank(message = "O nome do item não pode ser vazio")
    private String name;

    private String description;

    @NotNull
    @DecimalMin(value = "0.01", message = "O preço do item deve ser maior que R$ 0,01")
    @DecimalMax(value = "1000000.00", message = "O preço do item deve ser menor que R$ 1.000.000,00")
    @Digits(integer = 6, fraction = 2, message = "O preço não deve ter mais de duas casas para centavos")
    private BigDecimal price;

    public Item() {}

    public Item(String id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
