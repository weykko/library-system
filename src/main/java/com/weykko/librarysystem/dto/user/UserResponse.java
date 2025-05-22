package com.weykko.librarysystem.dto.user;

import com.weykko.librarysystem.dto.loan.LoanResponse;
import com.weykko.librarysystem.entity.UserEntity;
import com.weykko.librarysystem.entity.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String email;

    private String phoneNumber;

    private UserRole role;

    public static UserResponse fromEntity(UserEntity userEntity) {
        return new UserResponse(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getBirthDate(),
                userEntity.getEmail(),
                userEntity.getPhoneNumber(),
                userEntity.getRole()
        );
    }
}
