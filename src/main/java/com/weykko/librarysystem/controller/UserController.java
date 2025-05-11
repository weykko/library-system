package com.weykko.librarysystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    @PutMapping("/update")
    public void updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {

    }

    @GetMapping("/history")
    public void getHistory() {

    }
}
