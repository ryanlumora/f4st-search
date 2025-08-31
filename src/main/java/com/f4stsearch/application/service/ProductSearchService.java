package com.f4stsearch.application.service;

import com.f4stsearch.adapter.client.MercadoLivreClient;
import com.f4stsearch.domain.model.Product;
import com.f4stsearch.domain.port.client.ProductSearchPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSearchService {

    private final ProductSearchPort productSearchPort;

    public ProductSearchService(ProductSearchPort productSearchPort) {
        this.productSearchPort = productSearchPort;
    }

    public List<Product> searchProducts(String query) {
        return productSearchPort.search(query);
    }
}
