package com.weykko.librarysystem.controller;

import com.weykko.librarysystem.dto.loan.LoanResponse;
import com.weykko.librarysystem.dto.user.UserRequest;
import com.weykko.librarysystem.dto.user.UserResponse;
import com.weykko.librarysystem.security.UserDetailsImpl;
import com.weykko.librarysystem.service.LoanService;
import com.weykko.librarysystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final LoanService loanService;

    //Эндпоинта пока не актуальны, так как аутентификация пока не настроена

    @GetMapping
    public UserResponse getUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.getUserById(userDetails.getId());
    }

    @PatchMapping("/update")
    public UserResponse updateUser(@RequestBody UserRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.updateUser(userDetails.getId(), request);
    }

    @GetMapping("/loans")
    public List<LoanResponse> getLoans(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return loanService.getUserLoans(userDetails.getId());
    }
}
