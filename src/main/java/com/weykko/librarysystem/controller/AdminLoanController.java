package com.weykko.librarysystem.controller;

import com.weykko.librarysystem.dto.loan.BorrowBookRequest;
import com.weykko.librarysystem.dto.loan.LoanResponse;
import com.weykko.librarysystem.dto.loan.ReturnBookRequest;
import com.weykko.librarysystem.dto.loan.ReturnBookResponse;
import com.weykko.librarysystem.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/loans")
public class AdminLoanController {

    private final LoanService loanService;

    @GetMapping
    public List<LoanResponse> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public LoanResponse getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }

    @GetMapping("/overdue")
    public List<LoanResponse> getOverdueLoans() {
        return loanService.getOverdueLoans();
    }

    @PostMapping("/borrow")
    @ResponseStatus(HttpStatus.CREATED)
    public LoanResponse borrowBook(@RequestBody BorrowBookRequest request) {
        return loanService.borrowBook(request);
    }

    @PostMapping("/{id}/return")
    public void returnBook(@PathVariable Long id) {
        loanService.returnBook(id);
    }
}
