package com.budgetapp;

import com.budgetapp.model.UserBudget;
import org.springframework.stereotype.Repository;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BudgetRepository {
    // Simulated Database saving user data uniquely by userId
    private final ConcurrentHashMap<String, UserBudget> database = new ConcurrentHashMap<>();

    public void save(UserBudget userBudget) {
        database.put(userBudget.getUserId(), userBudget);
    }

    public UserBudget findByUserId(String userId) {
        return database.get(userId);
    }
}
