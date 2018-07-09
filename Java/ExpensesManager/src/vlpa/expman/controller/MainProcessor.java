package vlpa.expman.controller;

import vlpa.expman.dao.category.CategoriesRepository;
import vlpa.expman.dao.expense.ExpensesRepository;
import vlpa.expman.dao.report.ExpenseReportRepository;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.ExpensesReport;
import vlpa.expman.model.ImportPattern;

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

    public  Category getCategoryById(long categoryId) {
        return categoriesRepository.getCategoryById(categoryId);
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

    public void addExpense(Expense e) {
        expensesRepository.addExpense(e);
    }

    public void removeExpense(long expenseId) {
        expensesRepository.removeExpense(expenseId);
    }

    public void updateExpense(Expense e) {
        expensesRepository.updateExpense(e);
    }

    public List<ImportPattern> getAllPatterns() {
        return categoriesRepository.getAllPatterns();
    }

    public void addPattern(String text, Category category) {
        categoriesRepository.addPattern(text, category);
    }

    public void removePattern(long id) {
        categoriesRepository.removePattern(id);
    }

    public void updatePattern(ImportPattern pattern) {
        categoriesRepository.updatePattern(pattern);
    }

}
