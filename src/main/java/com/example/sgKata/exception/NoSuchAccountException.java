package com.example.sgKata.exception;

public class NoSuchAccountException extends RuntimeException{

    public NoSuchAccountException(String message) {
        super("Non existing account with id: " + message);
    }
}
