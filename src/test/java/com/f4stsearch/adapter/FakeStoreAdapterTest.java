package com.f4stsearch.adapter;

import com.f4stsearch.adapter.out.FakeStoreAdapter;
import com.f4stsearch.adapter.out.FakeStoreClient;
import com.f4stsearch.adapter.out.FakeStoreProductDTO;
import com.f4stsearch.domain.model.Product;
import com.f4stsearch.domain.model.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FakeStoreAdapterTest {

    private FakeStoreClient client;
    private FakeStoreAdapter adapter;

    @BeforeEach
    void setUp() {
        client = mock(FakeStoreClient.class);
        adapter = new FakeStoreAdapter(client);
    }

    @Test
    void givenProductsFromClient_whenFindAll_thenReturnMappedProducts() {
        FakeStoreProductDTO dto = new FakeStoreProductDTO();
        dto.setId(1L);
        dto.setTitle("Test Product");
        dto.setPrice(99.99);
        dto.setDescription("Test Description");
        dto.setCategory("electronics");
        dto.setImage("url");
        dto.setRating(new Rating(4.5, 100));

        when(client.getAll()).thenReturn(List.of(dto));

        List<Product> result = adapter.findAll();

        assertEquals(1, result.size());
        Product product = result.getFirst();
        assertEquals(dto.getId(), product.getId());
        assertEquals(dto.getTitle(), product.getTitle());
        assertEquals(dto.getPrice(), product.getPrice());
        assertEquals(dto.getCategory(), product.getCategory());
        assertEquals(dto.getRating().getRate(), product.getRating().getRate());
        assertEquals(dto.getRating().getCount(), product.getRating().getCount());
        assertTrue(product.getLink().contains("/products/1"));
    }

    @Test
    void givenProductFromClient_whenFindById_thenReturnOptionalProduct() {
        FakeStoreProductDTO dto = new FakeStoreProductDTO();
        dto.setId(2L);
        dto.setTitle("Another Product");
        dto.setPrice(49.99);
        dto.setDescription("Another Description");
        dto.setCategory("clothing");
        dto.setImage("image");
        dto.setRating(new Rating(3.5, 50));

        when(client.byId(2L)).thenReturn(dto);

        Optional<Product> result = adapter.findById(2L);

        assertTrue(result.isPresent());
        Product product = result.get();
        assertEquals(dto.getId(), product.getId());
        assertEquals(dto.getTitle(), product.getTitle());
        assertEquals(dto.getPrice(), product.getPrice());
        assertEquals(dto.getRating().getRate(), product.getRating().getRate());
        assertEquals(dto.getRating().getCount(), product.getRating().getCount());
        assertEquals("https://fakestoreapi.com/products/2", product.getLink());
    }
}
