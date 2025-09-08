package com.f4stsearch.domain.service;

import com.f4stsearch.domain.mapper.ProductMapper;
import com.f4stsearch.domain.model.Product;
import com.f4stsearch.domain.model.jpa.ProductCache;
import com.f4stsearch.domain.model.jpa.ProductSnapshot;
import com.f4stsearch.domain.repository.ProductCacheRepository;
import com.f4stsearch.domain.repository.ProductSnapshotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InsightService {

    private final ProductCacheRepository cacheRepository;
    private final ProductSnapshotRepository snapshotRepository;

    public List<Product> bestRate(int limit) {
        return cacheRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(ProductCache::getRatingRate).reversed())
                .limit(limit)
                .map(ProductMapper::fromCache)
                .toList();
    }

    public List<Product> bestValue(String category, int limit) {
        return cacheRepository.findAll().stream()
                .filter(p -> category == null || p.getCategory().equalsIgnoreCase(category))
                .sorted(Comparator.comparingDouble(this::valueScore).reversed())
                .limit(limit)
                .map(ProductMapper::fromCache)
                .toList();
    }

    private double valueScore(ProductCache product) {
        double popularity = Math.log(1 + product.getRatingCount());
        return (product.getRatingRate() * popularity) / (product.getPrice() > 0 ? product.getPrice() : 1);
    }

    public Map<String, Double> averagePriceByCategory() {
        return cacheRepository.findAll().stream()
                .collect(Collectors.groupingBy(ProductCache::getCategory,
                        Collectors.averagingDouble(ProductCache::getPrice)));
    }

    public Map<String, Double> averageRatingByCategory() {
        return cacheRepository.findAll().stream()
                .collect(Collectors.groupingBy(ProductCache::getCategory,
                        Collectors.averagingDouble(ProductCache::getRatingRate)));
    }

    public Map<String, Product> topProductByCategory() {
        return cacheRepository.findAll().stream()
                .collect(Collectors.groupingBy(ProductCache::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(ProductCache::getRatingRate)),
                                optional -> ProductMapper.fromCache(optional.orElse(null))
                        )));
    }

    public Map<Long, Double> averagePricePerProduct() {
        return snapshotRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        snapshot -> snapshot.getProductCache().getId(),
                        Collectors.averagingDouble(ProductSnapshot::getPrice)
                ));
    }
}
