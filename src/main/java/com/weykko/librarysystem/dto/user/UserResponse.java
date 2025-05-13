package com.weykko.librarysystem.dto.user;

import com.weykko.librarysystem.entity.enums.UserRole;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String email;

    private String phoneNumber;

    private UserRole role;
}
