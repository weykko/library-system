package com.weykko.librarysystem.service.impl;

import com.weykko.librarysystem.dto.loan.BorrowBookRequest;
import com.weykko.librarysystem.dto.loan.LoanResponse;
import com.weykko.librarysystem.entity.BookEntity;
import com.weykko.librarysystem.entity.LoanEntity;
import com.weykko.librarysystem.entity.UserEntity;
import com.weykko.librarysystem.entity.enums.BookStatus;
import com.weykko.librarysystem.entity.enums.LoanStatus;
import com.weykko.librarysystem.exception.BookNotAvailableException;
import com.weykko.librarysystem.exception.UserNotFoundException;
import com.weykko.librarysystem.repository.BookRepository;
import com.weykko.librarysystem.repository.LoanRepository;
import com.weykko.librarysystem.repository.UserRepository;
import com.weykko.librarysystem.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Override
    public LoanResponse borrowBook(BorrowBookRequest request) {
        UserEntity userEntity = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));

        BookEntity bookEntity = bookRepository.findById(request.getBookId())
                .filter(b -> b.getStatus() == BookStatus.AVAILABLE)
                .orElseThrow(() -> new BookNotAvailableException(request.getBookId()));

        LoanEntity loanEntity = LoanEntity.builder()
                .user(userEntity)
                .book(bookEntity)
                .borrowDate(LocalDateTime.now())
                .dueDate(LocalDateTime.now().plusDays(request.getLoanLength()))
                .status(LoanStatus.ACTIVE)
                .build();

        bookEntity.setStatus(BookStatus.BORROWED);
        bookRepository.save(bookEntity);

        loanRepository.save(loanEntity);

        //TODO: реализовать создание через маппер mapstruct
        return null;
    }

    @Override
    public void returnBook(Long id) {

    }

    @Override
    public LoanResponse getLoanById(Long id) {
        return null;
    }

    @Override
    public List<LoanResponse> getAllLoans() {
        return List.of();
    }

    @Override
    public List<LoanResponse> getOverdueLoans() {
        return List.of();
    }

    @Override
    public List<LoanResponse> getUserLoans(Long id) {
        return List.of();
    }
}
