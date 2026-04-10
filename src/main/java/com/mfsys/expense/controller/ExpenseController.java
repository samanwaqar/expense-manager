package com.mfsys.expense.controller;

import com.mfsys.expense.dto.DashboardResponse;
import com.mfsys.expense.model.Expense;
import com.mfsys.expense.service.ExpenseService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.mfsys.expense.config.UrlConstants.*;


@RestController
@RequestMapping(EXPENSE)
public class ExpenseController {

    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping(CREATE_EXPENSE)
    public Expense addExpense(@RequestBody Expense expense) {
        return service.addExpense(expense);
    }

    // GET
    @GetMapping(GET_EXPENSE)
    public List<Expense> getExpenses() {
        return service.getUserExpenses();
    }

    // UPDATE
    @PutMapping(UPDATE_EXPENSE)
    public Expense updateExpense(@PathVariable Long id,
                                 @RequestBody Expense expense) {
        return service.updateExpense(id, expense);
    }

    // DELETE
    @DeleteMapping(DELETE_EXPENSE)
    public String deleteExpense(@PathVariable Long id) {
        service.deleteExpense(id);
        return "Deleted successfully";
    }

    // DASHBOARD
    @GetMapping(DASHBOARD)
    public DashboardResponse getDashboard() {
        return service.getDashboard();
    }


    //Monthly report
    @GetMapping(MONTHLY_REPORT)
    public Map<String, Double> monthlyReport() {
        return service.getMonthlyReport();
    }

}
