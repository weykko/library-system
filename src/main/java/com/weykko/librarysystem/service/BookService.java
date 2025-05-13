package com.weykko.librarysystem.service;

import com.weykko.librarysystem.dto.book.BookRequest;
import com.weykko.librarysystem.dto.book.BookResponse;
import com.weykko.librarysystem.entity.BookEntity;
import com.weykko.librarysystem.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public BookResponse createBook(BookRequest request) {
        BookEntity bookEntity = new BookEntity();

        bookEntity.setTitle(request.getTitle());
        bookEntity.setAuthor(request.getAuthor());
        bookEntity.setReleaseDate(request.getReleaseDate());
        bookEntity.setIsbn(request.getIsbn());

        bookRepository.save(bookEntity);

        return new BookResponse(
                bookEntity.getId(),
                bookEntity.getTitle(),
                bookEntity.getAuthor(),
                bookEntity.getReleaseDate(),
                bookEntity.getIsbn(),
                bookEntity.getStatus());
    }
}
