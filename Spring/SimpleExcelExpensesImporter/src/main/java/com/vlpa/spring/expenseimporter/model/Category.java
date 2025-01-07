package com.vlpa.spring.expenseimporter.model;

import static com.vlpa.spring.expenseimporter.model.TopCategory.SAVINGS;

public class Category {

    private long id;
    private String name;
    private Category parentCategory;
    private TopCategory topCategory;

    public Category() {
        this(0, "", null, SAVINGS);
    }

    public Category(long id, String name, Category parentCategory, TopCategory topCategory) {
        this.id = id;
        this.name = name;
        this.parentCategory = parentCategory;
        this.topCategory = topCategory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public TopCategory getTopCategory() {
        return topCategory;
    }

    public void setTopCategory(TopCategory topCategory) {
        this.topCategory = topCategory;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentCategory=" + parentCategory +
                ", topCategory=" + topCategory +
                '}';
    }
}
