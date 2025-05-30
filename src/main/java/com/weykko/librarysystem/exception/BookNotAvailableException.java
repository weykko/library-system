package com.weykko.librarysystem.exception;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(Long id) {
        super("Book with ID: " + id + " is currently unavailable");
    }
}
