package com.selftech.springwebflux.stocktrading.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarRequest {
    private String carName;
    private String carModel;
    private String carModelYear;
    private String carVin;
}
