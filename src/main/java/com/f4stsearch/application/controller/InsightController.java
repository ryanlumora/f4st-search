package com.f4stsearch.application.controller;

import com.f4stsearch.domain.model.Product;
import com.f4stsearch.domain.service.InsightService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/insights")
@AllArgsConstructor
public class InsightController {
    private final InsightService service;

    @GetMapping("/best-value")
    public ResponseEntity<List<Product>> bestValue(
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(service.bestValue(category, limit));
    }

    @GetMapping("/best-rate")
    public ResponseEntity<List<Product>> bestRate(@RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(service.bestRate(limit));
    }

    @GetMapping("/average-price-by-category")
    public ResponseEntity<Map<String, Double>> averagePriceByCategory() {
        return ResponseEntity.ok(service.averagePriceByCategory());
    }

    @GetMapping("/average-rating-by-category")
    public ResponseEntity<Map<String, Double>> averageRatingByCategory() {
        return ResponseEntity.ok(service.averageRatingByCategory());
    }

    @GetMapping("/top-by-category")
    public ResponseEntity<Map<String, Product>> topProductByCategory() {
        return ResponseEntity.ok(service.topProductByCategory());
    }

    @GetMapping("/historical/average-price")
    public ResponseEntity<Map<Long, Double>> averagePricePerProduct() {
        return ResponseEntity.ok(service.averagePricePerProduct());
    }
}
