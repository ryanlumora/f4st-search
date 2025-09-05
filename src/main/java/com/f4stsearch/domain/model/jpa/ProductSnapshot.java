package com.f4stsearch.domain.model.jpa;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_snapshots")
public class ProductSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_cache_id", nullable = false)
    private ProductCache productCache;

    private double price;
    private double ratingRate;
    private Integer ratingCount;

    private LocalDateTime snapshotTime;

    public ProductSnapshot() {}

    public ProductCache getProductCache() {
        return productCache;
    }

    public void setProductCache(ProductCache productCache) {
        this.productCache = productCache;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Double getRatingRate() {
        return ratingRate;
    }

    public void setRatingRate(Double ratingRate) {
        this.ratingRate = ratingRate;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public LocalDateTime getSnapshotTime() {
        return snapshotTime;
    }

    public void setSnapshotTime(LocalDateTime snapshotTime) {
        this.snapshotTime = snapshotTime;
    }
}
