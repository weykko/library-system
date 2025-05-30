package com.weykko.librarysystem.mapper;

import com.weykko.librarysystem.dto.book.BookRequest;
import com.weykko.librarysystem.dto.book.BookResponse;
import com.weykko.librarysystem.entity.BookEntity;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookEntity toEntity(BookRequest request) {
        BookEntity bookEntity = new BookEntity();

        bookEntity.setTitle(request.getTitle());
        bookEntity.setAuthor(request.getAuthor());
        bookEntity.setReleaseDate(request.getReleaseDate());
        bookEntity.setIsbn(request.getIsbn());

        return bookEntity;
    }

    public BookResponse toResponse(BookEntity bookEntity) {
        return new BookResponse(
                bookEntity.getId(),
                bookEntity.getTitle(),
                bookEntity.getAuthor(),
                bookEntity.getReleaseDate(),
                bookEntity.getIsbn(),
                bookEntity.getStatus()
        );
    }
}
