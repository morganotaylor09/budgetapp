package com.budgetapp.model;

import java.util.ArrayList;
import java.util.List;

public class UserBudget {
    private String userId;
    private double monthlyIncome;
    private List<Expense> expenses = new ArrayList<>();

    public UserBudget(String userId, double monthlyIncome) {
        this.userId = userId;
        this.monthlyIncome = monthlyIncome;
    }
    
    public String getUserId() { return userId; }
    public double getMonthlyIncome() { return monthlyIncome; }
    public void setMonthlyIncome(double monthlyIncome) { this.monthlyIncome = monthlyIncome; }
    public List<Expense> getExpenses() { return expenses; }
    public void addExpense(Expense expense) { this.expenses.add(expense); }
}
