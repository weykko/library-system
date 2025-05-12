package com.weykko.librarysystem.controller;

import com.weykko.librarysystem.dto.user.UserRequest;
import com.weykko.librarysystem.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    // TODO: реализовать пагинацию при поиске
    @GetMapping
    public List<UserResponse> getUsers(@RequestParam String q) {

    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {

    }

    @GetMapping("/{id}/loans")
    public List<LoanResponse> getUserLoans(@PathVariable Long id) {

    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest request) {

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public DeleteUserResponse deleteUser(@PathVariable Long id) {

    }
}
