package vlpa.expman.dao.expense.sqlite;

import vlpa.expman.controller.ExpenseUtils;
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
                Date date = ExpenseUtils.toDate(rs.getString("date"));
                String merchant = rs.getString("merchant");
                double amount = rs.getDouble("amount");
                long categoryId = rs.getLong("category_id");
                Category fakeCategory = new Category(categoryId, null, 0);//TODO: fill category name and limit from query results
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

}
