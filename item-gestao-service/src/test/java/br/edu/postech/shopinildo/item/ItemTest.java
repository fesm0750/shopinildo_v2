package br.edu.postech.shopinildo.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ItemTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testItemValid() {
        Item item = new Item("1", "teste", "descrição", new BigDecimal("20.5"));
        assertEquals("1", item.getId());
        assertEquals("teste", item.getName());
        assertEquals("descrição", item.getDescription());
        assertEquals(new BigDecimal("20.5"), item.getPrice());
    }

    //-----
    // Name constraints
    //-----
    @Test
    void itemNameCannotBeNull_JakartaConstraint() {
        var item = new Item("1", null, null, new BigDecimal("20.5"));
        
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        
        assertTrue(violations.size() > 0, "Validation should fail for blank name");
    }

    @Test
    void itemNameCannotBeBlank_JakartaConstraint() {
        var item = new Item("1", " ", null, new BigDecimal("20.5"));
        
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        
        assertTrue(violations.size() > 0, "Validation should fail for blank name");
    }

    //-----
    // Price constraints
    //-----
    @Test
    void itemPriceCannotBeNull_JakartaConstraint() {
        var item = new Item("1", "teste", "descrição", null);
        
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        
        assertTrue(violations.size() > 0, "Validation should fail for null price");
    }

    @Test
    void itemPriceCannotBeNegative_JakartaConstraint() {
        var item = new Item("1", "teste", "descrição", new BigDecimal(-20.5));
        
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        
        assertTrue(violations.size() > 0, "Validation should fail for zero or negative price");
    }

    @Test
    void itemPriceNoMoreThan2DecimalPlaces_JakartaConstraint() {
        var item = new Item("1", "teste", "descrição", new BigDecimal("20.567"));
        
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        
        assertTrue(violations.size() > 0, "Validation should fail for more than 2 decimal places price");
    }
}
