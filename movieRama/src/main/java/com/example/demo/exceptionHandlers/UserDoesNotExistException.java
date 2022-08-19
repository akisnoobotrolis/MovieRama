package com.example.demo.exceptionHandlers;

public class UserDoesNotExistException extends Throwable {
    public UserDoesNotExistException(String s) {
        super(s);
    }
}
