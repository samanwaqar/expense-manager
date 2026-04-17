package com.mfsys.expense.repository;

import com.mfsys.expense.model.Expense;
import com.mfsys.expense.model.User;
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
    List<Expense> findByUser(User user);


    @Query("""
    SELECT e FROM Expense e
    WHERE (:email IS NULL OR e.user.email = :email)
    AND (:category IS NULL OR e.category = :category)
    AND (:status IS NULL OR e.status = :status)
    AND (:startDate IS NULL OR e.createdAt >= :startDate)
    AND (:endDate IS NULL OR e.createdAt <= :endDate)
""")
    List<Expense> filterExpenses(
            String email,
            String category,
            String status,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

}

