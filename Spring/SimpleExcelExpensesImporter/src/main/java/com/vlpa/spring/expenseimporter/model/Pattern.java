package com.vlpa.spring.expenseimporter.model;

public class Pattern {

    private String expression;
    private Category category;
    private ExpenseType expenseType;

    public Pattern() {
        this("", null, null);
    }

    public Pattern(String expression, Category category, ExpenseType expenseType) {
        this.expression = expression;
        this.category = category;
        this.expenseType = expenseType;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    @Override
    public String toString() {
        return "Pattern{" +
                "expression='" + expression + '\'' +
                ", category=" + category +
                ", expenseType=" + expenseType +
                '}';
    }
}
