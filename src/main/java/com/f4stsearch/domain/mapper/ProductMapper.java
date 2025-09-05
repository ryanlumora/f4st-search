package com.f4stsearch.domain.mapper;

import com.f4stsearch.domain.model.Product;
import com.f4stsearch.domain.model.Rating;
import com.f4stsearch.domain.model.jpa.ProductCache;

public class ProductMapper {

    public static Product fromCache(ProductCache cache) {
        Product product = new Product();
        product.setId(cache.getExternalId());
        product.setTitle(cache.getTitle());
        product.setDescription(cache.getDescription());
        product.setPrice(cache.getPrice());
        product.setCategory(cache.getCategory());
        product.setImage(cache.getImage());

        Rating rating = new Rating();
        rating.setRate(cache.getRatingRate());
        rating.setCount(cache.getRatingCount());
        product.setRating(rating);

        return product;
    }
}