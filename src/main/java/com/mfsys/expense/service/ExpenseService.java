package com.mfsys.expense.service;

import com.mfsys.expense.model.Expense;
import com.mfsys.expense.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public Expense addExpense(Expense expense) {
        Expense expense1 = new Expense();
        expense1.setAmount(expense.getAmount());
        expense1.setCategory(expense.getCategory());
        expense1.setTitle(expense.getTitle());
        return repository.save(expense);
    }

    public List<Expense> getUserExpenses(String email) {
       return repository.findAll();
    }

    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }

    public Expense updateExpense(Expense expense) {
        Expense existing = repository.findById(expense.getId())
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        existing.setTitle(expense.getTitle());
        existing.setCategory(expense.getCategory());
        existing.setAmount(expense.getAmount());

        return repository.save(existing);
    }

    public void deleteExpense(Long id) {
        Expense existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        repository.delete(existing);
    }
}

