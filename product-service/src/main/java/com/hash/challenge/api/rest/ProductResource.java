package com.hash.challenge.api.rest;

import com.hash.challenge.api.dto.out.Product;
import com.hash.challenge.service.ProductService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.util.stream.Collectors.toList;
import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/products")
public class ProductResource {

    @Inject
    ProductService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        var products = service.list();
        return Response.ok(products.stream().map(Product::fromDomain).collect(toList())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(com.hash.challenge.api.dto.in.Product product) {
        service.add(product.toDomain());
        return Response.status(CREATED).build();
    }
}