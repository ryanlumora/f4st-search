package com.f4stsearch.domain.service;

import com.f4stsearch.domain.model.jpa.ProductCache;
import com.f4stsearch.domain.model.jpa.ProductSnapshot;
import com.f4stsearch.domain.repository.ProductSnapshotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SnapshotService {

    private final ProductSnapshotRepository repository;

    public SnapshotService(ProductSnapshotRepository repository) {
        this.repository = repository;
    }

    public void createSnapshot(ProductCache productCache) {
        ProductSnapshot snapshot = new ProductSnapshot();
        snapshot.setProductCache(productCache);
        snapshot.setPrice(productCache.getPrice());
        snapshot.setRatingRate(productCache.getRatingRate());
        snapshot.setRatingCount(productCache.getRatingCount());
        snapshot.setSnapshotTime(LocalDateTime.now());

        repository.save(snapshot);
    }
}
