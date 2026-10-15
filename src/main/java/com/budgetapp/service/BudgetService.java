package com.budgetapp;

import com.budgetapp.model.UserBudget;

import java.util.Map;

public interface BudgetService {
    void saveUserIncome(String userId, double income);
    void addExpenseToUser(String userId, String desc, double amount, String typeStr);
    Map<String, Double> calculateTargets(double income);
    Map<String, Double> calculateCurrentSpending(UserBudget budget);
}
