package com.selftech.springwebflux.stocktrading.exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(String message) {
        super(message);
    }

    public StockNotFoundException() {
        super();
    }
}
