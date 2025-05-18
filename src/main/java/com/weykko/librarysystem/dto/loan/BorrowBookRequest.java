package com.weykko.librarysystem.dto.loan;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowBookRequest {

    private Long userId;

    private Long bookId;

    //TODO: разорбраться в каком виде принимать дату
    private LocalDate dueDate;

    private Integer loanLength;
}
