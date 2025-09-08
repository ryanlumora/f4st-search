package com.f4stsearch.domain.service;

import com.f4stsearch.domain.model.jpa.ProductCache;
import com.f4stsearch.domain.repository.ProductCacheRepository;
import com.f4stsearch.domain.repository.ProductSnapshotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class InsightServiceTest {

    private ProductCacheRepository cacheRepository;
    private ProductSnapshotRepository snapshotRepository;
    private InsightService insightService;

    @BeforeEach
    void setUp() {
        cacheRepository = Mockito.mock(ProductCacheRepository.class);
        snapshotRepository = Mockito.mock(ProductSnapshotRepository.class);
        insightService = new InsightService(cacheRepository,snapshotRepository);
    }

    @Test
    void givenProducts_whenGetBestRate_thenReturnTopRatedProduct() {
        ProductCache p1 = new ProductCache();
        p1.setRatingRate(4.5);
        ProductCache p2 = new ProductCache();
        p2.setRatingRate(3.0);
        when(cacheRepository.findAll()).thenReturn(List.of(p1, p2));

        var result = insightService.bestRate(1);

        assertEquals(1, result.size());
        assertEquals(4.5, result.getFirst().getRating().getRate());
    }

    @Test
    void givenProducts_whenGetBestValue_thenReturnTopValueProduct() {
        ProductCache p1 = new ProductCache();
        p1.setPrice(100.0);
        p1.setRatingRate(4.5);
        p1.setRatingCount(10);
        p1.setCategory("Electronics");

        ProductCache p2 = new ProductCache();
        p2.setPrice(200.0);
        p2.setRatingRate(4.5);
        p2.setRatingCount(10);
        p2.setCategory("Electronics");

        when(cacheRepository.findAll()).thenReturn(List.of(p1, p2));

        var result = insightService.bestValue("Electronics", 1);

        assertEquals(1, result.size());
        assertEquals(100.0, result.getFirst().getPrice());
    }
}
