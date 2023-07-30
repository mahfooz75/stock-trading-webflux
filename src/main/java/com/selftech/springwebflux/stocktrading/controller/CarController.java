package com.selftech.springwebflux.stocktrading.controller;

import com.selftech.springwebflux.stocktrading.dto.CarRequest;
import com.selftech.springwebflux.stocktrading.dto.CarResponse;
import com.selftech.springwebflux.stocktrading.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @PostMapping("/addAll")
    public Flux<CarResponse> addAll(@RequestBody List<CarRequest> cars) {
        return carService.addAll(cars);
    }

    @PostMapping("/add")
    public Mono<CarResponse> addOne(@RequestBody CarRequest car) {
        return carService.add(car);
    }
}
