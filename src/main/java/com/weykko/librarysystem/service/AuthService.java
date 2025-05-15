package com.weykko.librarysystem.service;

import com.weykko.librarysystem.dto.auth.RegisterRequest;
import com.weykko.librarysystem.dto.auth.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest request);
}
