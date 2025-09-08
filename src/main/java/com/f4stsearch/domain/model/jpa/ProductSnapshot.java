package com.f4stsearch.domain.model.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_snapshots")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
