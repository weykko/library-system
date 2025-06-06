package com.weykko.librarysystem.service;

import com.weykko.librarysystem.dto.user.UserUpdateRequest;
import com.weykko.librarysystem.dto.user.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(Long id, UserUpdateRequest request);

    void deleteUser(Long id);
}
