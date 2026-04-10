package com.mfsys.expense.service;

import com.mfsys.expense.dto.DashboardResponse;
import com.mfsys.expense.model.Expense;
import com.mfsys.expense.repository.ExpenseRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.security.core.context.SecurityContextHolder;
@Service
public class ExpenseService {

    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    // CREATE
//    public Expense addExpense(Expense expense) {
//
//        String email = SecurityContextHolder.getContext()
//                .getAuthentication()
//                .getName();
//
//        expense.setUserEmail(email);
//
//        return repository.save(expense);
//    }

    public Expense addExpense(Expense expense) {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        Expense e = new Expense();
        e.setTitle(expense.getTitle());
        e.setAmount(expense.getAmount());
        e.setCategory(expense.getCategory());
        e.setReceiptName(expense.getReceiptName());
        e.setReceiptType(expense.getReceiptType());
        e.setReceiptUrl(expense.getReceiptUrl());


        e.setUserEmail(email);

        return repository.save(e);
    }



    // GET (FIXED - CLEAN)
    public List<Expense> getUserExpenses() {
        return repository.findAll();
    }

    // UPDATE (CLEAN + SAFE)
    public Expense updateExpense(Long id, Expense newExpense) {

        Expense existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        existing.setTitle(newExpense.getTitle());
        existing.setAmount(newExpense.getAmount());
        existing.setCategory(newExpense.getCategory());

        return repository.save(existing);
    }
    // DELETE (CLEAN + SAFE)
    public void deleteExpense(Long id) {
        repository.deleteById(id);
    }

    // FILTER (FIXED ADMIN LOGIC)
    public List<Expense> filterExpenses(
            String email,
            String category,
            String status,
            LocalDateTime startDate,
            LocalDateTime endDate,
            boolean isAdmin
    ) {

        String ownerEmail = isAdmin ? null : email;

        return repository.filterExpenses(
                ownerEmail,
                category,
                status,
                startDate,
                endDate
        );
    }

    // DASHBOARD (FIXED)
    public DashboardResponse getDashboard(String email, boolean isAdmin) {

        List<Expense> expenses = isAdmin
                ? repository.findAll()
                : repository.findByUserEmail(email);

        double totalAmount = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        Map<String, Double> categoryMap = expenses.stream()
                .collect(Collectors.groupingBy(
                        Expense::getCategory,
                        Collectors.summingDouble(Expense::getAmount)
                ));

        return new DashboardResponse(
                expenses.size(),
                totalAmount,
                categoryMap
        );
    }
    public DashboardResponse getDashboard() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        List<Expense> expenses = repository.findByUserEmail(email);

        double totalAmount = expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        Map<String, Double> categoryMap = expenses.stream()
                .collect(Collectors.groupingBy(
                        Expense::getCategory,
                        Collectors.summingDouble(Expense::getAmount)
                ));

        return new DashboardResponse(
                expenses.size(),
                totalAmount,
                categoryMap
        );
    }
    public Map<String, Double> getMonthlyReport() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        List<Expense> expenses = repository.findByUserEmail(email);

        return expenses.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getCreatedAt().getMonth().toString(),
                        Collectors.summingDouble(Expense::getAmount)
                ));
    }

}

