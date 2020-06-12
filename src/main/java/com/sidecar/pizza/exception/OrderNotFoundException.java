package com.sidecar.pizza.exception;

public class OrderNotFoundException extends Exception {

    public OrderNotFoundException() {
        super("ORDER_NOT_FOUND");
    }

}
