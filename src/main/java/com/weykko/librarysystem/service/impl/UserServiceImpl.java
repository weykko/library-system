package com.weykko.librarysystem.service.impl;

import com.weykko.librarysystem.dto.user.UserRequest;
import com.weykko.librarysystem.dto.user.UserResponse;
import com.weykko.librarysystem.entity.UserEntity;
import com.weykko.librarysystem.exception.EmailAlreadyUsed;
import com.weykko.librarysystem.exception.UserNotFoundException;
import com.weykko.librarysystem.repository.UserRepository;
import com.weykko.librarysystem.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse getUser(Long id) {
        return userRepository.findById(id)
                .map(UserResponse::fromEntity)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        updateField(userEntity.getFirstName(), request.getFirstName(), userEntity::setFirstName);
        updateField(userEntity.getLastName(), request.getLastName(), userEntity::setLastName);
        updateField(userEntity.getBirthDate(), request.getBirthDate(), userEntity::setBirthDate);
        updateField(userEntity.getPhoneNumber(), request.getPhoneNumber(), userEntity::setPhoneNumber);

        if (userRepository.existsByEmail(request.getEmail())) throw new EmailAlreadyUsed(request.getEmail());
        updateField(userEntity.getEmail(), request.getEmail(), userEntity::setFirstName);

        if (request.getPassword() != null  && !request.getPassword().isBlank()) {
            updateField(
                    userEntity.getPassword(),
                    passwordEncoder.encode(request.getPassword()),
                    userEntity::setPassword
            );
        }

        return UserResponse.fromEntity(userEntity);
    }

    //TODO: реализовать soft delete
    @Transactional
    @Override
    public void deleteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userRepository.delete(userEntity);
    }

    private <T> void updateField(T currentValue, T newValue, Consumer<T> setter) {
        if (newValue != null && !Objects.equals(currentValue, newValue)) {
            setter.accept(newValue);
        }
    }
}
