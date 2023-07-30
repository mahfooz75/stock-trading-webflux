package com.selftech.springwebflux.stocktrading.repository;

import com.selftech.springwebflux.stocktrading.model.Stock;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends ReactiveMongoRepository<Stock, String> {
}
