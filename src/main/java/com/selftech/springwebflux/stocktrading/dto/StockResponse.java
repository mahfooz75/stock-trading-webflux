package com.selftech.springwebflux.stocktrading.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockResponse {
    private String id;
    @JsonProperty("stockName")
    private String name;
    private BigDecimal price;
    private String currency;
}
