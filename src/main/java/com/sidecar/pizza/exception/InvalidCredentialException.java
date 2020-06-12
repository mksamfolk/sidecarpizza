package com.sidecar.pizza.exception;

public class InvalidCredentialException extends Exception {

    public InvalidCredentialException() {
        super("INVALID_CREDENTIALS");
    }

}
