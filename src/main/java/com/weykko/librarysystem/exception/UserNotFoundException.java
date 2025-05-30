package com.weykko.librarysystem.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User with ID: " + id + " could not be found");
    }
}
