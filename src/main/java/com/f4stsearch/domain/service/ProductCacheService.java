package com.f4stsearch.domain.service;

import com.f4stsearch.adapter.out.FakeStoreProductDTO;
import com.f4stsearch.domain.mapper.ProductMapper;
import com.f4stsearch.domain.model.Product;
import com.f4stsearch.domain.model.jpa.ProductCache;
import com.f4stsearch.domain.repository.ProductCacheRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductCacheService {

    private final ProductCacheRepository repository;

    public ProductCacheService(ProductCacheRepository repository) {
        this.repository = repository;
    }

    public ProductCache saveOrUpdate(FakeStoreProductDTO dto) {
        ProductCache product = repository.findByExternalId(dto.getId())
                .orElse(new ProductCache());

        product.setExternalId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
        product.setImage(dto.getImage());
        product.setRatingRate(dto.getRating().getRate());
        product.setRatingCount(dto.getRating().getCount());
        product.setLastUpdated(LocalDateTime.now());
        product.setProductLink("https://fakestoreapi.com/products/" + dto.getId());

        return repository.save(product);
    }

    public List<Product> findAll() {
        return repository.findAll().stream().map(ProductMapper::fromCache).toList();
    }

    public ProductCache findByExternalId(Long externalId) {
        return repository.findByExternalId(externalId).orElse(null);
    }
}
