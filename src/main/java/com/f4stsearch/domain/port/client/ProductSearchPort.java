package com.f4stsearch.domain.port.client;

import com.f4stsearch.domain.model.Product;

import java.util.List;

public interface ProductSearchPort {
    List<Product> search(String query);
}
