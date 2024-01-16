package com.vlpa.spring.expenseimporter.model;

import java.util.ArrayList;
import java.util.List;

public class ExpenseCategory {

    private String name;
    private List<String> subCategories;

    public ExpenseCategory() {
        this("");
    }

    public ExpenseCategory(String name) {
        this.name = name;
        this.subCategories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<String> subCategories) {
        this.subCategories = subCategories;
    }
}
