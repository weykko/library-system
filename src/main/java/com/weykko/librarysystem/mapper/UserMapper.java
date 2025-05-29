package com.weykko.librarysystem.mapper;

import com.weykko.librarysystem.dto.auth.RegisterRequest;
import com.weykko.librarysystem.dto.user.UserResponse;
import com.weykko.librarysystem.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(RegisterRequest request) {
        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(request.getEmail());
        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity.setBirthDate(request.getBirthDate());
        userEntity.setPhoneNumber(request.getPhoneNumber());

        return userEntity;
    }

    public UserResponse toResponse(UserEntity userEntity) {
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
