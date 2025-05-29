package com.weykko.librarysystem.service.impl;

import com.weykko.librarysystem.dto.auth.LoginRequest;
import com.weykko.librarysystem.dto.auth.AuthResponse;
import com.weykko.librarysystem.dto.auth.RegisterRequest;
import com.weykko.librarysystem.dto.user.UserResponse;
import com.weykko.librarysystem.entity.UserEntity;
import com.weykko.librarysystem.entity.enums.UserRole;
import com.weykko.librarysystem.exception.EmailAlreadyUsedException;
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

    // Решил пока не реализовывать аутентификацию, так как ее не было на курсе
    // TODO: разобраться в теме и реализовать позднее

    @Transactional
    @Override
    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyUsedException(request.getEmail());
        }

        UserEntity userEntity = userMapper.toEntity(request);
        userEntity.setRole(UserRole.ROLE_USER);
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(userEntity);

        return userMapper.toResponse(userEntity);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        return null;
    }
}
