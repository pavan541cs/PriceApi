package com.target.price.model;

import org.springframework.data.annotation.Id;

public class Product {
    @Id
    private String product_id;
    private Price price;

    public Product() {
        super();
    }

    public Product(String product_id, Price price) {
        this.product_id = product_id;
        this.price = price;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
