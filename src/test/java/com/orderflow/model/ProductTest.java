package com.orderflow.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    @Test
    public void testDefaultConstructor() {
        Product product = new Product();
        assertNull(product.name);
        assertNull(product.price);
        assertNull(product.description);
    }

    @Test
    public void testParameterizedConstructor() {
        Product product = new Product("Test Product", new BigDecimal("25.50"), "Test description");
        
        assertEquals("Test Product", product.name);
        assertEquals(new BigDecimal("25.50"), product.price);
        assertEquals("Test description", product.description);
    }

    @Test
    public void testConstructorWithNullDescription() {
        Product product = new Product("Test Product", new BigDecimal("25.50"), null);
        
        assertEquals("Test Product", product.name);
        assertEquals(new BigDecimal("25.50"), product.price);
        assertNull(product.description);
    }

    @Test
    public void testConstructorWithEmptyDescription() {
        Product product = new Product("Test Product", new BigDecimal("25.50"), "");
        
        assertEquals("Test Product", product.name);
        assertEquals(new BigDecimal("25.50"), product.price);
        assertEquals("", product.description);
    }

    @Test
    public void testConstructorWithZeroPrice() {
        Product product = new Product("Test Product", BigDecimal.ZERO, "Description");
        
        assertEquals("Test Product", product.name);
        assertEquals(BigDecimal.ZERO, product.price);
        assertEquals("Description", product.description);
    }

    @Test
    public void testConstructorWithLargeDescription() {
        String largeDescription = "A".repeat(500);
        Product product = new Product("Test Product", new BigDecimal("25.50"), largeDescription);
        
        assertEquals("Test Product", product.name);
        assertEquals(new BigDecimal("25.50"), product.price);
        assertEquals(500, product.description.length());
    }

    @Test
    public void testConstructorWithMinimumNameLength() {
        Product product = new Product("AB", new BigDecimal("25.50"), "Description");
        
        assertEquals("AB", product.name);
        assertEquals(new BigDecimal("25.50"), product.price);
        assertEquals("Description", product.description);
    }

    @Test
    public void testConstructorWithMaximumNameLength() {
        String maxName = "A".repeat(100);
        Product product = new Product(maxName, new BigDecimal("25.50"), "Description");
        
        assertEquals(100, product.name.length());
        assertEquals(new BigDecimal("25.50"), product.price);
        assertEquals("Description", product.description);
    }
}