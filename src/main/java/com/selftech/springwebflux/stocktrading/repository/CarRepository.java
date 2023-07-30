package com.selftech.springwebflux.stocktrading.repository;

import com.selftech.springwebflux.stocktrading.model.Car;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends ReactiveMongoRepository<Car, String> {
}
