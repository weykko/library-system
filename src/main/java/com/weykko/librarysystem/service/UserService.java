package com.weykko.librarysystem.service;

import com.weykko.librarysystem.dto.user.UserRequest;
import com.weykko.librarysystem.dto.user.UserResponse;

public interface UserService {

    UserResponse getUser(Long id);

    UserResponse updateUser(Long id, UserRequest request);

    void deleteUser(Long id);
}
