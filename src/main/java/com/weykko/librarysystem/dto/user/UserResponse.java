package com.weykko.librarysystem.dto.user;

import com.weykko.librarysystem.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String phoneNumber;

    private UserRole role;
}
