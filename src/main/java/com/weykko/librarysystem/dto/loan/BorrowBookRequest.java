package com.weykko.librarysystem.dto.loan;

import java.time.LocalDate;

public class BorrowBookRequest {

    private Long userId;

    private Long bookId;

    private LocalDate dueDate;
}
