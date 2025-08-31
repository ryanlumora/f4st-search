package com.f4stsearch.domain.model;

import lombok.*;

@NoArgsConstructor
public class Product {
    private String id;
    private String title;
    private Double price;
    private String permalink;

    public Product(String id, String title, Double price, String permalink) {
        this.id = id;
        this.title = title;
        this.permalink = permalink;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

}
