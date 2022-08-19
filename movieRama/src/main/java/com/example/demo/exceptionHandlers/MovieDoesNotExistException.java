package com.example.demo.exceptionHandlers;

public class MovieDoesNotExistException extends Throwable {
    public MovieDoesNotExistException(String s){
        super(s);
    }
}
