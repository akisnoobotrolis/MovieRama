package com.example.demo.exceptionHandlers;

public class SameVoterAndCreatorException extends Throwable {
    public SameVoterAndCreatorException(String s){
        super(s);
    }
}
