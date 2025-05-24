package com.weykko.librarysystem.service;

import com.weykko.librarysystem.dto.loan.BorrowRequest;
import com.weykko.librarysystem.dto.loan.LoanResponse;

import java.util.List;

public interface LoanService {
    LoanResponse borrowBook(BorrowRequest request);

    void returnBook(Long id);

    LoanResponse getLoanById(Long id);

    List<LoanResponse> getAllLoans();

    List<LoanResponse> getOverdueLoans();

    List<LoanResponse> getUserLoans(Long id);
}
