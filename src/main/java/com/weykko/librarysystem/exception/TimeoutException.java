package com.weykko.librarysystem.exception;

public class TimeoutException extends RuntimeException {
    public TimeoutException(Long interval) {
        super("Method can only be called once every " + interval + " seconds");
    }
}
