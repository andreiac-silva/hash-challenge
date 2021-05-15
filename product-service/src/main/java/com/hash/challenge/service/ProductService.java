package com.hash.challenge.service;

import com.hash.challenge.domain.Product;
import com.hash.challenge.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository repository;

    public List<Product> list() {
        return repository.findAll();
    }

    public void add(final Product product) {
        repository.save(product);
    }
}
