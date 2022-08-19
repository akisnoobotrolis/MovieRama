package com.example.demo.exceptionHandlers;

public class UserAlreadyExistsException extends Throwable {

    public UserAlreadyExistsException(String s) {
        super(s);
    }
}