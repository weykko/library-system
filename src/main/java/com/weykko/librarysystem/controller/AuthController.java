package com.weykko.librarysystem.controller;

import com.weykko.librarysystem.dto.auth.LoginRequest;
import com.weykko.librarysystem.dto.auth.AuthResponse;
import com.weykko.librarysystem.dto.auth.RegisterRequest;
import com.weykko.librarysystem.dto.user.UserResponse;
import com.weykko.librarysystem.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody @Valid RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }
}
