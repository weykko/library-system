package com.weykko.librarysystem.service;

import com.weykko.librarysystem.dto.auth.RegisterRequest;
import com.weykko.librarysystem.dto.auth.RegisterResponse;
import com.weykko.librarysystem.entity.UserEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        UserEntity userEntity = new UserEntity();


    }
}
