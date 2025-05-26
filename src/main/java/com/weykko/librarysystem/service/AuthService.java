package com.weykko.librarysystem.service;

import com.weykko.librarysystem.dto.auth.LoginRequest;
import com.weykko.librarysystem.dto.auth.LoginResponse;
import com.weykko.librarysystem.dto.auth.RegisterRequest;
import com.weykko.librarysystem.dto.auth.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
