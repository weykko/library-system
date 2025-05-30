package com.weykko.librarysystem.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class LibraryMetrics {
    private final Counter booksAddedCounter;
    private final Counter booksBorrowedCounter;
    private final Counter usersRegisteredCounter;

    public LibraryMetrics(MeterRegistry registry) {
        booksAddedCounter = Counter.builder("library.books.added")
                .description("Total books added to library")
                .register(registry);

        booksBorrowedCounter = Counter.builder("library.books.borrowed")
                .description("Total books borrowed")
                .register(registry);

        usersRegisteredCounter = Counter.builder("library.users.registered")
                .description("Total users registered")
                .register(registry);
    }

    public void incrementBooksAdded() {
        booksAddedCounter.increment();
    }

    public void incrementBooksBorrowed() {
        booksBorrowedCounter.increment();
    }

    public void incrementUsersRegistered() {
        usersRegisteredCounter.increment();
    }
}