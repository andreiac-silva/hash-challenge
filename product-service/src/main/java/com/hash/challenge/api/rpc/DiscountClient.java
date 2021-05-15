package com.hash.challenge.api.rpc;

import io.quarkus.grpc.runtime.annotations.GrpcService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;


@Path("/hello")
public class DiscountClient {

    @Inject
    @GrpcService("discount")
    DiscountServiceGrpc.DiscountServiceBlockingStub client;

    @GET
    public String hello() {
        DiscountResponse discount = client.discount(DiscountRequest.newBuilder().build());
        System.out.println("Discount = " + discount);
        return "hey";
    }
}
