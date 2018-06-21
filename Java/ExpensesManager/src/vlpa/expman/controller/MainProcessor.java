package vlpa.expman.controller;

import vlpa.expman.dao.category.CategoriesRepository;
import vlpa.expman.dao.expense.ExpensesRepository;
import vlpa.expman.dao.report.ExpenseReportRepository;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.ExpensesReport;

import java.util.*;

public class MainProcessor {

    private CategoriesRepository categoriesRepository = new CategoriesRepository();
    private ExpensesRepository expensesRepository = new ExpensesRepository();
    private ExpenseReportRepository expenseReportRepository = new ExpenseReportRepository();

    private static MainProcessor instance;

    private MainProcessor() {
    }

    public static MainProcessor getInstance() {
        if (instance == null) {
            instance = new MainProcessor();
        }
        return instance;
    }

    public List<Category> getAllCategories() {
        return categoriesRepository.getAllCategories();
    }

    public Collection<Expense> getExpensesByCategoryId(long categoryId) {
        return expensesRepository.getExpensesByCategoryId(categoryId);
    }

    public Collection<Expense> getExpensesByCategoryId(long categoryId, Date start, Date end) {
        return expensesRepository.getExpensesByCategoryId(categoryId, start, end);
    }

    public ExpensesReport getExpensesReportForCategory(long id, Date start, Date end) {
        return expenseReportRepository.getExpensesReportForCategory(id, start, end);
    }

    public ExpensesReport getExpensesReportForAllCategories(Date start, Date end) {
        return expenseReportRepository.getExpensesReportForAllCategories(start, end);
    }

    public void addCategory(String name, double limit) {
        categoriesRepository.addCategory(name, limit);
    }

    public void removeCategory(long categoryId) {
        categoriesRepository.removeCategory(categoryId);
    }

    public void updateCategory(Category category) {
        categoriesRepository.updateCategory(category);
    }

}
