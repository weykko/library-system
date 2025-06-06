package com.weykko.librarysystem.controller;

import com.weykko.librarysystem.dto.loan.LoanResponse;
import com.weykko.librarysystem.dto.user.UserUpdateRequest;
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

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // TODO: реализовать пагинацию при поиске
//    @GetMapping
//    public List<UserResponse> getUsers(@RequestParam String q) {
//        return List.of();
//    }

    // Временный метод без поиска
    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getAllUsers();
    }

    // Позже этот эндпоинт возможно перестанет быть нужен
    // Появится общий поиск займов c фильтрами
    // GET /api/admin/loans?userId=5&status=OVERDUE
    @GetMapping("/{id}/loans")
    public List<LoanResponse> getUserLoans(@PathVariable Long id) {
        return loanService.getUserLoans(id);
    }

    @PatchMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
