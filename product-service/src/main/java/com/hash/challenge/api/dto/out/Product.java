package com.hash.challenge.api.dto.out;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.graalvm.collections.Pair;

@RegisterForReflection
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return new EqualsBuilder().append(id, product.id).append(title, product.title).append(description, product.description).append(priceInCents, product.priceInCents).append(discount, product.discount).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(title).append(description).append(priceInCents).append(discount).toHashCode();
    }
}
