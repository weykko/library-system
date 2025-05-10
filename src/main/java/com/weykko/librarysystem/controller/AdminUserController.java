package com.weykko.librarysystem.controller;

import com.weykko.librarysystem.dto.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    // TODO: реализовать пагинацию при поиске
    @GetMapping
    public List<BookResponse> getUsers(@RequestParam String q) {

    }

    @GetMapping("/{id}")
    public List<BookResponse> getUser(@PathVariable Long id) {

    }

    @PutMapping("/{id}")
    public List<BookResponse> updateUser(@PathVariable Long id) {

    }

    @DeleteMapping("/{id}")
    public List<BookResponse> deleteUser(@PathVariable Long id) {

    }
}
