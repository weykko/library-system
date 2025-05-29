package com.weykko.librarysystem.exception.handler;

import com.weykko.librarysystem.dto.error.ErrorResponse;
import com.weykko.librarysystem.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.stream.Collectors;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            BookNotFoundException.class,
            LoanNotFoundException.class,
            UserNotFoundException.class
    })
    public ErrorResponse handleNotFoundException(RuntimeException ex, HttpServletRequest request) {
        return new ErrorResponse(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getServletPath(),
                null
        );
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({
            BookNotAvailableException.class,
            EmailAlreadyUsedException.class,
    })
    public ErrorResponse handleConflictException(RuntimeException ex, HttpServletRequest request) {
        return new ErrorResponse(
                Instant.now(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                request.getServletPath(),
                null
        );
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResponse handleException(Exception ex, HttpServletRequest request) {
        return new ErrorResponse(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                request.getServletPath(),
                null
        );
    }
}
