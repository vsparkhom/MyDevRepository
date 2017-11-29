package vlpa.expman.model;


import vlpa.expman.model.Category;

import java.util.Date;

public class Expense {

    private int id;
    private String name;
    private Date date;
    private double amount;
    private Category category;

    public Expense(int id, String name, Date date, double amount, Category category) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.amount = amount;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", category=[" + category.getId() + ", " + category.getName() + "]" +
                '}';
    }
}
