package com.f4stsearch.domain.service;

import com.f4stsearch.domain.mapper.ProductMapper;
import com.f4stsearch.domain.model.Product;
import com.f4stsearch.domain.model.jpa.ProductCache;
import com.f4stsearch.domain.port.ProductSearchPort;
import com.f4stsearch.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductCacheService service;

    public ProductService(ProductCacheService service) {
        this.service = service;
    }

    public List<Product> findAll() {
        return service.findAll();
    }

    public Optional<Product> findById(Long id) {
        ProductCache cache = service.findByExternalId(id);
        return Optional.of(ProductMapper.fromCache(cache));
    }
}
