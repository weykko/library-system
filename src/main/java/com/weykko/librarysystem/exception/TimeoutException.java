package com.weykko.librarysystem.exception;

public class TimeoutException extends RuntimeException {
    public TimeoutException(Long interval) {
        super("Method cannot be called more than once every " + interval + " seconds");
    }
}
