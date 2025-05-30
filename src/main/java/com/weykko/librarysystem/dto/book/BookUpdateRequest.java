package com.weykko.librarysystem.dto.book;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookUpdateRequest {

    private String title;

    private String author;

    @Past(message = "Release date must be correct")
    private LocalDate releaseDate;

    @Pattern(regexp = "^(?=(?:\\D*\\d){10}(?:(?:\\D*\\d){3})?$)[\\d-]+$", message = "Invalid ISBN format")
    private String isbn;
}
