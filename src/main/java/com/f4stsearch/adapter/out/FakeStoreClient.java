package com.f4stsearch.adapter.out;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "fakestore", url = "https://fakestoreapi.com")
public interface FakeStoreClient {
    @GetMapping("/products")
    List<FakeStoreProductDTO> getAll();

    @GetMapping("/products/{id}")
    FakeStoreProductDTO byId(@PathVariable Long id);
}