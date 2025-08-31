package com.f4stsearch.application.service;

import com.f4stsearch.domain.model.Product;
import com.f4stsearch.domain.port.client.ProductSearchPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductSearchServiceTest {

    @Mock
    private ProductSearchPort productSearchPort;

    @InjectMocks
    private ProductSearchService productService;

    @Test
    void searchProducts_shouldReturnList() {
        Product product = new Product("MLB123","Notebook",3000.0,"link");

        Mockito.when(productSearchPort.search("notebook"))
                .thenReturn(List.of(product));

        List<Product> resultado = productService.searchProducts("notebook");

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals("Notebook", resultado.getFirst().getTitle());
    }
}
