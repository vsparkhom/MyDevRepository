package vlpa.expman.model;

import java.util.Collections;
import java.util.Set;

public class Category {

    private int id;
    private String name;
    private double currentAmount;
    private double limit;
    private Set<String> transactionMsgs;

    public Category(int id, String name, double limit) {
        this(id, name, limit, Collections.<String>emptySet());
    }

    public Category(int id, String name, double limit, Set<String> transactionMsgs) {
        this.id = id;
        this.name = name;
        this.limit = limit;
        this.transactionMsgs = transactionMsgs;
    }

    public int getId() {
        return id;
    }

    public Set<String> getTransactionMsgs() {
        return transactionMsgs;
    }

    public void setTransactionMsgs(Set<String> transactionMsgs) {
        this.transactionMsgs = transactionMsgs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public int getUsagePercent() {
        return (int) Math.ceil((currentAmount/limit) * 100);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", limit=" + limit +
                '}';
    }

}
