package com.hash.challenge.api.rest;

import com.hash.challenge.api.dto.out.Product;
import com.hash.challenge.api.rpc.DiscountClient;
import com.hash.challenge.service.ProductService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/product")
public class ProductResource {

    @Inject
    DiscountClient discountClient;

    @Inject
    ProductService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@HeaderParam("X-USER-ID") Optional<String> userId) {
        var products = service.list()
                .stream()
                .map(p -> {
                    var discount = discountClient.calculateDiscount(userId.orElse(""), p.getIdAsString());
                    return Product.fromDomain(p, discount);
                })
                .collect(toList());

        return Response.ok(products).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(com.hash.challenge.api.dto.in.Product product) {
        service.add(product.toDomain());
        return Response.status(CREATED).build();
    }
}