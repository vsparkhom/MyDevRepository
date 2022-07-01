package vlpa.spring.expman.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "merchant")
    private String merchant;

    @Column(name = "amount")
    private double amount;

    @Column(name = "bank")
    private String bank;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    public Expense() {
    }

    public Expense(int id, Date date, String merchant, double amount, String bank, String description, Category category) {
        this.id = id;
        this.date = date;
        this.merchant = merchant;
        this.amount = amount;
        this.bank = bank;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
                ", date=" + date +
                ", merchant='" + merchant + '\'' +
                ", amount=" + amount +
                ", bank='" + bank + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category.getId() + " - " + category.getName() +
                '}';
    }
}
