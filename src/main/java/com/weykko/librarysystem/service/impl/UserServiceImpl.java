package com.weykko.librarysystem.service.impl;

import com.weykko.librarysystem.dto.user.UserRequest;
import com.weykko.librarysystem.dto.user.UserResponse;
import com.weykko.librarysystem.entity.UserEntity;
import com.weykko.librarysystem.exception.UserNotFoundException;
import com.weykko.librarysystem.repository.UserRepository;
import com.weykko.librarysystem.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

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

        updateFieldWithCheck(
                userEntity::getEmail,
                request.getEmail(),
                newEmail -> {
                    if (userRepository.existsByEmail(newEmail)) {
                        throw new ConflictException("blablabla");
                    }
                    userEntity.setEmail(newEmail);
                }
        );
        if (request.getEmail() != null && !userRepository.existsByEmail(request.getEmail())) {
            updateField(userEntity::getFirstName, request.getFirstName(), userEntity::setFirstName);
        }
        else {
            throw new ConflictException("blablabla");
        }

        updateField(userEntity::getFirstName, request.getFirstName(), userEntity::setFirstName);
        updateField(userEntity::getLastName, request.getLastName(), userEntity::setLastName);
        updateField(userEntity::getBirthDate, request.getBirthDate(), userEntity::setBirthDate);
        updateField(userEntity::getPhoneNumber, request.getPhoneNumber(), userEntity::setPhoneNumber);

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            updateField(
                    userEntity::getPassword,
                    passwordEncoder.encode(request.getPassword()),
                    userEntity::setPassword
            );
        }
    }

    private <T> void updateField(Supplier<T> getCurrentValue, T newValue, Consumer<T> setter) {
        if (!Objects.equals(getCurrentValue.get(), newValue)) {
            setter.accept(newValue);
        }
    }

    private <T> void updateFieldWithCheck(Supplier<T> getCurrentValue, T newValue, Consumer<T> setterWithCheck) {
        if (!Objects.equals(getCurrentValue.get(), newValue)) {
            setterWithCheck.accept(newValue);
        }
    }

    @Override
    public void deleteUser(Long id) {

    }
}
