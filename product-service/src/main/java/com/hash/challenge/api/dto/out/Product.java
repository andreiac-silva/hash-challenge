package com.hash.challenge.api.dto.out;

import org.graalvm.collections.Pair;

public final class Product {

    private final String id;
    private final String title;
    private final String description;
    private final Integer priceInCents;
    private final Discount discount;

    public Product(String id, String title, String description, Integer priceInCents, Discount discount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priceInCents = priceInCents;
        this.discount = discount;
    }

    public static Product fromDomain(com.hash.challenge.domain.Product product, Pair<Float, Long> discountValues) {
        return new Product(
                product.getIdAsString(),
                product.getTitle(),
                product.getDescription(),
                product.getPriceInCents(),
                Discount.fromValues(discountValues)
        );
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPriceInCents() {
        return priceInCents;
    }

    public Discount getDiscount() {
        return discount;
    }
}
