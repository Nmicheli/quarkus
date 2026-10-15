package com.orderflow;

import com.orderflow.model.Product;
import com.orderflow.model.dto.ProductRequest;
import com.orderflow.model.dto.ProductResponse;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @GET
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ProductResponse> getAll() {
        return Product.<Product>listAll()
                .stream()
                .map(p -> new ProductResponse(p.id, p.name, p.price, p.description))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    @Transactional(Transactional.TxType.SUPPORTS)
    public Response getById(@PathParam("id") Long id) {
        Product product = Product.findById(id);
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        ProductResponse response = new ProductResponse(product.id, product.name, product.price, product.description);
        return Response.ok(response).build();
    }

    @POST
    @Transactional
    public Response create(@Valid ProductRequest request) {
        Product product = new Product(request.name, request.price, request.description);
        product.persist();
        ProductResponse response = new ProductResponse(product.id, product.name, product.price, product.description);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, @Valid ProductRequest request) {
        Product product = Product.findById(id);
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        product.name = request.name;
        product.price = request.price;
        product.description = request.description;
        ProductResponse response = new ProductResponse(product.id, product.name, product.price, product.description);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = Product.deleteById(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
