package com.f4stsearch.domain.model.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_cache")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long externalId;
    private String title;

    @Column(length = 1000)
    private String description;

    private double price;
    private String category;
    private String image;

    private Double ratingRate;
    private Integer ratingCount;

    private LocalDateTime lastUpdated;

    private String productLink;
}
