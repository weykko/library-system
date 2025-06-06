package com.weykko.librarysystem.controller;

import com.weykko.librarysystem.dto.book.BookRequest;
import com.weykko.librarysystem.dto.book.BookResponse;
import com.weykko.librarysystem.dto.book.BookUpdateRequest;
import com.weykko.librarysystem.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/books")
public class AdminBookController {

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse createBook(@RequestBody @Valid BookRequest request) {
        return bookService.createBook(request);
    }

    @PatchMapping("/{id}")
    public BookResponse updateBook(@PathVariable Long id, @RequestBody @Valid BookUpdateRequest request) {
        return bookService.updateBook(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
