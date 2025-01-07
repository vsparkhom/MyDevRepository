package com.vlpa.spring.expenseimporter.model;

public enum TopCategory {

    NEEDS,
    WANTS,
    SAVINGS;

    public static TopCategory resolve(String s) {
        for (TopCategory value : values()) {
            if(value.name().equalsIgnoreCase(s)) {
                return value;
            }
        }
        throw new IllegalArgumentException("The '" + s + "' value can't be resolved");
    }

}
