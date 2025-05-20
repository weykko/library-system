package com.weykko.librarysystem.service.impl;

import com.weykko.librarysystem.dto.loan.LoanResponse;
import com.weykko.librarysystem.dto.user.UserRequest;
import com.weykko.librarysystem.dto.user.UserResponse;
import com.weykko.librarysystem.entity.UserEntity;
import com.weykko.librarysystem.exception.LoanNotFoundException;
import com.weykko.librarysystem.exception.UserNotFoundException;
import com.weykko.librarysystem.repository.UserRepository;
import com.weykko.librarysystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse getUser(Long id) {
        return userRepository.findById(id)
                .map(UserResponse::fromEntity)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));



        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
