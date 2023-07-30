package com.selftech.springwebflux.stocktrading.mapper;

import com.selftech.springwebflux.stocktrading.dto.CarRequest;
import com.selftech.springwebflux.stocktrading.dto.CarResponse;
import com.selftech.springwebflux.stocktrading.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarMapper {

    Car toModel(CarRequest carRequest);

    CarResponse fromModel(Car car);

}
