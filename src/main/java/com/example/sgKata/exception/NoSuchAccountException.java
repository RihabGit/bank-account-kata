package com.example.sgKata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchAccountException extends RuntimeException{

    public NoSuchAccountException(String message) {
        super("Non existing account with id: " + message);
    }
}
