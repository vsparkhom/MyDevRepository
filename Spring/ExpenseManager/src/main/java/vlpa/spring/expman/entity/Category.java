package vlpa.spring.expman.entity;

import vlpa.spring.expman.controller.utils.ExpenseDateUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "category_limit")
    private double limit;

    /* VLPA_NOTE: Lazy init does not work properly in this case (LazyInitializationException is thrown with
       "could not initialize proxy - no Session" message
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.EAGER)
    private List<Expense> expenses;

    @Transient
    private ExpenseReport expenseReport;

    public Category() {
    }

    public Category(String name) {
        this(0, name, 0);
    }

    public Category(int id, String name, double limit) {
        this.id = id;
        this.name = name;
        this.limit = limit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addExpense(Expense expense) {
        if (expenses == null) {
            expenses = new ArrayList<>();
        }
        expenses.add(expense);
        expense.setCategory(this);
    }

    public ExpenseReport getExpenseReport() {
        if (expenseReport == null) {
//            Period currentPeriod = PeriodHolder.getInstance().getCurrentPeriod();
//            Date startDate = currentPeriod.getStartDate();
//            Date endDate = currentPeriod.getEndDate();
//            expenseReport = new ExpenseReport(startDate, endDate, getTotalExpenseAmountByDates(startDate, endDate));
            expenseReport = new ExpenseReport(getTotalExpenseAmountForCurrentPeriod());
        }
        return expenseReport;
    }

    //TODO: check and remove unused code
//    private Date getStartDate() {
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.MONTH, -1);
//        return cal.getTime();
//    }

//    private Date getEndDate() {
//        return new Date();
//    }

    private double getTotalExpenseAmountForCurrentPeriod() {
            return getTotalExpenseAmountByDates(ExpenseDateUtils.getStartDate(), ExpenseDateUtils.getEndDate());
    }

    private double getTotalExpenseAmountByDates(Date startDate, Date endDate) {
        double sum = 0;
        for (Expense e : getExpenses()) {
            if (e.getDate().after(startDate) && e.getDate().before(endDate)) {
                sum += e.getAmount();
            }
        }
        return sum;
    }

    public void setExpenseReport(ExpenseReport expenseReport) {
        this.expenseReport = expenseReport;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", limit=" + limit +
//                ", CurrentAmount=" + getCurrentAmount() +
//                ", UsagePercent=" + getUsagePercent() +
//                ", Leftover()=" + getLeftover() +
                '}';
    }

    //utils methods

    public double getCurrentAmount() {
        return ExpenseDateUtils.round(getExpenseReport().getCurrentAmount(), 2);
    }

    public int getUsagePercent() {
        return (int) Math.ceil(ExpenseDateUtils.round(getExpenseReport().getCurrentAmount()/getLimit(), 2) * 100);
    }

    public double getLeftover() {
        return ExpenseDateUtils.round(getLimit() - getExpenseReport().getCurrentAmount(), 2);
    }
}
