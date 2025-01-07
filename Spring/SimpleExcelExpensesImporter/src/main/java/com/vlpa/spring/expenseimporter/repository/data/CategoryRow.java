package com.vlpa.spring.expenseimporter.repository.data;

public class CategoryRow {

    private int id;
    private String category;
    private String parentCategory;
    private String topCategory;

    public CategoryRow() {
    }

    public CategoryRow(int id, String category, String parentCategory, String topCategory) {
        this.id = id;
        this.category = category;
        this.parentCategory = parentCategory;
        this.topCategory = topCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getTopCategory() {
        return topCategory;
    }

    public void setTopCategory(String topCategory) {
        this.topCategory = topCategory;
    }

    @Override
    public String toString() {
        return "CategoryRow{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", parentCategory='" + parentCategory + '\'' +
                ", topCategory='" + topCategory + '\'' +
                '}';
    }
}
