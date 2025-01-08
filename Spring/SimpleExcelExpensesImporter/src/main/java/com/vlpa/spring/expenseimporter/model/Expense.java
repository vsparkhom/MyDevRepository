package com.vlpa.spring.expenseimporter.model;

import java.util.Date;

public class Expense {

    private Date date;
    private String merchant;
    private double amount;
    private Category category;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
                "date=" + date +
                ", merchant='" + merchant + '\'' +
                ", amount=" + amount +
                ", category=" + category +
                '}';
    }

    public static Builder builder() {
        return new Expense().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Expense build() {
            return Expense.this;
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

        public Builder setMerchant(String merchant) {
            Expense.this.merchant = merchant;
            return this;
        }
    }

}
