package com.weykko.librarysystem.repository;

import com.weykko.librarysystem.entity.LoanEntity;
import com.weykko.librarysystem.entity.enums.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    List<LoanEntity> findByUserId(Long userId);

    @Query("SELECT l FROM LoanEntity l " +
            "WHERE l.dueDate < :currentDate " +
            "OR l.status = :status")
    List<LoanEntity> findOverdueLoans(
            @Param("currentDate") LocalDateTime currentDate,
            @Param("status") LoanStatus status
    );
}

