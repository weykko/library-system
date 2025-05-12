package com.weykko.librarysystem.repository;

import com.weykko.librarysystem.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("SELECT b " +
            "FROM BookEntity b " +
            "WHERE " +
            "(LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%'))) OR " +
            "(LOWER(b.author) LIKE LOWER(CONCAT('%', :query, '%'))) OR " +
            "(b.isbn LIKE CONCAT('%', :query, '%'))")
    Page<BookEntity> searchBooks(
            @Param("query") String query,
            Pageable pageable
    );
}
