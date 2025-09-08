package com.f4stsearch.domain.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;
    private String link;

    public double getRateValue() {
        return this.rating != null ? this.rating.getRate() : 0.0;
    }
}