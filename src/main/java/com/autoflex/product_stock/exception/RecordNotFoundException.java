package com.autoflex.product_stock.exception;


public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String message) {
        super(message);
    }
}
