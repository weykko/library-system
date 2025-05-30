package com.weykko.librarysystem.service.impl;

import com.weykko.librarysystem.aspect.Timeout;
import com.weykko.librarysystem.dto.loan.BorrowRequest;
import com.weykko.librarysystem.dto.loan.LoanResponse;
import com.weykko.librarysystem.entity.BookEntity;
import com.weykko.librarysystem.entity.LoanEntity;
import com.weykko.librarysystem.entity.UserEntity;
import com.weykko.librarysystem.entity.enums.BookStatus;
import com.weykko.librarysystem.entity.enums.LoanStatus;
import com.weykko.librarysystem.eventlistener.event.DatabaseChangedEvent;
import com.weykko.librarysystem.exception.BookNotAvailableException;
import com.weykko.librarysystem.exception.LoanNotFoundException;
import com.weykko.librarysystem.exception.UserNotFoundException;
import com.weykko.librarysystem.mapper.LoanMapper;
import com.weykko.librarysystem.repository.BookRepository;
import com.weykko.librarysystem.repository.LoanRepository;
import com.weykko.librarysystem.repository.UserRepository;
import com.weykko.librarysystem.service.LoanService;
import org.springframework.context.ApplicationEventPublisher;
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
    private final LoanMapper loanMapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    @Override
    public LoanResponse borrowBook(BorrowRequest request) {
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
        applicationEventPublisher.publishEvent(new DatabaseChangedEvent("books", bookEntity.getId()));

        loanRepository.save(loanEntity);
        applicationEventPublisher.publishEvent(new DatabaseChangedEvent("loans", loanEntity.getId()));

        return loanMapper.toResponse(loanEntity);
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
        applicationEventPublisher.publishEvent(new DatabaseChangedEvent("books", bookEntity.getId()));

        loanRepository.save(loanEntity);
        applicationEventPublisher.publishEvent(new DatabaseChangedEvent("loans", loanEntity.getId()));
    }

    @Override
    public LoanResponse getLoanById(Long id) {
        return loanRepository.findById(id)
                .map(loanMapper::toResponse)
                .orElseThrow(() -> new LoanNotFoundException(id));
    }

    @Timeout(10)
    @Override
    public List<LoanResponse> getAllLoans() {
        return loanRepository.findAll().stream()
                .map(loanMapper::toResponse)
                .toList();
    }

    @Override
    public List<LoanResponse> getOverdueLoans() {
        return loanRepository.findOverdueLoans(LocalDateTime.now(), LoanStatus.OVERDUE).stream()
                .map(loanMapper::toResponse)
                .toList();
    }

    @Override
    public List<LoanResponse> getUserLoans(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        return loanRepository.findByUserId(userId).stream()
                .map(loanMapper::toResponse)
                .toList();
    }
}
