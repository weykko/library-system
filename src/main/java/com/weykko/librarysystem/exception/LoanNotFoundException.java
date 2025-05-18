package com.weykko.librarysystem.exception;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(Long id) {
        super("Заем с ID: " + id + " не найден");
    }
}
