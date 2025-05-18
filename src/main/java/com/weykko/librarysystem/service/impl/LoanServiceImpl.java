package com.weykko.librarysystem.service.impl;

import com.weykko.librarysystem.dto.loan.BorrowBookRequest;
import com.weykko.librarysystem.dto.loan.LoanResponse;
import com.weykko.librarysystem.service.LoanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Override
    public LoanResponse borrowBook(BorrowBookRequest request) {
        return null;
    }

    @Override
    public void returnBook(Long id) {

    }

    @Override
    public LoanResponse getLoanById(Long id) {
        return null;
    }

    @Override
    public List<LoanResponse> getAllLoans() {
        return List.of();
    }

    @Override
    public List<LoanResponse> getOverdueLoans() {
        return List.of();
    }

    @Override
    public List<LoanResponse> getUserLoans(Long id) {
        return List.of();
    }
}
