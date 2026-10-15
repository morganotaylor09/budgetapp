package com.budgetapp;

import com.budgetapp.model.BudgetType;
import com.budgetapp.model.Expense;
import com.budgetapp.model.UserBudget;
import com.budgetapp.repository.BudgetRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class BudgetServiceImpl implements BudgetService {

    private final BudgetRepository repository;

    public BudgetServiceImpl(BudgetRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveUserIncome(String userId, double income) {
        UserBudget budget = repository.findByUserId(userId);
        if (budget == null) {
            budget = new UserBudget(userId, income);
        } else {
            budget.setMonthlyIncome(income);
        }
        repository.save(budget);
    }

    @Override
    public void addExpenseToUser(String userId, String desc, double amount, String typeStr) {
        UserBudget budget = repository.findByUserId(userId);
        if (budget != null) {
            BudgetType type = BudgetType.valueOf(typeStr.toUpperCase());
            budget.addExpense(new Expense(desc, amount, type));
            repository.save(budget);
        }
    }

    @Override
    public Map<String, Double> calculateTargets(double income) {
        Map<String, Double> targets = new HashMap<>();
        targets.put("NEED", income * BudgetType.NEED.getPercentage());
        targets.put("WANT", income * BudgetType.WANT.getPercentage());
        targets.put("SAVINGS", income * BudgetType.SAVINGS.getPercentage());
        return targets;
    }

    @Override
    public Map<String, Double> calculateCurrentSpending(UserBudget budget) {
        Map<String, Double> spending = new HashMap<>();
        spending.put("NEED", 0.0);
        spending.put("WANT", 0.0);
        spending.put("SAVINGS", 0.0);

        for (Expense e : budget.getExpenses()) {
            String key = e.getType().name();
            spending.put(key, spending.get(key) + e.getAmount());
        }
        return spending;
    }
}
