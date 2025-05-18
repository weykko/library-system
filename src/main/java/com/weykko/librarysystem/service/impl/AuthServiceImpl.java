package com.weykko.librarysystem.service.impl;

import com.weykko.librarysystem.dto.auth.RegisterRequest;
import com.weykko.librarysystem.dto.auth.RegisterResponse;
import com.weykko.librarysystem.entity.UserEntity;
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

    @Transactional
    @Override
    public RegisterResponse register(RegisterRequest request) {
        //TODO: добавить проверку по email
        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(request.getEmail());
        userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        userEntity.setFirstName(request.getFirstName());
        userEntity.setLastName(request.getLastName());
        userEntity.setBirthDate(request.getBirthDate());
        userEntity.setPhoneNumber(request.getPhoneNumber());

        userRepository.save(userEntity);

        // 🐘 Заглушка
        //TODO: переделать
        return new RegisterResponse();
    }
}
