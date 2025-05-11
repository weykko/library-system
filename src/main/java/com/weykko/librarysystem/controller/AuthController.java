package com.weykko.librarysystem.controller;

import com.weykko.librarysystem.dto.LoginRequest;
import com.weykko.librarysystem.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public/auth")
public class AuthController {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterRequest registerRequest) {
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest) {
    }
}
