package com.weykko.librarysystem.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequest {
    @Email(message = "Invalid email format")
    private String email;

    private String firstName;

    private String lastName;

    @Past(message = "Birth date must be correct")
    private LocalDate birthDate;

    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "Invalid phone number format")
    private String phoneNumber;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
