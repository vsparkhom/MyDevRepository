package com.vlpa.spring.expenseimporter.model;

public enum ExpenseType {

    Fixed,
    Floating,
    Skip,
    Unknown;

    public static ExpenseType resolveExpenseType(String value) {
        for (ExpenseType expenseType : values()) {
            if (expenseType.name().equalsIgnoreCase(value)) {
                return expenseType;
            }
        }
        throw new RuntimeException(String.format("Pattern type '%s' is not supported", value));
    }
}
