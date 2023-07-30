package com.selftech.springwebflux.stocktrading.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockRequest {
    private String name;
    private BigDecimal price;
    private String currency;
    private String symbol;
    private String sector;
}
