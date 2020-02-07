package vlpa.expman.dao.expense.sqlite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vlpa.expman.controller.ExpenseUtils;
import vlpa.expman.controller.imprt.BankType;
import vlpa.expman.dao.DBQueries;
import vlpa.expman.dao.connection.ConnectionManager;
import vlpa.expman.dao.exception.ExpensesDatabaseOperationException;
import vlpa.expman.dao.expense.ExpensesDAO;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExpensesDAOImpl implements ExpensesDAO {

    private final Logger LOGGER = LoggerFactory.getLogger(ExpensesDAOImpl.class);

    @Override
    public List<Expense> queryExpenses(String query) {
        LOGGER.debug("Running query: {}", query);
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
                String bank = rs.getString("bank");
                String description = rs.getString("description");

                Expense exp = Expense.builder()
                        .setId(id)
                        .setName(merchant)
                        .setDate(date)
                        .setAmount(amount)
                        .setCategory(fakeCategory)
                        .setBank(bank)
                        .setDescription(description).build();
                expenses.add(exp);
            }
            return expenses;
        } catch (Exception e) {
            LOGGER.error("Query can't be execute due to error", e);
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return Collections.emptyList();
    }

    @Override
    public void addExpense(Expense expense) {
        LOGGER.info("Adding expense: {}", expense);
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.ADD_EXPENSE);
            pstm.setString(1, ExpenseUtils.fromDateToString(expense.getDate()));
            pstm.setString(2, expense.getName());
            pstm.setDouble(3, expense.getAmount());
            pstm.setLong(4, expense.getCategory().getId());
            pstm.setString(5, expense.getBank());
            pstm.setString(6, expense.getDescription());
            pstm.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Expense can't be added due to error", e);
            throw new ExpensesDatabaseOperationException(e);
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void mergeExpense(Expense expense) {
        LOGGER.info("Merging expense: {}", expense);
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.MERGE_EXPENSE);
            pstm.setString(1, ExpenseUtils.fromDateToString(expense.getDate()));
            pstm.setString(2, expense.getName());
            pstm.setString(3, ExpenseUtils.fromDateToString(expense.getDate()));
            pstm.setString(4, expense.getName());
            pstm.setDouble(5, expense.getAmount());
            pstm.setLong(6, expense.getCategory().getId());
            pstm.setString(7, expense.getBank());
            pstm.setString(8, expense.getDescription());
            pstm.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Expense can't be merged due to error", e);
            throw new ExpensesDatabaseOperationException(e);
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void removeExpense(long expenseId) {
        LOGGER.info("Removing expense with id: {}", expenseId);
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.REMOVE_EXPENSE);
            pstm.setLong(1, expenseId);
            pstm.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Expense can't be removed due to error", e);
            throw new ExpensesDatabaseOperationException(e);
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void updateExpense(Expense expense) {
        LOGGER.info("Updating expense: {}", expense);
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.UPDATE_EXPENSE);
            pstm.setString(1, ExpenseUtils.fromDateToString(expense.getDate()));
            pstm.setString(2, expense.getName());
            pstm.setDouble(3, expense.getAmount());
            pstm.setLong(4, expense.getCategory().getId());
            pstm.setString(5, expense.getBank());
            pstm.setString(6, expense.getDescription());
            pstm.setLong(7, expense.getId());
            pstm.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Expense can't be updated due to error", e);
            throw new ExpensesDatabaseOperationException(e);
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void addImportHistoryRecord(Date start, Date end, BankType bankType) {
        LOGGER.info("Adding history record for expenses import for {} account. Start date: {}, End Date: {}" + bankType.getName(), start, end);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String message = String.format("Expenses have been imported for %s account. Start date: %s, End Date: %s",
                bankType.getName(), dateFormat.format(start), dateFormat.format(end));
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.ADD_IMPORT_HISTORY_RECORD);
            pstm.setString(1, message);
            pstm.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("History record for expenses import can't be added due to error", e);
            throw new ExpensesDatabaseOperationException(e);
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }
}
