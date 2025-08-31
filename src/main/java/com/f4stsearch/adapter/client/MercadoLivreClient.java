package com.f4stsearch.adapter.client;

import com.f4stsearch.adapter.client.dto.MercadoLivreItemDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;
import java.util.Map;

@FeignClient(name = "mercado-livre", url = "https://api.mercadolibre.com")
public interface MercadoLivreClient {
    @GetMapping("/sites/MLB/search")
    List<MercadoLivreItemDTO> searchProducts(@RequestParam("q") String query,
                                                 @RequestParam(value = "limit", defaultValue = "20") int limit);
}