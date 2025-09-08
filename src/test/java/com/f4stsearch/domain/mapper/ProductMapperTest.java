package com.f4stsearch.domain.mapper;

import com.f4stsearch.domain.model.Product;
import com.f4stsearch.domain.model.jpa.ProductCache;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    @Test
    void givenProductCache_whenMapping_thenReturnProduct() {
        ProductCache cache = new ProductCache();
        cache.setExternalId(1L);
        cache.setTitle("Produto Teste");
        cache.setDescription("Descrição");
        cache.setPrice(100.0);
        cache.setCategory("Eletrônicos");
        cache.setImage("url");
        cache.setRatingRate(4.5);
        cache.setRatingCount(10);

        Product product = ProductMapper.fromCache(cache);

        assertEquals(cache.getExternalId(), product.getId());
        assertEquals(cache.getTitle(), product.getTitle());
        assertEquals(cache.getPrice(), product.getPrice());
        assertEquals(cache.getRatingRate(), product.getRating().getRate());
        assertEquals(cache.getRatingCount(), product.getRating().getCount());
    }
}
