package com.weykko.librarysystem.controller;

import com.weykko.librarysystem.dto.loan.BorrowBookRequest;
import com.weykko.librarysystem.dto.loan.LoanResponse;
import com.weykko.librarysystem.dto.loan.ReturnBookRequest;
import com.weykko.librarysystem.dto.loan.ReturnBookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/loans")
public class AdminLoanController {

    @GetMapping
    public List<LoanResponse> getLoans() {

    }

    @PostMapping("/borrow")
    @ResponseStatus(HttpStatus.CREATED)
    public LoanResponse borrowBook(@RequestBody BorrowBookRequest request) {

    }

    @PostMapping("/return")
    public ReturnBookResponse returnBook(@RequestBody ReturnBookRequest request) {

    }

    @GetMapping("/overdue")
    public List<LoanResponse> getOverdueLoans() {

    }

    @GetMapping("/{id}")
    public LoanResponse getLoanById(@PathVariable Long id) {

    }
}
