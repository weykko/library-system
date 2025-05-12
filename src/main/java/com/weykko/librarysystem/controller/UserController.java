package com.weykko.librarysystem.controller;

import com.weykko.librarysystem.dto.user.UserRequest;
import com.weykko.librarysystem.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping
    public UserResponse getUser(@AuthenticationPrincipal UserDetails userDetails) {

    }

    @PutMapping("/update")
    public UserResponse updateUser(@RequestBody UserRequest request, @AuthenticationPrincipal UserDetails userDetails) {

    }

    @GetMapping("/loans")
    public List<LoanResponse> getLoans(@AuthenticationPrincipal UserDetails userDetails) {

    }
}
