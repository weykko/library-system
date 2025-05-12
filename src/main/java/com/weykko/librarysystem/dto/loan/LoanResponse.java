package com.weykko.librarysystem.dto.loan;

import com.weykko.librarysystem.entity.enums.LoanStatus;

import java.time.LocalDate;

public class LoanResponse {

    private Long LoanId;

    private Long userId;

    private Long bookId;

    private LocalDate borrowDate;

    private LocalDate dueDate;

    private LoanStatus status;
}
