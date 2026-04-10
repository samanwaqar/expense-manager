package com.mfsys.expense.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class DashboardResponse {

    private long totalExpenses;
    private double totalAmount;
    private Map<String, Double> categoryWiseAmount;
}
