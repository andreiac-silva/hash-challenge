package com.hash.challenge.api.dto.in;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public final class Product {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Positive
    private Integer priceInCents;

    public Product() {
    }

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

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPriceInCents() {
        return priceInCents;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriceInCents(Integer priceInCents) {
        this.priceInCents = priceInCents;
    }
}
