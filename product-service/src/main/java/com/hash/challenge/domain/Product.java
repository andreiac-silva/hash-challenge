package com.hash.challenge.domain;

public class Product {

    private String id;
    private String title;
    private String description;
    private Integer priceInCents;

    public Product() {
    }

    public Product(String title, String description, Integer priceInCents) {
        this.title = title;
        this.description = description;
        this.priceInCents = priceInCents;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(Integer priceInCents) {
        this.priceInCents = priceInCents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
