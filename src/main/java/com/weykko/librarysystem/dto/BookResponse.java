package com.weykko.librarysystem.dto;

import com.weykko.librarysystem.entity.enums.BookStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookResponse {

    private String title;

    private String author;

    private LocalDate releaseDate;

    private BookStatus bookStatus;

}
