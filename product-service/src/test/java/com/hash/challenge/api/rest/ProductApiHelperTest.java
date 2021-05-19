package com.hash.challenge.api.rest;

import com.hash.challenge.api.rpc.DiscountClient;
import com.hash.challenge.domain.Product;
import com.hash.challenge.service.ProductService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.bson.types.ObjectId;
import org.graalvm.collections.Pair;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@QuarkusTest
public class ProductApiHelperTest {

    private static final String USER_ID = UUID.randomUUID().toString();

    @Inject
    ProductApiHelper helper;

    @InjectMock
    ProductService service;

    @InjectMock
    DiscountClient discountClient;

    @Test
    public void listShouldListProducts() {
        var mockedProducts = mockProducts();
        Mockito.when(service.list()).thenReturn(new ArrayList<>(mockedProducts.keySet()));
        mockedProducts.forEach((product, discount) -> {
            Mockito.when(discountClient.calculateDiscount(USER_ID, product.getIdAsString())).thenReturn(discount);
        });

        var actual = helper.list(USER_ID);

        assertNotNull(actual);
        assertEquals(3, actual.size());
        verify(service, times(1)).list();
        mockedProducts.forEach((product, discount) -> verify(discountClient, times(1)).calculateDiscount(USER_ID, product.getIdAsString()));
    }

    @Test
    public void listShouldReturnEmpty() {
        Mockito.when(service.list()).thenReturn(new ArrayList<>());

        var actual = helper.list(USER_ID);

        assertTrue(actual.isEmpty());
        verify(service, times(1)).list();
        verifyNoInteractions(discountClient);
    }

    private Map<Product, Pair<Float, Long>> mockProducts() {
        var mockMap = new HashMap<Product, Pair<Float, Long>>();

        var p1 = new Product(ObjectId.get(), "Vacina Covid 19 Power", "Vacina com 70% de eficácia", 1000);
        mockMap.put(p1, Pair.create(10F, 100L));

        var p2 = new Product(ObjectId.get(), "Vacina Covid 19 Mega Power", "Vacina com 80% de eficácia", 2000);
        mockMap.put(p2, Pair.create(10F, 200L));

        var p3 = new Product(ObjectId.get(), "Vacina Covid 19 Mega Blaster Power", "Vacina com 100% de eficácia", 4000);
        mockMap.put(p3, Pair.create(10F, 400L));

        return mockMap;
    }
}
