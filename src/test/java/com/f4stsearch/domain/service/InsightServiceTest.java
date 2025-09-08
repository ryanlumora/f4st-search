package com.f4stsearch.domain.service;

import com.f4stsearch.domain.mapper.ProductMapper;
import com.f4stsearch.domain.model.Product;
import com.f4stsearch.domain.model.jpa.ProductCache;
import com.f4stsearch.domain.model.jpa.ProductSnapshot;
import com.f4stsearch.domain.repository.ProductCacheRepository;
import com.f4stsearch.domain.repository.ProductSnapshotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InsightServiceTest {

    private ProductCacheRepository cacheRepository;
    private ProductSnapshotRepository snapshotRepository;
    private InsightService insightService;

    @BeforeEach
    void setUp() {
        cacheRepository = mock(ProductCacheRepository.class);
        snapshotRepository = mock(ProductSnapshotRepository.class);
        insightService = new InsightService(cacheRepository, snapshotRepository);
    }

    @Test
    void testBestRate() {
        ProductCache p1 = new ProductCache(); p1.setRatingRate(4.5);
        ProductCache p2 = new ProductCache(); p2.setRatingRate(3.0);
        when(cacheRepository.findAll()).thenReturn(List.of(p1, p2));

        List<Product> result = insightService.bestRate(1);

        assertEquals(1, result.size());
        assertEquals(4.5, result.get(0).getRating().getRate());
    }

    @Test
    void testBestValue() {
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

        List<Product> result = insightService.bestValue("Electronics", 1);

        assertEquals(1, result.size());
        assertEquals(100.0, result.get(0).getPrice());
    }

    @Test
    void testAveragePriceByCategory() {
        ProductCache p1 = new ProductCache(); p1.setCategory("A"); p1.setPrice(100);
        ProductCache p2 = new ProductCache(); p2.setCategory("A"); p2.setPrice(200);
        ProductCache p3 = new ProductCache(); p3.setCategory("B"); p3.setPrice(300);
        when(cacheRepository.findAll()).thenReturn(List.of(p1, p2, p3));

        Map<String, Double> result = insightService.averagePriceByCategory();

        assertEquals(2, result.size());
        assertEquals(150.0, result.get("A"));
        assertEquals(300.0, result.get("B"));
    }

    @Test
    void testAverageRatingByCategory() {
        ProductCache p1 = new ProductCache(); p1.setCategory("A"); p1.setRatingRate(4.0);
        ProductCache p2 = new ProductCache(); p2.setCategory("A"); p2.setRatingRate(5.0);
        when(cacheRepository.findAll()).thenReturn(List.of(p1, p2));

        Map<String, Double> result = insightService.averageRatingByCategory();

        assertEquals(4.5, result.get("A"));
    }

    @Test
    void testTopProductByCategory() {
        ProductCache p1 = new ProductCache(); p1.setCategory("A"); p1.setRatingRate(4.0);
        ProductCache p2 = new ProductCache(); p2.setCategory("A"); p2.setRatingRate(5.0);
        when(cacheRepository.findAll()).thenReturn(List.of(p1, p2));

        Map<String, Product> result = insightService.topProductByCategory();

        assertEquals(1, result.size());
        assertEquals(5.0, result.get("A").getRating().getRate());
    }

    @Test
    void testAveragePricePerProduct() {
        ProductCache cache1 = new ProductCache(); cache1.setId(1L);
        ProductCache cache2 = new ProductCache(); cache2.setId(2L);

        ProductSnapshot s1 = new ProductSnapshot(); s1.setProductCache(cache1); s1.setPrice(100);
        ProductSnapshot s2 = new ProductSnapshot(); s2.setProductCache(cache1); s2.setPrice(200);
        ProductSnapshot s3 = new ProductSnapshot(); s3.setProductCache(cache2); s3.setPrice(300);

        when(snapshotRepository.findAll()).thenReturn(List.of(s1, s2, s3));

        Map<Long, Double> result = insightService.averagePricePerProduct();

        assertEquals(2, result.size());
        assertEquals(150.0, result.get(1L));
        assertEquals(300.0, result.get(2L));
    }
}
