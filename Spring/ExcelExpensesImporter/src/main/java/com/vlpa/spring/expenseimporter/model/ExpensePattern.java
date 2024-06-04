package com.vlpa.spring.expenseimporter.model;

public class ExpensePattern {

    private String expression;
    private String category;
    private boolean isAmountBased;

    public ExpensePattern() {
        this("","");
    }

    public ExpensePattern(String expression, String category) {
        this.expression = expression;
        this.category = category;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAmountBased() {
        return isAmountBased;
    }
}
