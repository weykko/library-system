package com.weykko.librarysystem.dto.loan;

import com.weykko.librarysystem.entity.LoanEntity;
import com.weykko.librarysystem.entity.enums.LoanStatus;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class LoanResponse {

    private Long loanId;

    private Long userId;

    private Long bookId;

    private LocalDateTime borrowDate;

    private LocalDateTime dueDate;

    private LoanStatus status;

    public static LoanResponse fromEntity(LoanEntity loanEntity) {
        return new LoanResponse(
                loanEntity.getId(),
                loanEntity.getUser().getId(),
                loanEntity.getBook().getId(),
                loanEntity.getBorrowDate(),
                loanEntity.getDueDate(),
                loanEntity.getStatus()
        );
    }
}
