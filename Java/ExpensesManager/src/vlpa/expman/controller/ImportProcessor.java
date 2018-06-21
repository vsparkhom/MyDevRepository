package vlpa.expman.controller;

import vlpa.expman.controller.imprt.CsvDataImporter;
import vlpa.expman.dao.category.CategoriesRepository;
import vlpa.expman.dao.connection.ConnectionManager;
import vlpa.expman.dao.expense.ExpensesRepository;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

public class ImportProcessor {

    private ExpensesRepository expensesRepository = new ExpensesRepository();
    private CategoriesRepository categoriesRepository = new CategoriesRepository();

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

    private void sortExpensesByCategories(Connection conn, Collection<Expense> expenses) throws SQLException {
        Map<String, Category> configMap = expensesRepository.getExpensesMapping(conn);
        for (Expense e : expenses) {
            Category c = configMap.get(e.getName());//TODO: regexp
            e.setCategory((c == null) ? categoriesRepository.getUnknownCategory() : c);
        }
    }

    private void storeImportedData(Connection conn, Collection<Expense> expenses) {
        expensesRepository.saveExpenses(conn, expenses);
    }
}
