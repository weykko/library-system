package com.weykko.librarysystem.service;

import com.weykko.librarysystem.dto.loan.BorrowBookRequest;
import com.weykko.librarysystem.dto.loan.LoanResponse;

import java.util.List;

public interface LoanService {
    LoanResponse borrowBook(BorrowBookRequest request);

    void returnBook(Long id);

    LoanResponse getLoanById(Long id);

    List<LoanResponse> getAllLoans();

    List<LoanResponse> getOverdueLoans();

    List<LoanResponse> getUserLoans(Long id);
}
