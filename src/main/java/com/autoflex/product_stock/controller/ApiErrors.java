package com.autoflex.product_stock.controller;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {
    private List<String> errors;

    public ApiErrors(String messageErrors){
        this.errors = Arrays.asList(messageErrors);
    }

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
