package com.f4stsearch.domain.service;

import com.f4stsearch.domain.model.jpa.ProductCache;
import com.f4stsearch.domain.repository.ProductCacheRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductCacheServiceTest {

    private ProductCacheRepository repository;
    private ProductCacheService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(ProductCacheRepository.class);
        service = new ProductCacheService(repository);
    }

    @Test
    void givenProductsInRepository_whenFindAll_thenReturnAllProducts() {
        when(repository.findAll()).thenReturn(List.of(new ProductCache()));
        List<ProductCache> result = service.findAll();
        assertEquals(1, result.size());
    }

    @Test
    void givenProductInRepository_whenFindByExternalId_thenReturnProduct() {
        ProductCache product = new ProductCache();
        product.setExternalId(1L);
        when(repository.findByExternalId(1L)).thenReturn(Optional.of(product));

        ProductCache result = service.findByExternalId(1L);
        assertNotNull(result);
        assertEquals(1L, result.getExternalId());
    }
}
