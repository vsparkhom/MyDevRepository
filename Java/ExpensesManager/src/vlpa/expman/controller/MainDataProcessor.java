package vlpa.expman.controller;

import vlpa.expman.controller.imprt.CsvDataImporter;
import vlpa.expman.dao.ExpenseManagerDAO;
import vlpa.expman.dao.ExpenseManagerDAOFactory;
import vlpa.expman.dao.connection.ConnectionManager;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.ExpensesReport;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

public class MainDataProcessor {

    private ExpenseManagerDAO dao = ExpenseManagerDAOFactory.getInstance();

    public Collection<Category> getAllCategories() {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            return dao.getAllCategories(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return Collections.EMPTY_LIST;
    }

    public Collection<Expense> getExpensesByCategoryId(long categoryId) {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            return dao.getExpensesByCategoryId(conn, categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return Collections.EMPTY_LIST;
    }

    public ExpensesReport getExpensesReportForCategory(long id, Date start, Date end) {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            return dao.getExpensesReportForCategory(conn, id, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return null;
    }

    public ExpensesReport getExpensesReportForAllCategories(Date start, Date end) {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            return dao.getExpensesReportForAllCategories(conn, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return null;
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
        Map<String, Category> configMap = dao.getExpensesMapping(conn);
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
            return dao.getExpensesMapping(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return null;
    }

    protected void storeImportedData(Connection conn, Collection<Expense> expenses) {
        dao.saveExpenses(conn, expenses);
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
