package com.selftech.springwebflux.stocktrading.repository;

import com.selftech.springwebflux.stocktrading.model.Car;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CarRepository extends ReactiveMongoRepository<Car, String> {
    Mono<Car> findByCarVin(String vin);

}
