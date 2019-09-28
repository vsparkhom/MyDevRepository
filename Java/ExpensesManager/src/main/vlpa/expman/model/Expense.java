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

    private Expense() {
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

    public static Builder builder() {
        return new Expense().new Builder();
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

    public class Builder {

        private Builder() {
        }

        public Expense build() {
            return Expense.this;
        }

        public Builder setId(long id) {
            Expense.this.id = id;
            return this;
        }

        public Builder setCategory(Category category) {
            Expense.this.category = category;
            return this;
        }

        public Builder setAmount(double amount) {
            Expense.this.amount = amount;
            return this;
        }

        public Builder setDate(Date date) {
            Expense.this.date = date;
            return this;
        }

        public Builder setName(String name) {
            Expense.this.name = name;
            return this;
        }

        public Builder setBank(String bank) {
            Expense.this.bank = bank;
            return this;
        }

        public Builder setDescription(String description) {
            Expense.this.description = description;
            return this;
        }
    }
}
