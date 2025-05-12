package com.weykko.librarysystem.dto.book;

import com.weykko.librarysystem.entity.enums.BookStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookResponse {

    private Long id;

    private String title;

    private String author;

    private LocalDate releaseDate;

    private String isbn;

    private BookStatus bookStatus;

}
