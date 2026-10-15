package com.budgetapp.model;

public class Expense {
    private String description;
    private double amount;
    private BudgetType type;

    public Expense(String description, double amount, BudgetType type) {
        this.description = description;
        this.amount = amount;
        this.type = type;
    }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public BudgetType getType() { return type; }
}
