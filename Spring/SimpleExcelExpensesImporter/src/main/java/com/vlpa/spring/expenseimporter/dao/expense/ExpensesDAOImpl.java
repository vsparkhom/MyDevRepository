package com.vlpa.spring.expenseimporter.dao.expense;

import com.vlpa.spring.expenseimporter.ExpenseUtils;
import com.vlpa.spring.expenseimporter.dao.connection.ConnectionManager;
import com.vlpa.spring.expenseimporter.dao.exception.ExpensesDatabaseOperationException;
import com.vlpa.spring.expenseimporter.model.Category;
import com.vlpa.spring.expenseimporter.model.Expense;
import com.vlpa.spring.expenseimporter.model.ImportRequest;
import com.vlpa.spring.expenseimporter.model.TopCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import static com.vlpa.spring.expenseimporter.LoggerUtils.*;
import static com.vlpa.spring.expenseimporter.dao.SqlQueries.ADD_EXPENSE;
import static com.vlpa.spring.expenseimporter.dao.SqlQueries.GET_ALL_EXPENSES;
import static com.vlpa.spring.expenseimporter.dao.SqlQueries.REMOVE_ALL_EXPENSES;

public class ExpensesDAOImpl implements ExpensesDAO {

    @Override
    public void saveExpenses(List<Expense> expenses, ImportRequest request) {
        info("Number of expenses to add: " + expenses.size());
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(ADD_EXPENSE);

            for (Expense e : expenses) {
                info("Import " + e);
                pstm.setString(1, e.getMerchant());
                pstm.setDouble(2, e.getAmount());
                pstm.setString(3, ExpenseUtils.fromDateToString(e.getDate()));
                if (e.getCategory() != null) {//TODO: handle unknown expenses where category is null
                    pstm.setLong(4, e.getCategory().getId());
                } else {
                    pstm.setLong(4, 0);
                }
                pstm.setString(5, request.getCard().name());
                pstm.executeUpdate();
            }

            info("All expenses have been imported successfully");

        } catch (Exception e) {
            error("Expense can't be added due to error: " + e.getMessage());
            throw new ExpensesDatabaseOperationException(e);
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void removeExpenses(ImportRequest request) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(request.getBeginningOfTheMonth());
        int monthNumber = calendar.get(Calendar.MONTH) + 1;
        info("Removing all expenses for monthNumber = " + monthNumber);
        debug("request.getBeginningOfTheMonth: " + request.getBeginningOfTheMonth());

        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(REMOVE_ALL_EXPENSES);
            pstm.setString(1, ExpenseUtils.fromDateToString(request.getBeginningOfTheMonth()));

            calendar.add(Calendar.MONTH, 1);
            debug("End Date: " + calendar.getTime());
            pstm.setString(2, ExpenseUtils.fromDateToString(calendar.getTime()));

            pstm.setString(3, request.getCard().name());

            debug("Statement: " + pstm);

            pstm.executeUpdate();
        } catch (Exception e) {
            error("Expense can't be removed due to error: " + e);
            throw new ExpensesDatabaseOperationException(e);
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public List<Expense> readExpenses(ImportRequest request) {
        info("Getting all the expenses based on the " +  request);
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(GET_ALL_EXPENSES);

            pstm.setString(1, ExpenseUtils.fromDateToString(request.getBeginningOfTheMonth()));

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(request.getBeginningOfTheMonth());
            calendar.add(Calendar.MONTH, 1);
            pstm.setString(2, ExpenseUtils.fromDateToString(calendar.getTime()));

            ResultSet rs = pstm.executeQuery();

            List<Expense> expenses = new ArrayList<>();
            while (rs.next()) {
                String merchant = rs.getString("merchant");
                double amount = rs.getDouble("amount");
                Date date = ExpenseUtils.fromStringToDate(rs.getString("purchase_date"));

                long categoryId = rs.getLong("category_id");
                String categoryName = rs.getString("category_name");
                String categoryType = rs.getString("category_type");

                long parentCategoryId = rs.getLong("p_category_id");
                String parentCategoryName = rs.getString("p_category_name");
                Category parentCategory = new Category(parentCategoryId, parentCategoryName, null, null);

                TopCategory topCategory = categoryType == null ? null : TopCategory.resolve(categoryType);
                Category category = new Category(categoryId, categoryName, parentCategory, topCategory);

                Expense exp = Expense.builder()
                        .setMerchant(merchant)
                        .setAmount(amount)
                        .setDate(date)
                        .setCategory(category)
                        .build();
                expenses.add(exp);
            }
            return expenses;
        } catch (Exception e) {
            error("Query can't be execute due to error: " + e);
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return Collections.emptyList();
    }

}
