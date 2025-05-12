package com.weykko.librarysystem.controller;

import com.weykko.librarysystem.dto.book.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public/books")
public class BookController {

    // TODO: реализовать пагинацию при поиске
    @GetMapping
    public List<BookResponse> getBooks(@RequestParam String q) {

    }

    @GetMapping("/{id}")
    public BookResponse getBook(@PathVariable Long id) {

    }
}
