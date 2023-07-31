package com.selftech.springwebflux.stocktrading.service;

import com.selftech.springwebflux.stocktrading.dto.StockRequest;
import com.selftech.springwebflux.stocktrading.dto.StockResponse;
import com.selftech.springwebflux.stocktrading.mapper.StockMapper;
import com.selftech.springwebflux.stocktrading.model.Stock;
import com.selftech.springwebflux.stocktrading.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public Mono<StockResponse> getOneStock(String id) {
        return stockRepository.findById(id).map(stockMapper::fromModel);
    }

    public Flux<StockResponse> getAllStockGreaterThan(BigDecimal priceGreaterThan) {
        return stockRepository.findAll().filter(s -> s.getPrice().compareTo(priceGreaterThan) > 0)
                .map(stockMapper::fromModel);
    }

    public Mono<StockResponse> createStock(StockRequest stockRequest) {
        return stockRepository.save(stockMapper.toModel(stockRequest)).map(stockMapper::fromModel);
    }

    public Flux<StockResponse> addAll(List<StockRequest> stockRequests) {
        Flux<StockRequest> stockRequestFlux = Flux.fromIterable(stockRequests);
        Mono<List<Stock>> stockMono = stockRequestFlux.map(stockMapper::toModel).collect(Collectors.toList());
        Flux<Stock> stocks = stockMono.flatMapMany(Flux::fromIterable);
        return stockRepository.saveAll(stocks).map(stockMapper::fromModel);
    }

    public Flux<StockResponse> getAllStock() {
        return stockRepository.findAll().map(stockMapper::fromModel);
    }
}
