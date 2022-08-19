package com.example.demo.exceptionHandlers;

public class SameActionByUserException extends Throwable {
    public SameActionByUserException(String s) {
        super(s);
    }
}
