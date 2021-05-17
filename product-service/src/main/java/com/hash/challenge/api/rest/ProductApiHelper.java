package com.hash.challenge.api.rest;

import com.hash.challenge.api.dto.out.Product;
import com.hash.challenge.api.rpc.DiscountClient;
import com.hash.challenge.service.ProductService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.toList;

@ApplicationScoped
public class ProductApiHelper {

    @Inject
    ProductService productService;

    @Inject
    DiscountClient discountClient;

    public List<Product> list(String userId) {
        return productService.list()
                .stream()
                .map(p -> {
                    var discount = discountClient.calculateDiscount(userId, p.getIdAsString());
                    return Product.fromDomain(p, discount);
                })
                .collect(toList());
    }

    public void add(com.hash.challenge.api.dto.in.Product product) {
        productService.add(product.toDomain());
    }
}
