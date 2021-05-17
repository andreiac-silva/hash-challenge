package com.hash.challenge.repository;

import com.hash.challenge.domain.Product;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ProductRepository implements PanacheMongoRepository<Product> {

    public List<Product> list() {
        return listAll();
    }
}
