package com.hash.challenge.api.dto.in;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public final class Product {

    @NotBlank
    private final String title;

    @NotBlank
    private final String description;

    @Positive
    private final Integer priceInCents;

    public Product(String title, String description, Integer priceInCents) {
        this.title = title;
        this.description = description;
        this.priceInCents = priceInCents;
    }

    public com.hash.challenge.domain.Product toDomain() {
        return new com.hash.challenge.domain.Product(
                title,
                description,
                priceInCents);
    }
}
