package vlpa.expman.model;

import java.util.Date;

public class Expense {

    private long id;
    private String name;
    private Date date;
    private double amount;
    private Category category;
    private String bank;
    private String description;

    public Expense(long id, String name, Date date, double amount, Category category) { //TODO: use Builder
        this.id = id;
        this.name = name;
        this.date = date;
        this.amount = amount;
        this.category = category;
    }

    public long getId() {
        return id;
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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", category=[" + ((category == null) ? "null]" : (category.getId() + ", " + category.getName() + "]")) +
                ", bank=" + bank +
                ", description=" + description +
                '}';
    }
}
