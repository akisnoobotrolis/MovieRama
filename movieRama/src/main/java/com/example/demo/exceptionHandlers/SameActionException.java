package com.example.demo.exceptionHandlers;

public class SameActionException extends Throwable {
    public SameActionException(String s) {
        super(s);
    }
}
