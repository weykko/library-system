package com.weykko.librarysystem.dto.loan;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Book ID is required")
    private Long bookId;

//    TODO: разорбраться в каком виде принимать дату
//    private LocalDate dueDate;

    @NotNull(message = "Loan length is required")
    @Min(value = 1, message = "Loan length must be at least 1 day")
    private Integer loanLength;
}
