package com.selftech.springwebflux.stocktrading.service;

import com.selftech.springwebflux.stocktrading.dto.StockRequest;
import com.selftech.springwebflux.stocktrading.dto.StockResponse;
import com.selftech.springwebflux.stocktrading.exception.StockCreationException;
import com.selftech.springwebflux.stocktrading.exception.StockNotFoundException;
import com.selftech.springwebflux.stocktrading.mapper.StockMapper;
import com.selftech.springwebflux.stocktrading.model.Stock;
import com.selftech.springwebflux.stocktrading.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockService {
    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    public Mono<StockResponse> getOneStock(String id) {
        return stockRepository.findById(id).map(stockMapper::fromModel)
                .switchIfEmpty(Mono.error(new StockNotFoundException("Stock not found with id " + id)));
    }

    public Mono<StockResponse> getOneStockAlternate(String id) {
        return stockRepository.findById(id)
                .map(stockMapper::fromModel)
                .switchIfEmpty(Mono.error(new StockNotFoundException("Stock not found with id " + id)))
                .doFirst(() -> log.info("Retrieving stock with id: {}", id))
                .doOnNext(stock -> log.info("Stock found: {}", stock))
                .doOnError(ex -> log.error("Something went wrong while retrieving the stock with id: {}", id, ex))
                .doOnTerminate(() -> log.info("Finalized retrieving stock"))
                .doFinally(signalType -> log.info("Finalized retrieving stock with signal type: {}", signalType));
    }

    public Flux<StockResponse> getAllStockGreaterThan(BigDecimal priceGreaterThan) {
        return stockRepository.findAll().filter(s -> s.getPrice().compareTo(priceGreaterThan) > 0)
                .map(stockMapper::fromModel);
    }

    public Mono<StockResponse> createStock(StockRequest stockRequest) {
        return Mono.just(stockRequest)
                .map(stockMapper::toModel)
                .flatMap(stockRepository::save)
                .map(stockMapper::fromModel)
                //.onErrorReturn(StockResponse.builder().build());
                /*.onErrorResume(ex->{
                    log.error("Error {}",ex.getMessage());
                    return Mono.just(StockResponse.builder().build());
                });*/
                .onErrorMap(ex -> new StockCreationException(ex.getMessage()));
        /*return stockRepository.save(stockMapper.toModel(stockRequest)).map(stockMapper::fromModel)
                .onErrorReturn(StockResponse.builder().build());*/
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
