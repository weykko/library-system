package com.weykko.librarysystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/loans")
public class AdminLoanController {

    @PostMapping("/borrow")
    @ResponseStatus(HttpStatus.CREATED)
    public void borrowBook(@RequestBody BorrowRequest borrowRequest) {

    }

    @PostMapping("/return")
    public void returnBook(@RequestBody ReturnRequest returnRequest) {

    }
}
