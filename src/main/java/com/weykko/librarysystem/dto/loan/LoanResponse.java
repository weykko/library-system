package com.weykko.librarysystem.dto.loan;

import com.weykko.librarysystem.entity.LoanEntity;
import com.weykko.librarysystem.entity.enums.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LoanResponse {

    private Long loanId;

    private Long userId;

    private Long bookId;

    private LocalDateTime borrowDate;

    private LocalDateTime dueDate;

    private LoanStatus status;


}
