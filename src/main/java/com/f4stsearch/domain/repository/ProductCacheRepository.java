package com.f4stsearch.domain.repository;

import com.f4stsearch.domain.model.jpa.ProductCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCacheRepository extends JpaRepository<ProductCache, Long> {
    Optional<ProductCache> findByExternalId(Long externalId);
}
