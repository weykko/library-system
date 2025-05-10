package com.weykko.librarysystem.controller;

import com.weykko.librarysystem.dto.BookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/borrow")
public class BorrowController {

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void borrowBook(@RequestBody BookRequest borrowRequest) {

    }

    @PutMapping("/return")
    public void returnBook(@RequestBody BookRequest returnRequest) {

    }
}
