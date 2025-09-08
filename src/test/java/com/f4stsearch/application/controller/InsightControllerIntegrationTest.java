package com.f4stsearch.application.controller;

import com.f4stsearch.domain.repository.ProductCacheRepository;
import com.f4stsearch.domain.repository.ProductSnapshotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class InsightControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductCacheRepository cacheRepository;

    @Autowired
    private ProductSnapshotRepository snapshotRepository;

    @BeforeEach
    void setUp() {
        snapshotRepository.deleteAll();
        cacheRepository.deleteAll();

        var p1 = new com.f4stsearch.domain.model.jpa.ProductCache();
        p1.setExternalId(1L);
        p1.setTitle("Product 1");
        p1.setPrice(100.0);
        p1.setCategory("Electronics");
        p1.setRatingRate(4.5);
        p1.setRatingCount(10);

        var p2 = new com.f4stsearch.domain.model.jpa.ProductCache();
        p2.setExternalId(2L);
        p2.setTitle("Product 2");
        p2.setPrice(200.0);
        p2.setCategory("Electronics");
        p2.setRatingRate(3.5);
        p2.setRatingCount(5);

        cacheRepository.save(p1);
        cacheRepository.save(p2);
    }

    @Test
    void testBestValueEndpoint() throws Exception {
        mockMvc.perform(get("/insights/best-value")
                        .param("category", "Electronics")
                        .param("limit", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("Product 1")));
    }

    @Test
    void testBestRateEndpoint() throws Exception {
        mockMvc.perform(get("/insights/best-rate")
                        .param("limit", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rating.rate", is(4.5)));
    }

    @Test
    void testAveragePriceByCategoryEndpoint() throws Exception {
        mockMvc.perform(get("/insights/average-price-by-category")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Electronics", is(150.0)));
    }

    @Test
    void testAverageRatingByCategoryEndpoint() throws Exception {
        mockMvc.perform(get("/insights/average-rating-by-category")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Electronics", is(4.0)));
    }

    @Test
    void testTopProductByCategoryEndpoint() throws Exception {
        mockMvc.perform(get("/insights/top-by-category")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Electronics.rating.rate", is(4.5)));
    }

    @Test
    void testAveragePricePerProductEndpoint() throws Exception {
        mockMvc.perform(get("/insights/historical/average-price")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
