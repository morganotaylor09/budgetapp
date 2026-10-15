package com.budgetapp;

import com.budgetapp.model.UserBudget;
import com.budgetapp.repository.BudgetRepository;
import com.budgetapp.service.BudgetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/budget")
public class BudgetController {

    private final BudgetService service;
    private final BudgetRepository repository;

    public BudgetController(BudgetService service, BudgetRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public String showDashboard(@RequestParam(defaultValue = "guest_user") String userId, Model model) {
        UserBudget budget = repository.findByUserId(userId);
        if (budget == null) {
            budget = new UserBudget(userId, 0.0);
            repository.save(budget);
        }

        model.addAttribute("budget", budget);
        model.addAttribute("targets", service.calculateTargets(budget.getMonthlyIncome()));
        model.addAttribute("spending", service.calculateCurrentSpending(budget));
        return "dashboard"; // Maps to dashboard.jsp
    }

    @PostMapping("/income")
    public String updateIncome(@RequestParam String userId, @RequestParam double income) {
        service.saveUserIncome(userId, income);
        return "redirect:/budget?userId=" + userId;
    }

    @PostMapping("/expense")
    public String addExpense(@RequestParam String userId, @RequestParam String desc,
                             @RequestParam double amount, @RequestParam String type) {
        service.addExpenseToUser(userId, desc, amount, type);
        return "redirect:/budget?userId=" + userId;
    }
}
