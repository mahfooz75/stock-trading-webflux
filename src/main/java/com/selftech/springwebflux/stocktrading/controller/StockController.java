package com.selftech.springwebflux.stocktrading.controller;

import com.selftech.springwebflux.stocktrading.dto.StockRequest;
import com.selftech.springwebflux.stocktrading.model.Stock;
import com.selftech.springwebflux.stocktrading.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/{id}")
    public Mono<Stock> getOneStock(@PathVariable String id) {
        return stockService.getOneStock(id);
    }

    @GetMapping
    public Flux<Stock> getOneStock() {
        return stockService.getAllStock();
    }

    @PostMapping
    public Mono<Stock> createStock(@RequestBody StockRequest stockRequest) {
        return stockService.createStock(stockRequest.toModel());
    }
}
