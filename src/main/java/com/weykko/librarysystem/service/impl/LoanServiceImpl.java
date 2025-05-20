package com.weykko.librarysystem.service.impl;

import com.weykko.librarysystem.dto.loan.BorrowBookRequest;
import com.weykko.librarysystem.dto.loan.LoanResponse;
import com.weykko.librarysystem.entity.BookEntity;
import com.weykko.librarysystem.entity.LoanEntity;
import com.weykko.librarysystem.entity.UserEntity;
import com.weykko.librarysystem.entity.enums.BookStatus;
import com.weykko.librarysystem.entity.enums.LoanStatus;
import com.weykko.librarysystem.exception.BookNotAvailableException;
import com.weykko.librarysystem.exception.LoanNotFoundException;
import com.weykko.librarysystem.exception.UserNotFoundException;
import com.weykko.librarysystem.repository.BookRepository;
import com.weykko.librarysystem.repository.LoanRepository;
import com.weykko.librarysystem.repository.UserRepository;
import com.weykko.librarysystem.service.LoanService;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
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

        return LoanResponse.fromEntity(loanEntity);
    }

    @Transactional
    @Override
    public void returnBook(Long id) {
        LoanEntity loanEntity = loanRepository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));

        //TODO: Добавить проверку возвращена ли книга раньше

        loanEntity.setReturnDate(LocalDateTime.now());
        loanEntity.setStatus(LoanStatus.RETURNED);

        BookEntity bookEntity = loanEntity.getBook();
        bookEntity.setStatus(BookStatus.AVAILABLE);
        bookRepository.save(bookEntity);

        loanRepository.save(loanEntity);
    }

    @Override
    public LoanResponse getLoanById(Long id) {
        return loanRepository.findById(id)
                .map(LoanResponse::fromEntity)
                .orElseThrow(() -> new LoanNotFoundException(id));
    }

    @Override
    public List<LoanResponse> getAllLoans() {
        return loanRepository.findAll().stream()
                .map(LoanResponse::fromEntity)
                .toList();
    }

    @Override
    public List<LoanResponse> getOverdueLoans() {
        return loanRepository.findOverdueLoans(LocalDateTime.now(), LoanStatus.OVERDUE).stream()
                .map(LoanResponse::fromEntity)
                .toList();
    }

    @Override
    public List<LoanResponse> getUserLoans(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        return loanRepository.findByUserId(userId).stream()
                .map(LoanResponse::fromEntity)
                .toList();
    }
}
