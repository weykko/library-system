package com.weykko.librarysystem.entity;

import com.weykko.librarysystem.entity.enums.BookStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    private String isbn;

    @Enumerated(EnumType.STRING)
    private BookStatus status = BookStatus.AVAILABLE;
//
//    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
//    private List<LoanEntity> loans = new ArrayList<>();
}

