package com.perscab.model;

import java.math.BigInteger;
import java.util.Date;

public class Payment {

    private BigInteger id;
    private Date periodStart;
    private Date periodEnd;
    private Date dueDate;
    private double sum;
    private String status;

    public Payment(BigInteger id) {
        this.id = id;
    }

    public Payment(BigInteger id, Date periodStart, Date periodEnd, Date dueDate, double sum, String status) {
        this.id = id;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.dueDate = dueDate;
        this.sum = sum;
        this.status = status;
    }

    public BigInteger getId() {
        return id;
    }

    public Date getPeriodStart() {
        return periodStart;
    }

    public void setPeriodStart(Date periodStart) {
        this.periodStart = periodStart;
    }

    public Date getPeriodEnd() {
        return periodEnd;
    }

    public void setPeriodEnd(Date periodEnd) {
        this.periodEnd = periodEnd;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", periodStart=" + periodStart +
                ", periodEnd=" + periodEnd +
                ", dueDate=" + dueDate +
                ", sum=" + sum +
                ", status='" + status + '\'' +
                '}';
    }
}
