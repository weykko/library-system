package com.weykko.librarysystem.exception;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String email) {
        super("Электронная почта: " + email + " уже используется");
    }
}
