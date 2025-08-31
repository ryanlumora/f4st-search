package com.f4stsearch.application.controller;

import com.f4stsearch.domain.model.Product;
import com.f4stsearch.domain.port.client.ProductSearchPort;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SearchController.class)
public class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ProductSearchPort productSearchPort() {
            return Mockito.mock(ProductSearchPort.class);
        }
    }

    @Autowired
    private ProductSearchPort productSearchPort;

    @Test
    void searchProducts_shouldReturnJson() throws Exception {
        when(productSearchPort.search("notebook"))
                .thenReturn(List.of(new Product("MLB123", "Notebook", 3000.0, "link")));

        mockMvc.perform(get("/products/search").param("query", "notebook"))
                .andExpect(status().isOk());
    }

    @Test
    void searchProducts_emptyQuery_shouldReturnEmptyList() throws Exception {
        when(productSearchPort.search(""))
                .thenReturn(List.of());

        mockMvc.perform(get("/products/search")
                        .param("query", ""))
                .andExpect(status().isOk());
    }
}
