package vlpa.expman.model;

import vlpa.expman.controller.ExpenseUtils;

import java.util.Collection;
import java.util.Date;

public class ExpensesReport {

    private Category category;
    private double currentAmount;
    private Date start;
    private Date end;

    public ExpensesReport(Category category, Date start, Date end) {
        this(category, start, end, 0);
    }

    public ExpensesReport(Category category, Date start, Date end, double currentAmount) {
        this.category = category;
        this.currentAmount = currentAmount;
        this.start = start;
        this.end = end;
    }

    public Category getCategory() {
        return category;
    }

    public double getCurrentAmount() {
        return ExpenseUtils.round(currentAmount, 2);
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public void addExpense(Expense e) {
        currentAmount += e.getAmount();
    }

    public void addExpenses(Collection<Expense> expenses) {
        for (Expense e : expenses) {
            currentAmount += e.getAmount();
        }
    }

    public void removeExpense(Expense e) {
        currentAmount -= e.getAmount();
    }

    public double getLeftover() {
        return ExpenseUtils.round(getMonthlyCategoryLimit() - currentAmount, 2);
    }

    public double getMonthlyCategoryLimit() {
        return category.getLimit();
    }

    public double getUsagePercent() {
        return ExpenseUtils.round(currentAmount/category.getLimit(), 2);
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "ExpensesReport{" +
                "category=" + category +
                ", currentAmount=" + currentAmount +
                ", leftover=" + getLeftover() +
                ", limit=" + getMonthlyCategoryLimit() +
                ", usagePercent=" + getUsagePercent() +
                '}';
    }
}
