package com.weykko.librarysystem.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequest {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String email;

    private String phoneNumber;

    private String password;
}
