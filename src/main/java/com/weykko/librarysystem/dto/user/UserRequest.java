package com.weykko.librarysystem.dto.user;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequest {

    private String email;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String phoneNumber;

    private String password;
}
