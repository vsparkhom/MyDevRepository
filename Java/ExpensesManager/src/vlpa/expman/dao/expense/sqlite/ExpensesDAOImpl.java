package vlpa.expman.dao.expense.sqlite;

import vlpa.expman.controller.ExpenseUtils;
import vlpa.expman.dao.DBQueries;
import vlpa.expman.dao.connection.ConnectionManager;
import vlpa.expman.dao.expense.ExpensesDAO;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ExpensesDAOImpl implements ExpensesDAO {

    @Override
    public List<Expense> queryExpenses(String query) {
        System.out.println("[DEBUG]<ExpensesDAOImpl.queryExpenses> query: " + query);
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            List<Expense> expenses = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                Date date = ExpenseUtils.fromStringToDate(rs.getString("date"));
                String merchant = rs.getString("merchant");
                double amount = rs.getDouble("amount");
                long categoryId = rs.getLong("category_id");
                Category fakeCategory = new Category(categoryId, null, 0);
                Expense exp = new Expense(id, merchant, date, amount, fakeCategory);
                expenses.add(exp);
            }
            return expenses;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return Collections.emptyList();
    }

    @Override
    public void addExpense(Expense expense) {
        System.out.println("Adding expense to database... " + expense);
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.ADD_EXPENSE);
            pstm.setString(1, ExpenseUtils.fromDateToString(expense.getDate()));
            pstm.setString(2, expense.getName());
            pstm.setDouble(3, expense.getAmount());
            pstm.setLong(4, expense.getCategory().getId());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void removeExpense(long expenseId) {
        System.out.println("Removing expense with id=" + expenseId);
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.REMOVE_EXPENSE);
            pstm.setLong(1, expenseId);
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void updateExpense(Expense expense) {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.UPDATE_EXPENSE);
            pstm.setString(1, ExpenseUtils.fromDateToString(expense.getDate()));
            pstm.setString(2, expense.getName());
            pstm.setDouble(3, expense.getAmount());
            pstm.setLong(4, expense.getCategory().getId());
            pstm.setLong(5, expense.getId());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }
}
