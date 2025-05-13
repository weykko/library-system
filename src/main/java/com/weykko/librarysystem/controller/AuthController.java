package com.weykko.librarysystem.controller;

import com.weykko.librarysystem.dto.auth.LoginRequest;
import com.weykko.librarysystem.dto.auth.LoginResponse;
import com.weykko.librarysystem.dto.auth.RegisterRequest;
import com.weykko.librarysystem.dto.auth.RegisterResponse;
import com.weykko.librarysystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
    }
}
