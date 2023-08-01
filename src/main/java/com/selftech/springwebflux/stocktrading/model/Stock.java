package com.selftech.springwebflux.stocktrading.model;

import com.mongodb.lang.NonNull;
import com.selftech.springwebflux.stocktrading.constants.CollectionName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = CollectionName.STOCK)
public class Stock {
    private String id;
    private String name;
    @NonNull
    private BigDecimal price;
    private String currency;
    private String symbol;
    private String sector;
}
