import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import java.util.*;

class Expense {
    private String category;
    private String description;
    private double amount;
    private Date date;

    public Expense(String category, String description, double amount, Date date) {
        this.category = category;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | $%.2f | %s", category, description, amount, date.toString());
    }
}

public class ExpenseTracker {
    private List<Expense> expenses;
    private Scanner scanner;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. View Spending Insights");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    viewInsights();
                    break;
                case 4:
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addExpense() {
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        Date date = new Date();

        expenses.add(new Expense(category, description, amount, date));
        System.out.println("Expense added successfully.");
    }

    private void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to display.");
            return;
        }

        System.out.println("\nAll Expenses:");
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    private void viewInsights() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to analyze.");
            return;
        }

        Map<String, Double> categoryTotals = new HashMap<>();
        double totalSpent = 0;

        for (Expense e : expenses) {
            categoryTotals.put(e.getCategory(), categoryTotals.getOrDefault(e.getCategory(), 0.0) + e.getAmount());
            totalSpent += e.getAmount();
        }

        System.out.println("\nSpending Insights:");
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            double percentage = (entry.getValue() / totalSpent) * 100;
            System.out.printf("%s: $%.2f (%.2f%% of total spending)%n", entry.getKey(), entry.getValue(), percentage);
        }

        System.out.printf("Total Spent: $%.2f%n", totalSpent);
    }

    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        tracker.start();
    }
}
