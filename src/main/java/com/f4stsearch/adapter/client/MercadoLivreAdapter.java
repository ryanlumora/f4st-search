package com.f4stsearch.adapter.client;

import com.f4stsearch.adapter.client.dto.MercadoLivreItemDTO;
import com.f4stsearch.application.mapper.ProductMapper;
import com.f4stsearch.domain.model.Product;
import com.f4stsearch.domain.port.client.ProductSearchPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MercadoLivreAdapter implements ProductSearchPort {

    private final MercadoLivreClient client;

    public MercadoLivreAdapter(MercadoLivreClient client) {
        this.client = client;
    }

    @Override
    public List<Product> search(String query) {
        List<MercadoLivreItemDTO> results = client.searchProducts(query,20);
        return results.stream()
                .map(ProductMapper::toDomain)
                .toList();
    }
}