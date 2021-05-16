package com.hash.challenge.api.rpc;

import com.hash.challenge.api.rpc.proto.DiscountInputProto;
import com.hash.challenge.api.rpc.proto.DiscountServiceGrpc;
import io.grpc.StatusRuntimeException;
import io.quarkus.grpc.runtime.annotations.GrpcService;
import org.graalvm.collections.Pair;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DiscountClient {

    private static final Logger LOG = Logger.getLogger(DiscountClient.class);

    @Inject
    @GrpcService("discount")
    DiscountServiceGrpc.DiscountServiceBlockingStub client;

    public Pair<Float, Long> calculateDiscount(String userId, String productId) {
        DiscountInputProto.DiscountResponse response;

        try {
            response = client.discount(
                    DiscountInputProto.DiscountRequest.newBuilder()
                            .setUserId(userId)
                            .setProductId(productId)
                            .build());

        } catch (StatusRuntimeException e) {
            LOG.errorf("Something went wrong calculating discount of product with id %s. Assuming no discounts for it.", productId, e);
            return Pair.empty();
        }

        return Pair.create(response.getPercentage(), response.getValueInCents());
    }
}
