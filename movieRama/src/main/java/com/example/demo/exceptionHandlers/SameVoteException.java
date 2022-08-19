package com.example.demo.exceptionHandlers;

public class SameVoteException extends Throwable {
    public SameVoteException(String s) {
        super(s);
    }
}
