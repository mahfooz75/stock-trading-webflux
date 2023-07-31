package com.selftech.springwebflux.stocktrading.service;

import com.selftech.springwebflux.stocktrading.dto.CarRequest;
import com.selftech.springwebflux.stocktrading.dto.CarResponse;
import com.selftech.springwebflux.stocktrading.mapper.CarMapper;
import com.selftech.springwebflux.stocktrading.model.Car;
import com.selftech.springwebflux.stocktrading.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public Flux<CarResponse> addAll(List<CarRequest> carRequest) {
        Flux<CarRequest> carRequestFlux = Flux.fromIterable(carRequest);
        Mono<List<Car>> carMono = carRequestFlux.map(carMapper::toModel).collect(Collectors.toList());
        Flux<Car> cars = carMono.flatMapMany(Flux::fromIterable);
        return carRepository.saveAll(cars).map(carMapper::fromModel);
    }

    public Mono<CarResponse> add(CarRequest carRequest) {
        return carRepository.insert(carMapper.toModel(carRequest)).map(carMapper::fromModel);
    }

    public Flux<CarResponse> getAllCars() {
        return carRepository.findAll().map(carMapper::fromModel);
    }

    public Mono<CarResponse> getCarByVin(String vin) {
        return carRepository.findByCarVin(vin).map(carMapper::fromModel);
    }
}
