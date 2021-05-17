package com.hash.challenge.api.rest;

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

import static javax.ws.rs.core.Response.Status.CREATED;

@Path("/product")
public class ProductResource {

    @Inject
    ProductApiHelper productApiHelper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@HeaderParam("X-USER-ID") Optional<String> userId) {
        var products = productApiHelper.list(userId.orElse(""));
        return Response.ok(products).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(com.hash.challenge.api.dto.in.Product product) {
        productApiHelper.add(product);
        return Response.status(CREATED).build();
    }
}