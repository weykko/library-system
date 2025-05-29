package com.weykko.librarysystem.service.impl;

import com.weykko.librarysystem.dto.auth.LoginRequest;
import com.weykko.librarysystem.dto.auth.LoginResponse;
import com.weykko.librarysystem.dto.auth.RegisterRequest;
import com.weykko.librarysystem.dto.auth.RegisterResponse;
import com.weykko.librarysystem.entity.UserEntity;
import com.weykko.librarysystem.mapper.UserMapper;
import com.weykko.librarysystem.repository.UserRepository;
import com.weykko.librarysystem.service.AuthService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public RegisterResponse register(RegisterRequest request) {
        //TODO: добавить проверку по email
        UserEntity userEntity = userMapper.toEntity(request);
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(userEntity);

        // 🐘 Заглушка
        //TODO: переделать
        return new RegisterResponse();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        return null;
    }
}
