package vlpa.spring.expman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlpa.spring.expman.controller.period.Period;
import vlpa.spring.expman.controller.period.PeriodHolder;
import vlpa.spring.expman.dao.CategoryDAO;
import vlpa.spring.expman.dao.ExpenseDAO;
import vlpa.spring.expman.entity.Category;
import vlpa.spring.expman.entity.Expense;
import vlpa.spring.expman.entity.ExpenseReport;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseManagerService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ExpenseDAO expenseDAO;

    @Transactional
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    @Transactional
    public Category getCategoryById(int categoryId) {
        return categoryDAO.getCategoryById(categoryId);
    }

    public Category calculateSummary(List<Category> allCategories) {
        Category summary = new Category("Summary");
        ExpenseReport totalExpenseReport = new ExpenseReport();
        summary.setExpenseReport(totalExpenseReport);

        for (Category category : allCategories) {
            summary.setLimit(summary.getLimit() + category.getLimit());
            totalExpenseReport.addAmount(category.getCurrentAmount());
        }
        return summary;
    }

    public List<Expense> getCategoryExpensesForCurrentPeriod(Category category) {
        List<Expense> expenses = new ArrayList<>();
        Period currentPeriod = PeriodHolder.getInstance().getCurrentPeriod();
        for (Expense expense : category.getExpenses()) {//TODO: replace with streams (filter)
            if (expense.getDate().after(currentPeriod.getStartDate()) && expense.getDate().before(currentPeriod.getEndDate())) {
                expenses.add(expense);
            }
        }
        return expenses;
    }
}
