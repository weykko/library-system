package com.weykko.librarysystem.service.impl;

import com.weykko.librarysystem.dto.book.BookRequest;
import com.weykko.librarysystem.dto.book.BookResponse;
import com.weykko.librarysystem.dto.book.BookUpdateRequest;
import com.weykko.librarysystem.entity.BookEntity;
import com.weykko.librarysystem.exception.BookNotFoundException;
import com.weykko.librarysystem.mapper.BookMapper;
import com.weykko.librarysystem.repository.BookRepository;
import com.weykko.librarysystem.service.BookService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Transactional
    @Override
    public BookResponse getBook(Long id){
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        return bookMapper.toResponse(bookEntity);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    @Transactional
    @Override
    public BookResponse createBook(BookRequest request) {
        //TODO: реализовать проверку на наличие аналогичной книги в библиотеке

        BookEntity bookEntity = bookMapper.toEntity(request);

        bookRepository.save(bookEntity);

        return bookMapper.toResponse(bookEntity);
    }

    @Transactional
    @Override
    public BookResponse updateBook(Long id, BookUpdateRequest request) {
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        updateField(bookEntity.getTitle(), request.getTitle(), bookEntity::setTitle);
        updateField(bookEntity.getAuthor(), request.getAuthor(), bookEntity::setAuthor);
        updateField(bookEntity.getReleaseDate(), request.getReleaseDate(), bookEntity::setReleaseDate);
        updateField(bookEntity.getIsbn(), request.getIsbn(), bookEntity::setIsbn);

        bookRepository.save(bookEntity);

        return bookMapper.toResponse(bookEntity);
    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        bookRepository.delete(book);
    }

    private <T> void updateField(T currentValue, T newValue, Consumer<T> setter) {
        if (newValue != null && !Objects.equals(currentValue, newValue)) {
            setter.accept(newValue);
        }
    }
}
