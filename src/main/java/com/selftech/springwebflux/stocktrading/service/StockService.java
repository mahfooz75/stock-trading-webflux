package com.selftech.springwebflux.stocktrading.service;

import com.selftech.springwebflux.stocktrading.dto.StockRequest;
import com.selftech.springwebflux.stocktrading.dto.StockResponse;
import com.selftech.springwebflux.stocktrading.mapper.StockMapper;
import com.selftech.springwebflux.stocktrading.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public Mono<StockResponse> getOneStock(String id) {
        return stockRepository.findById(id).map(stockMapper::fromModel);
    }

    public Flux<StockResponse> getAllStock() {
        return stockRepository.findAll().map(stockMapper::fromModel);
    }

    public Mono<StockResponse> createStock(StockRequest stockRequest) {
        return stockRepository.save(stockMapper.toModel(stockRequest)).map(stockMapper::fromModel);
    }
}
