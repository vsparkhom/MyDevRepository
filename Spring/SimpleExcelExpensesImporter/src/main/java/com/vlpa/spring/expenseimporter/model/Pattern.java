package com.vlpa.spring.expenseimporter.model;

public class Pattern {

    private String expression;
    private Category category;

    public Pattern() {
        this("",null);
    }

    public Pattern(String expression, Category category) {
        this.expression = expression;
        this.category = category;
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

    @Override
    public String toString() {
        return "Pattern{" +
                "expression='" + expression + '\'' +
                ", category=" + category +
                '}';
    }
}
