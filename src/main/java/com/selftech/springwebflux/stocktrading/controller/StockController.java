package com.selftech.springwebflux.stocktrading.controller;

import com.selftech.springwebflux.stocktrading.dto.StockRequest;
import com.selftech.springwebflux.stocktrading.dto.StockResponse;
import com.selftech.springwebflux.stocktrading.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/{id}")
    public Mono<StockResponse> getAllStock(@PathVariable String id) {
        return stockService.getOneStock(id);
    }

    @GetMapping("/greaterThan")
    public Flux<StockResponse> getAllStockGreaterThan(@RequestParam(required = false, defaultValue = "0")
                                               BigDecimal priceGreaterThan) {
        return stockService.getAllStockGreaterThan(priceGreaterThan);
    }

    @GetMapping
    public Flux<StockResponse> getAllStock() {
        return stockService.getAllStock();
    }

    @PostMapping
    public Mono<StockResponse> createStock(@RequestBody StockRequest stockRequest) {
        return stockService.createStock(stockRequest);
    }

    @PostMapping("/addAll")
    public Flux<StockResponse> addAll(@RequestBody List<StockRequest> stockRequests) {
        return stockService.addAll(stockRequests);
    }
}
