package vlpa.spring.expman.entity;

import vlpa.spring.expman.controller.utils.ExpenseDateUtils;

import java.util.Date;

public class ExpenseReport {

    private Date start;
    private Date end;
    private double currentAmount;

    public ExpenseReport() {
        this(ExpenseDateUtils.getStartDate(), ExpenseDateUtils.getEndDate(), 0);
    }

    public ExpenseReport(double currentAmount) {
        this(ExpenseDateUtils.getStartDate(), ExpenseDateUtils.getEndDate(), currentAmount);
    }

    public ExpenseReport(Date start, Date end) {
        this(start, end, 0);
    }

    public ExpenseReport(Date start, Date end, double currentAmount) {
        this.start = start;
        this.end = end;
        this.currentAmount = currentAmount;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public void addAmount(double amount) {
        this.currentAmount += amount;
    }

}
