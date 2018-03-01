package vlpa.expman.dao;

import vlpa.expman.dao.exception.CategoryNotFoundException;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.ExpensesReport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SQLiteExpenseManagerDAOImpl implements ExpenseManagerDAO {

    private static final boolean DEPOSIT_ALLOWED = false;
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private Date parseDate(String s) {
        try {
            return format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Category> getAllCategories(Connection conn) throws SQLException {

        PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.GET_ALL_CATEGORIES);
        ResultSet rs = pstm.executeQuery();

        Collection<Category> categories = new ArrayList<>();
        while (rs.next()) {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            double limit = rs.getDouble("limit");

            Category c = new Category(id, name, limit);
            categories.add(c);
        }

        return categories;
    }

    @Override
    public Category getCategoryById(Connection conn, long categoryId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.GET_CATEGORY_BY_ID);
        pstm.setLong(1, categoryId);
        ResultSet rs = pstm.executeQuery();

        Category category = null;
        if (rs.next()) {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            double limit = rs.getDouble("limit");

            category = new Category(id, name, limit);
        }

        return category;
    }

    @Override
    public ExpensesReport getExpensesReportForAllCategories(Connection conn, Date start, Date end) throws SQLException {
        Category summary = new Category(-1, "Summary", getLimitForAllCategories(conn));
        ExpensesReport expensesForAllCategories = new ExpensesReport(summary, start, end);
        for (Expense e : getAllExpenses(conn)) {
            Date currentExpenseDate = e.getDate();
            if (currentExpenseDate.after(start) && currentExpenseDate.before(end)) {
                expensesForAllCategories.addExpense(e);
            }
        }
        return expensesForAllCategories;
    }

    private double getLimitForAllCategories(Connection conn) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.GET_LIMIT_FOR_ALL_CATEGORIES);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            return rs.getDouble("total_limit");
        }

        return 0;
    }

    @Override
    public ExpensesReport getExpensesReportForCategory(Connection conn, long categoryId, Date start, Date end) throws SQLException {
        Category category = getCategoryById(conn, categoryId);
        if (category == null) {
            throw new CategoryNotFoundException(categoryId);
        }
        ExpensesReport expensesForCategory = new ExpensesReport(category, start, end);
        for (Expense e : getExpensesByCategoryId(conn, categoryId)) {
            Date currentExpenseDate = e.getDate();
            if (currentExpenseDate.after(start) && currentExpenseDate.before(end) && isDepositAllowed(e)) {
                expensesForCategory.addExpense(e);
            }
        }
        return expensesForCategory;
    }

    private boolean isDepositAllowed(Expense e) {
        return e.getAmount() > 0 || DEPOSIT_ALLOWED;
    }

    @Override
    public Collection<Expense> getAllExpenses(Connection conn) throws SQLException {

        PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.GET_ALL_EXPENSES);
        ResultSet rs = pstm.executeQuery();

        Collection<Expense> expenses = new ArrayList<>();
        while (rs.next()) {
            long id = rs.getLong("id");
            Date date = parseDate(rs.getString("date"));
            String merchant = rs.getString("merchant");
            double amount = rs.getDouble("amount");
            long categoryId = rs.getLong("category_id");

            Category category = getCategoryById(conn, categoryId);

            Expense exp = new Expense(id, merchant, date, amount, category);
            expenses.add(exp);
        }

        return expenses;
    }

    @Override
    public Collection<Expense> getExpensesByCategoryId(Connection conn, long categoryId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.GET_EXPENSES_BY_CATEGORY_ID);
        pstm.setLong(1, categoryId);
        ResultSet rs = pstm.executeQuery();

        Collection<Expense> expenses = new ArrayList<>();
        while (rs.next()) {
            long id = rs.getLong("id");
            Date date = parseDate(rs.getString("date"));
            String merchant = rs.getString("merchant");
            double amount = rs.getDouble("amount");

            Category category = getCategoryById(conn, categoryId);

            Expense exp = new Expense(id, merchant, date, amount, category);
            expenses.add(exp);
        }

        return expenses;
    }

    @Override
    public void saveExpenses(Connection conn, Collection<Expense> expenses) {
        //TODO: implement the method
    }

    @Override
    public Map<String, Category> getExpensesMapping(Connection conn) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.GET_EXPENSES_MAPPING);
        ResultSet rs = pstm.executeQuery();

        Map<String, Category> mapping = new HashMap<>();
        while (rs.next()) {
            String pattern = rs.getString("pattern");
            long categoryId = rs.getLong("category_id");

            Category category = getCategoryById(conn, categoryId);

            mapping.put(pattern, category);
        }

        return mapping;
    }
}
