package com.selftech.springwebflux.stocktrading.exception;

public class StockCreationException extends RuntimeException {
    public StockCreationException(String message) {
        super(message);
    }

    public StockCreationException() {
        super();
    }
}
