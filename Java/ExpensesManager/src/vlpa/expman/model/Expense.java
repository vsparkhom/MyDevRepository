package vlpa.expman.model;

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

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
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
