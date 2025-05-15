package com.weykko.librarysystem.service;

import com.weykko.librarysystem.dto.book.BookRequest;
import com.weykko.librarysystem.dto.book.BookResponse;

public interface BookService {
    BookResponse createBook(BookRequest request);

    BookResponse updateBook(Long id, BookRequest request);

    void deleteBook(Long id);
}
