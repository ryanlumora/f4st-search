package com.f4stsearch.application.mapper;

import com.f4stsearch.adapter.client.dto.MercadoLivreItemDTO;
import com.f4stsearch.domain.model.Product;

public class ProductMapper {
    public static Product toDomain(MercadoLivreItemDTO dto) {
        return new Product(dto.id(), dto.title(), dto.price(), dto.title());
    }
}
