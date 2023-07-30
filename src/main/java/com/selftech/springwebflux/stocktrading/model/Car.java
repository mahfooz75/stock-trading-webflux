package com.selftech.springwebflux.stocktrading.model;

import com.selftech.springwebflux.stocktrading.constants.CollectionName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = CollectionName.CAR)
public class Car {
    private String id;
    private String carName;
    private String carModel;
    private String carModelYear;
    private String carVin;
}
