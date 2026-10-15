package com.orderflow.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ProductTest {

    @Test
    public void testProduct() {
        Product product = new Product();
        product.name = "Product name";
        product.price = new BigDecimal("10.99");
        product.description = "Product description";

        assertEquals("Product name", product.name);
        assertEquals(new BigDecimal("10.99"), product.price);
        assertEquals("Product description", product.description);
    }
}