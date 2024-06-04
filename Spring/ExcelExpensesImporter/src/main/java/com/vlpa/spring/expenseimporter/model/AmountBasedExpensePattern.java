package com.vlpa.spring.expenseimporter.model;

public class AmountBasedExpensePattern extends ExpensePattern {

    private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean isAmountBased() {
        return true;
    }
}
