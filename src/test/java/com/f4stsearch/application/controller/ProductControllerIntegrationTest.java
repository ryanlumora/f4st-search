package com.f4stsearch.application.controller;

import com.f4stsearch.domain.model.jpa.ProductCache;
import com.f4stsearch.domain.repository.ProductCacheRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductCacheRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();

        ProductCache p1 = new ProductCache();
        p1.setExternalId(1L);
        p1.setTitle("Test Product 1");
        p1.setPrice(100.0);
        p1.setCategory("Electronics");
        p1.setRatingRate(5.0);
        p1.setRatingCount(10);
        repository.save(p1);
    }

    @Test
    void givenProducts_whenGetAllProducts_thenReturnProducts() throws Exception {
        mockMvc.perform(get("/products/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void givenProductExists_whenGetById_thenReturnProduct() throws Exception {
        mockMvc.perform(get("/products/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Product 1"));
    }
}
