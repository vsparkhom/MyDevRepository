package com.vlpa.spring.expenseimporter.model;

import java.util.Date;

public class Expense {

    private Date date;
    private String merchant;
    private double amount;
    private String category;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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
}
