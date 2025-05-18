package com.weykko.librarysystem.exception;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(Long id) {
        super("Книга с ID" + id + "недоступна");
    }
}
