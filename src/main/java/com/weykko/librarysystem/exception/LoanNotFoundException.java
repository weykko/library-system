package com.weykko.librarysystem.exception;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(Long id) {
        super("Loan with ID: " + id + " could not be found");
    }
}
