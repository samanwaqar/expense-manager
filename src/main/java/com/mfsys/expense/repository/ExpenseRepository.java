package com.mfsys.expense.repository;

import com.mfsys.expense.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserEmail(String userEmail);
    List<Expense> findAll();


    @Query("""

            SELECT e FROM Expense e
WHERE (:email IS NULL OR e.userEmail = :email)
AND (:category IS NULL OR e.category = :category)
AND (:status IS NULL OR e.status = :status)
AND (:startDate IS NULL OR e.createdAt >= :startDate)
AND (:endDate IS NULL OR e.createdAt <= :endDate)
""")
    List<Expense> filterExpenses(
            @Param("email") String email,
            @Param("category") String category,
            @Param("status") String status,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
    }

