package com.selftech.springwebflux.stocktrading.controller;

import com.selftech.springwebflux.stocktrading.dto.StockRequest;
import com.selftech.springwebflux.stocktrading.dto.StockResponse;
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
    public Mono<StockResponse> getOneStock(@PathVariable String id) {
        return stockService.getOneStock(id);
    }

    @GetMapping
    public Flux<StockResponse> getOneStock() {
        return stockService.getAllStock();
    }

    @PostMapping
    public Mono<StockResponse> createStock(@RequestBody StockRequest stockRequest) {
        return stockService.createStock(stockRequest);
    }
}
