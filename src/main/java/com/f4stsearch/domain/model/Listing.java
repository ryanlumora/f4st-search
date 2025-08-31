package com.f4stsearch.domain.model;

import lombok.*;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Listing {
    private String id;
    private String productId;
    private String title;
    private double price;
    private String currency;
    private String url;
    private OffsetDateTime timestamp;
}
