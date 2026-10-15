package com.orderflow.model.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProductResponseTest {

    @Test
    public void testDefaultConstructor() {
        ProductResponse response = new ProductResponse();
        assertNull(response.id);
        assertNull(response.name);
        assertNull(response.price);
        assertNull(response.description);
    }

    @Test
    public void testParameterizedConstructor() {
        ProductResponse response = new ProductResponse(1L, "Product Name", new BigDecimal("99.99"), "Product description");
        
        assertEquals(1L, response.id);
        assertEquals("Product Name", response.name);
        assertEquals(new BigDecimal("99.99"), response.price);
        assertEquals("Product description", response.description);
    }

    @Test
    public void testConstructorWithNullId() {
        ProductResponse response = new ProductResponse(null, "Product Name", new BigDecimal("99.99"), "Description");
        
        assertNull(response.id);
        assertEquals("Product Name", response.name);
        assertEquals(new BigDecimal("99.99"), response.price);
        assertEquals("Description", response.description);
    }

    @Test
    public void testConstructorWithNullDescription() {
        ProductResponse response = new ProductResponse(1L, "Product Name", new BigDecimal("99.99"), null);
        
        assertEquals(1L, response.id);
        assertEquals("Product Name", response.name);
        assertEquals(new BigDecimal("99.99"), response.price);
        assertNull(response.description);
    }

    @Test
    public void testConstructorWithZeroPrice() {
        ProductResponse response = new ProductResponse(1L, "Product Name", BigDecimal.ZERO, "Description");
        
        assertEquals(1L, response.id);
        assertEquals("Product Name", response.name);
        assertEquals(BigDecimal.ZERO, response.price);
        assertEquals("Description", response.description);
    }

    @Test
    public void testConstructorWithLargeId() {
        ProductResponse response = new ProductResponse(Long.MAX_VALUE, "Product Name", new BigDecimal("99.99"), "Description");
        
        assertEquals(Long.MAX_VALUE, response.id);
        assertEquals("Product Name", response.name);
        assertEquals(new BigDecimal("99.99"), response.price);
        assertEquals("Description", response.description);
    }

    @Test
    public void testConstructorWithEmptyName() {
        ProductResponse response = new ProductResponse(1L, "", new BigDecimal("99.99"), "Description");
        
        assertEquals(1L, response.id);
        assertEquals("", response.name);
        assertEquals(new BigDecimal("99.99"), response.price);
        assertEquals("Description", response.description);
    }
}
