package com.f4stsearch.adapter.client;

import com.f4stsearch.adapter.client.dto.MercadoLivreItemDTO;
import com.f4stsearch.domain.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MercadoLivreAdapterTest {

    @Mock
    private MercadoLivreClient client;

    @InjectMocks
    private MercadoLivreAdapter adapter;

    @Test
    void searchProducts_shouldReturnProducts() {
        MercadoLivreItemDTO product = new MercadoLivreItemDTO("MLB123", "Notebook", 3000.0, "link");
        List<MercadoLivreItemDTO> response = List.of(product);

        Mockito.when(client.searchProducts("notebook", 20))
                .thenReturn(response);

        List<Product> result = adapter.search("notebook");

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Notebook", result.getFirst().getTitle());
    }
}
