package com.f4stsearch.application.controller;

import com.f4stsearch.domain.model.Product;
import com.f4stsearch.domain.port.client.ProductSearchPort;
import feign.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class SearchController {

    private final ProductSearchPort productSearchPort;

    public SearchController(ProductSearchPort productSearchPort) {
        this.productSearchPort = productSearchPort;
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam String query) {
        return ResponseEntity.ok(productSearchPort.search(query));
    }
}
