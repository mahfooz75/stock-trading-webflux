package com.selftech.springwebflux.stocktrading.service;

import com.selftech.springwebflux.stocktrading.model.Stock;
import com.selftech.springwebflux.stocktrading.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public Mono<Stock> getOneStock(String id) {
        return stockRepository.findById(id);
    }

    public Flux<Stock> getAllStock() {
        return stockRepository.findAll();
    }

    public Mono<Stock> createStock(Stock stock) {
        return stockRepository.save(stock);
    }
}
