package com.weykko.librarysystem.mapper;

import com.weykko.librarysystem.dto.loan.BorrowRequest;
import com.weykko.librarysystem.dto.loan.LoanResponse;
import com.weykko.librarysystem.entity.LoanEntity;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public LoanEntity toEntity(BorrowRequest request) {
        return null;
    }

    public LoanResponse toResponse(LoanEntity loanEntity) {
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
