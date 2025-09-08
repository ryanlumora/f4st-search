package com.f4stsearch.domain.service;

import com.f4stsearch.domain.mapper.ProductMapper;
import com.f4stsearch.domain.model.Product;
import com.f4stsearch.domain.model.jpa.ProductCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private ProductCacheService cacheService;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        cacheService = mock(ProductCacheService.class);
        productService = new ProductService(cacheService);
    }

    @Test
    void testFindAllReturnsMappedProducts() {
        ProductCache cache1 = new ProductCache();
        ProductCache cache2 = new ProductCache();
        when(cacheService.findAll()).thenReturn(Stream.of(cache1, cache2).map(ProductMapper::fromCache).toList());

        List<Product> result = productService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testFindByIdReturnsMappedProduct() {
        ProductCache cache = new ProductCache();
        cache.setExternalId(1L);
        when(cacheService.findByExternalId(1L)).thenReturn(cache);

        Optional<Product> result = productService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(cache.getExternalId(), result.get().getId());
    }
}
