package com.hash.challenge.api.dto.out;

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

    public static Product fromDomain(com.hash.challenge.domain.Product product) {
        return new Product
                (
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPriceInCents(),
                null);
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
