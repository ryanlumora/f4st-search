package com.f4stsearch.domain.repository;

import com.f4stsearch.domain.model.jpa.ProductSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSnapshotRepository extends JpaRepository<ProductSnapshot, Long> {}
