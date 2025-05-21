package com.weykko.librarysystem.controller;

import com.weykko.librarysystem.dto.book.BookResponse;
import com.weykko.librarysystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public/books")
public class BookController {

    private final BookService bookService;

    // TODO: реализовать пагинацию при поиске
    @GetMapping
    public List<BookResponse> getBooks(@RequestParam String q) {
        return List.of();
    }

    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }
}
