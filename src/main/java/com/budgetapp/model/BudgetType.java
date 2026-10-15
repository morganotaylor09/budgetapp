package com.budgetapp.model;

public enum BudgetType {
    NEED(0.50, "Needs (50%)"),
    WANT(0.30, "Wants (30%)"),
    SAVINGS(0.20, "Savings/Debt (20%)");

    private final double percentage;
    private final String description;

    BudgetType(double percentage, String description) {
        this.percentage = percentage;
        this.description = description;
    }

    public double getPercentage() { return percentage; }
    public String getDescription() { return description; }
}
