package com.selftech.springwebflux.stocktrading.mapper;

import com.selftech.springwebflux.stocktrading.dto.StockRequest;
import com.selftech.springwebflux.stocktrading.dto.StockResponse;
import com.selftech.springwebflux.stocktrading.model.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StockMapper {

    Stock toModel(StockRequest stockRequest);

    StockResponse fromModel(Stock stock);
}
