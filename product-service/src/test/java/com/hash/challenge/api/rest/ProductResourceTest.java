package com.hash.challenge.api.rest;

import com.hash.challenge.api.dto.out.Discount;
import com.hash.challenge.api.dto.out.Product;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ProductResourceTest {

    @InjectMock
    ProductApiHelper helper;

    @Test
    public void listShouldListProducts() {
        var products = mockProducts();
        Mockito.when(helper.list("")).thenReturn(products);

        given()
                .when().get("/product")
                .then()
                .statusCode(200)
                .body("$.size()", is(products.size()),
                        "[0].title", is("Vacina Covid 19 Power"),
                        "[0].description", is("Vacina com 70% de eficácia"),
                        "[0].priceInCents", is(1000),
                        "[0].discount.percentage", is(10F),
                        "[0].discount.valueInCents", is(100),
                        "[1].title", is("Vacina Covid 19 Mega Blaster Power"),
                        "[1].description", is("Vacina com 100% de eficácia"),
                        "[1].priceInCents", is(4000),
                        "[1].discount.percentage", is(10F),
                        "[1].discount.valueInCents", is(400));
    }

    @Test
    public void listShouldReturnAnEmptyList() {
        Mockito.when(helper.list("")).thenReturn(emptyList());

        given()
                .when().get("/product")
                .then()
                .statusCode(200)
                .body("$.size()", is(0));
    }

    private List<Product> mockProducts() {
        var p1 = new Product(UUID.randomUUID().toString(), "Vacina Covid 19 Power", "Vacina com 70% de eficácia", 1000, new Discount(10F, 100L));
        var p2 = new Product(UUID.randomUUID().toString(), "Vacina Covid 19 Mega Blaster Power", "Vacina com 100% de eficácia", 4000, new Discount(10F, 400L));
        return List.of(p1, p2);
    }
}
