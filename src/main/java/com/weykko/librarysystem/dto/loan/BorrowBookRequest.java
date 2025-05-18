package com.weykko.librarysystem.dto.loan;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowBookRequest {

    private Long userId;

    private Long bookId;

    private LocalDate dueDate;
}
