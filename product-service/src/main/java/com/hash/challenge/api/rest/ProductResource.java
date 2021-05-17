package com.hash.challenge.api.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

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
}