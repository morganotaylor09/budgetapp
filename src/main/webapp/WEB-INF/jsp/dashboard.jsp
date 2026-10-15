<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://sun.com" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>50-30-20 Budget Dashboard</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f6f9; }
        .container { max-width: 800px; background: white; padding: 25px; border-radius: 8px; box-shadow: 0px 0px 10px rgba(0,0,0,0.1); }
        .grid { display: flex; gap: 20px; margin-top: 20px; }
        .card { flex: 1; padding: 15px; border-radius: 5px; color: white; }
        .needs { background-color: #2bc0d8; }
        .wants { background-color: #f39c12; }
        .savings { background-color: #2ecc71; }
        form { margin-bottom: 20px; }
    </style>
</head>
<body>
<div class="container">
    <h2>📊 50-30-20 Budget Planner</h2>
    <p>Logged in as: <strong>${budget.userId}</strong></p>

    <!-- Income Form -->
    <form action="/budget/income" method="post">
        <input type="hidden" name="userId" value="${budget.userId}">
        <label>Monthly Take-Home Income ($): </label>
        <input type="number" name="income" value="${budget.monthlyIncome}" step="0.01" required>
        <button type="submit">Update Income</button>
    </form>

    <!-- Expense Form -->
    <form action="/budget/expense" method="post">
        <input type="hidden" name="userId" value="${budget.userId}">
        <input type="text" name="desc" placeholder="Expense Description" required>
        <input type="number" name="amount" placeholder="Amount" step="0.01" required>
        <select name="type">
            <option value="NEED">Need (50%)</option>
            <option value="WANT">Want (30%)</option>
            <option value="SAVINGS">Savings (20%)</option>
        </select>
        <button type="submit">Add Expense</button>
    </form>

    <!-- Budget Metrics Breakdowns -->
    <div class="grid">
        <div class="card needs">
            <h3>Needs (50%)</h3>
            <p>Target: $${targets['NEED']}</p>
            <p>Spent: $${spending['NEED']}</p>
        </div>
        <div class="card wants">
            <h3>Wants (30%)</h3>
            <p>Target: $${targets['WANT']}</p>
            <p>Spent: $${spending['WANT']}</p>
        </div>
        <div class="card savings">
            <h3>Savings (20%)</h3>
            <p>Target: $${targets['SAVINGS']}</p>
            <p>Spent: $${spending['SAVINGS']}</p>
        </div>
    </div>

    <!-- History List -->
    <h3>📝 Transaction History</h3>
    <ul>
        <c:forEach var="item" items="${budget.expenses}">
            <li>${item.description}: $${item.amount} (${item.type})</li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
