package com.orderflow.model.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProductRequestTest {

    @Test
    public void testDefaultConstructor() {
        ProductRequest request = new ProductRequest();
        assertNull(request.name);
        assertNull(request.price);
        assertNull(request.description);
    }

    @Test
    public void testParameterizedConstructor() {
        ProductRequest request = new ProductRequest("Product Name", new BigDecimal("99.99"), "Product description");
        
        assertEquals("Product Name", request.name);
        assertEquals(new BigDecimal("99.99"), request.price);
        assertEquals("Product description", request.description);
    }

    @Test
    public void testConstructorWithNullDescription() {
        ProductRequest request = new ProductRequest("Product Name", new BigDecimal("99.99"), null);
        
        assertEquals("Product Name", request.name);
        assertEquals(new BigDecimal("99.99"), request.price);
        assertNull(request.description);
    }

    @Test
    public void testConstructorWithEmptyDescription() {
        ProductRequest request = new ProductRequest("Product Name", new BigDecimal("99.99"), "");
        
        assertEquals("Product Name", request.name);
        assertEquals(new BigDecimal("99.99"), request.price);
        assertEquals("", request.description);
    }

    @Test
    public void testConstructorWithZeroPrice() {
        ProductRequest request = new ProductRequest("Product Name", BigDecimal.ZERO, "Description");
        
        assertEquals("Product Name", request.name);
        assertEquals(BigDecimal.ZERO, request.price);
        assertEquals("Description", request.description);
    }

    @Test
    public void testConstructorWithLargeDescription() {
        String largeDescription = "A".repeat(500);
        ProductRequest request = new ProductRequest("Product Name", new BigDecimal("99.99"), largeDescription);
        
        assertEquals("Product Name", request.name);
        assertEquals(new BigDecimal("99.99"), request.price);
        assertEquals(500, request.description.length());
    }
}
