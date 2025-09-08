package com.f4stsearch.application.controller;

import com.f4stsearch.domain.model.Product;
import com.f4stsearch.domain.service.InsightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/insights")
@AllArgsConstructor
public class InsightController {
    private final InsightService service;

    @GetMapping("/best-value")
    @Operation(summary = "Get products with best value")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved best value products")
    })
    public ResponseEntity<List<Product>> bestValue(
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(service.bestValue(category, limit));
    }

    @GetMapping("/best-rate")
    @Operation(summary = "Get products with highest rating")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved best rated products")
    })
    public ResponseEntity<List<Product>> bestRate(@RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(service.bestRate(limit));
    }

    @GetMapping("/average-price-by-category")
    @Operation(summary = "Get average price per category")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved average price by category")
    })
    public ResponseEntity<Map<String, Double>> averagePriceByCategory() {
        return ResponseEntity.ok(service.averagePriceByCategory());
    }

    @GetMapping("/average-rating-by-category")
    @Operation(summary = "Get average rating per category")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved average rating by category")
    })
    public ResponseEntity<Map<String, Double>> averageRatingByCategory() {
        return ResponseEntity.ok(service.averageRatingByCategory());
    }

    @GetMapping("/top-by-category")
    @Operation(summary = "Get top rated product per category")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved top product by category")
    })
    public ResponseEntity<Map<String, Product>> topProductByCategory() {
        return ResponseEntity.ok(service.topProductByCategory());
    }

    @GetMapping("/historical/average-price")
    @Operation(summary = "Get historical average price per product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved historical average price")
    })
    public ResponseEntity<Map<Long, Double>> averagePricePerProduct() {
        return ResponseEntity.ok(service.averagePricePerProduct());
    }
}
