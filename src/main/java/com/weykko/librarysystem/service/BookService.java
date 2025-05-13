package com.weykko.librarysystem.service;

import com.weykko.librarysystem.dto.book.BookRequest;
import com.weykko.librarysystem.dto.book.BookResponse;
import com.weykko.librarysystem.entity.BookEntity;
import com.weykko.librarysystem.exception.BookNotFoundException;
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
        //TODO: реализовать проверку на наличие аналогичной книги в библиотеке

        BookEntity bookEntity = new BookEntity();

        bookEntity.setTitle(request.getTitle());
        bookEntity.setAuthor(request.getAuthor());
        bookEntity.setReleaseDate(request.getReleaseDate());
        bookEntity.setIsbn(request.getIsbn());

        bookRepository.save(bookEntity);

        return createBookResponse(bookEntity);
    }

    @Transactional
    public BookResponse updateBook(Long id, BookRequest request) {
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        bookEntity.setTitle(request.getTitle());
        bookEntity.setAuthor(request.getAuthor());
        bookEntity.setReleaseDate(request.getReleaseDate());
        bookEntity.setIsbn(request.getIsbn());

        bookRepository.save(bookEntity);

        return createBookResponse(bookEntity);
    }

    @Transactional
    public void deleteBook(Long id) {
        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        bookRepository.delete(book);
    }

    private BookResponse createBookResponse(BookEntity bookEntity) {
        return new BookResponse(
                bookEntity.getId(),
                bookEntity.getTitle(),
                bookEntity.getAuthor(),
                bookEntity.getReleaseDate(),
                bookEntity.getIsbn(),
                bookEntity.getStatus());
    }
}
