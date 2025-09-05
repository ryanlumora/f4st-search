package com.f4stsearch.adapter.out;

import com.f4stsearch.domain.model.Product;
import com.f4stsearch.domain.model.Rating;
import com.f4stsearch.domain.port.ProductSearchPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FakeStoreAdapter implements ProductSearchPort {

    private final FakeStoreClient client;

    public FakeStoreAdapter(FakeStoreClient client) {
        this.client = client;
    }

    @Override
    public List<Product> findAll() {
        return client.getAll().stream().map(this::map).toList();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.of(map(client.byId(id)));
    }


    private Product map(FakeStoreProductDTO dto) {
        return new Product(
                dto.getId(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getCategory(),
                dto.getImage(),
                new Rating(
                        dto.getRating().getCount(),
                        dto.getRating().getRate()
                ));
    }
}