package com.weykko.librarysystem.service.impl;

import com.weykko.librarysystem.aspect.Timeout;
import com.weykko.librarysystem.dto.book.BookRequest;
import com.weykko.librarysystem.dto.book.BookResponse;
import com.weykko.librarysystem.dto.book.BookUpdateRequest;
import com.weykko.librarysystem.entity.BookEntity;
import com.weykko.librarysystem.eventlistener.event.DatabaseChangedEvent;
import com.weykko.librarysystem.exception.BookNotFoundException;
import com.weykko.librarysystem.mapper.BookMapper;
import com.weykko.librarysystem.repository.BookRepository;
import com.weykko.librarysystem.service.BookService;
import org.springframework.context.ApplicationEventPublisher;
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

    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    @Override
    public BookResponse getBook(Long id){
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        return bookMapper.toResponse(bookEntity);
    }

    @Timeout(10)
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
        applicationEventPublisher.publishEvent(new DatabaseChangedEvent("books", bookEntity.getId()));

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
        applicationEventPublisher.publishEvent(new DatabaseChangedEvent("books", bookEntity.getId()));

        return bookMapper.toResponse(bookEntity);
    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        bookRepository.delete(bookEntity);
        applicationEventPublisher.publishEvent(new DatabaseChangedEvent("books", bookEntity.getId()));
    }

    private <T> void updateField(T currentValue, T newValue, Consumer<T> setter) {
        if (newValue != null && !Objects.equals(currentValue, newValue)) {
            setter.accept(newValue);
        }
    }
}
