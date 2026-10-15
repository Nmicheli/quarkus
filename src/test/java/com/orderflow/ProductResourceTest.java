package com.orderflow;

import com.orderflow.model.Product;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
public class ProductResourceTest {

    private Long testProductId;

    @BeforeEach
    @Transactional
    public void setup() {
        Product.deleteAll();
        Product product = new Product("Test Product", new BigDecimal("19.99"), "Test description");
        product.persist();
        testProductId = product.id;
    }

    @Test
    public void testGetAllProducts() {
        given()
            .when().get("/products")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(greaterThanOrEqualTo(1)));
    }

    @Test
    public void testGetProductById() {
        given()
            .pathParam("id", testProductId)
            .when().get("/products/{id}")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(testProductId.intValue()))
                .body("name", equalTo("Test Product"))
                .body("price", equalTo(19.99f))
                .body("description", equalTo("Test description"));
    }

    @Test
    public void testGetProductByIdNotFound() {
        given()
            .pathParam("id", 99999)
            .when().get("/products/{id}")
            .then()
                .statusCode(404);
    }

    @Test
    public void testCreateProduct() {
        String requestBody = "{\"name\":\"New Product\",\"price\":29.99,\"description\":\"New description\"}";
        
        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when().post("/products")
            .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("name", equalTo("New Product"))
                .body("price", equalTo(29.99f))
                .body("description", equalTo("New description"));
    }

    @Test
    public void testCreateProductValidationError() {
        String requestBody = "{\"name\":\"A\",\"price\":-10.0,\"description\":\"\"}";
        
        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when().post("/products")
            .then()
                .statusCode(400);
    }

    @Test
    public void testUpdateProduct() {
        String requestBody = "{\"name\":\"Updated Product\",\"price\":39.99,\"description\":\"Updated description\"}";
        
        given()
            .pathParam("id", testProductId)
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when().put("/products/{id}")
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(testProductId.intValue()))
                .body("name", equalTo("Updated Product"))
                .body("price", equalTo(39.99f))
                .body("description", equalTo("Updated description"));
    }

    @Test
    public void testUpdateProductNotFound() {
        String requestBody = "{\"name\":\"Updated Product\",\"price\":39.99,\"description\":\"Updated description\"}";
        
        given()
            .pathParam("id", 99999)
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when().put("/products/{id}")
            .then()
                .statusCode(404);
    }

    @Test
    public void testDeleteProduct() {
        given()
            .pathParam("id", testProductId)
            .when().delete("/products/{id}")
            .then()
                .statusCode(204);
    }

    @Test
    public void testDeleteProductNotFound() {
        given()
            .pathParam("id", 99999)
            .when().delete("/products/{id}")
            .then()
                .statusCode(404);
    }
}
