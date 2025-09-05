package com.f4stsearch.domain.port;

import com.f4stsearch.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductSearchPort {
    List<Product> findAll();
    Optional<Product> findById(Long id);
}
