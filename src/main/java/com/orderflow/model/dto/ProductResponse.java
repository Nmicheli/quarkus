package com.orderflow.model.dto;

import java.math.BigDecimal;

public class ProductResponse {
    public Long id;
    public String name;
    public BigDecimal price;
    public String description;

    public ProductResponse() {
    }

    public ProductResponse(Long id, String name, BigDecimal price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
