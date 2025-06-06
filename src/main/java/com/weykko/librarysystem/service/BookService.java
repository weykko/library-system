package com.weykko.librarysystem.service;

import com.weykko.librarysystem.dto.book.BookRequest;
import com.weykko.librarysystem.dto.book.BookResponse;
import com.weykko.librarysystem.dto.book.BookUpdateRequest;

import java.util.List;

public interface BookService {
    BookResponse getBook(Long id);

    List<BookResponse> getAllBooks();

    BookResponse createBook(BookRequest request);

    BookResponse updateBook(Long id, BookUpdateRequest request);

    void deleteBook(Long id);
}
