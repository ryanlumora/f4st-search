package com.f4stsearch.adapter.out;

import com.f4stsearch.domain.model.Rating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FakeStoreProductDTO {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String image;
    private Rating rating;
}