package com.weykko.librarysystem.exception;

public class EmailAlreadyUsed extends RuntimeException {
    public EmailAlreadyUsed(String email) {
        super("Электронная почта: " + email + " уже используется");
    }
}
