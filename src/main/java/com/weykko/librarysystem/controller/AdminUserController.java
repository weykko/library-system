package com.weykko.librarysystem.controller;

import com.weykko.librarysystem.dto.loan.LoanResponse;
import com.weykko.librarysystem.dto.user.DeleteUserResponse;
import com.weykko.librarysystem.dto.user.UserRequest;
import com.weykko.librarysystem.dto.user.UserResponse;
import com.weykko.librarysystem.service.LoanService;
import com.weykko.librarysystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final UserService userService;
    private final LoanService loanService;

    // TODO: реализовать пагинацию при поиске
    @GetMapping
    public List<UserResponse> getUsers(@RequestParam String q) {
        return List.of();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    // Позже этот эндпоинт возможно перестанет быть нужен
    // Появится общий поиск займов c фильтрами
    // GET /api/admin/loans?userId=5&status=OVERDUE
    @GetMapping("/{id}/loans")
    public List<LoanResponse> getUserLoans(@PathVariable Long id) {
        return loanService.getUserLoans(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
