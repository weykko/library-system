package com.weykko.librarysystem.dto.book;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookRequest {

    private String title;

    private String author;

    private LocalDate releaseDate;
}
