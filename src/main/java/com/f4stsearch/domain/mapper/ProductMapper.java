package com.f4stsearch.domain.mapper;

import com.f4stsearch.domain.model.Product;
import com.f4stsearch.domain.model.Rating;
import com.f4stsearch.domain.model.jpa.ProductCache;

public class ProductMapper {

    public static Product fromCache(ProductCache cache) {
        return Product.builder()
                .id(cache.getExternalId())
                .title(cache.getTitle())
                .description(cache.getDescription())
                .price(cache.getPrice())
                .category(cache.getCategory())
                .image(cache.getImage())
                .link(cache.getProductLink())
                .rating(Rating.builder()
                        .rate(cache.getRatingRate())
                        .count(cache.getRatingCount())
                        .build())
                .build();
    }
}