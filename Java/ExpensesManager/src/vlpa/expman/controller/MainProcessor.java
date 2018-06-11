package vlpa.expman.controller;

import vlpa.expman.controller.imprt.CsvDataImporter;
import vlpa.expman.dao.category.CategoriesRepository;
import vlpa.expman.dao.connection.ConnectionManager;
import vlpa.expman.dao.expense.ExpensesRepository;
import vlpa.expman.dao.report.ExpenseReportRepository;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.ExpensesReport;

import java.sql.Connection;
import java.sql.SQLException;
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

    public void importExpenses(String fileName) {
        Collection<Expense> expenses = CsvDataImporter.getInstance().importExpensesFromFile(fileName);
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            sortExpensesByCategories(conn, expenses);
            storeImportedData(conn, expenses);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    protected void sortExpensesByCategories(Connection conn, Collection<Expense> expenses) throws SQLException {
        Map<String, Category> configMap = expensesRepository.getExpensesMapping(conn);
        for (Expense e : expenses) {
            Category c = configMap.get(e.getName());//TODO: regexp
            e.setCategory((c == null) ? getUnknownCategory() : c);
        }
    }

    //TODO: remove after testing
    public Map<String, Category> getExpensesMapping() {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            return expensesRepository.getExpensesMapping(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return null;
    }

    protected void storeImportedData(Connection conn, Collection<Expense> expenses) {
        expensesRepository.saveExpenses(conn, expenses);
    }

    //TODO: re-implement to get Unknown category from DB by id
    protected Category getUnknownCategory() {
        for (Category c : getAllCategories()) {
            if ("Unknown".equals(c.getName())) {
                return c;
            }
        }
        return null;
    }

}
