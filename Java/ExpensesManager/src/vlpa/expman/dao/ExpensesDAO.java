package vlpa.expman.dao;

import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

public interface ExpensesDAO {

    Collection<Expense> getAllExpenses(Connection conn) throws SQLException;

    Collection<Expense> getExpensesByCategoryId(Connection conn, long categoryId) throws SQLException;

    void saveExpenses(Connection conn, Collection<Expense> expenses);

    Map<String, Category> getExpensesMapping(Connection conn) throws SQLException;
}
