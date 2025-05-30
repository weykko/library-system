package com.weykko.librarysystem.metrics;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
@RequiredArgsConstructor
public class CounterAspect {
    private final LibraryMetrics libraryMetrics;

    @After("@annotation(counter)")
    public void counterMetric(Counter counter) {
        if (Objects.equals(counter.value(), "book"))
            libraryMetrics.incrementBooksAdded();
        if (Objects.equals(counter.value(), "user"))
            libraryMetrics.incrementBooksBorrowed();
        if (Objects.equals(counter.value(), "loan"))
            libraryMetrics.incrementUsersRegistered();
    }
}
