package com.example.weddingofhuongsan.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeddingSpendResponse {
    private Double productSpend;
    private Double MenuSpend;
    private Double totalGift;
}
