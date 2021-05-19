package com.hash.challenge.api.dto.out;

import com.hash.challenge.domain.Product;
import io.quarkus.test.junit.QuarkusTest;
import org.bson.types.ObjectId;
import org.graalvm.collections.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class ProductTest {

    @Test
    public void productShouldBeParsedToDto() {
        var product = new Product(ObjectId.get(), "Vacina Covid 19", "Vacina com 100% de eficácia", 1000);
        var discount = Pair.create(10F, 100L);

        var actual = com.hash.challenge.api.dto.out.Product.fromDomain(product, discount);

        assertNotNull(actual);
        assertEquals(product.getTitle(), actual.getTitle());
        assertEquals(product.getDescription(), actual.getDescription());
        assertEquals(product.getPriceInCents(), actual.getPriceInCents());
        assertEquals(product.getIdAsString(), actual.getId());
        assertEquals(discount.getLeft(), actual.getDiscount().getPercentage());
        assertEquals(discount.getRight(), actual.getDiscount().getValueInCents());
    }

    @Test
    public void productShouldBeParsedWithNoDiscountToDto() {
        var product = new Product(ObjectId.get(), "Vacina Covid 19", "Vacina com 100% de eficácia", 1000);

        var actual = com.hash.challenge.api.dto.out.Product.fromDomain(product, Pair.empty());

        assertNotNull(actual);
        assertEquals(product.getTitle(), actual.getTitle());
        assertEquals(product.getDescription(), actual.getDescription());
        assertEquals(product.getPriceInCents(), actual.getPriceInCents());
        assertEquals(product.getIdAsString(), actual.getId());
        assertEquals(0F, actual.getDiscount().getPercentage());
        assertEquals(0L, actual.getDiscount().getValueInCents());
    }
}
