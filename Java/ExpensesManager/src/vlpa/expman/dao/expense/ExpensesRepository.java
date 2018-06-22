package vlpa.expman.dao.expense;

import vlpa.expman.dao.DBQueries;
import vlpa.expman.dao.ExpenseManagerDAOFactory;
import vlpa.expman.dao.category.CategoriesRepository;
import vlpa.expman.dao.expense.spec.ExpenseSqlSpecificationGetAll;
import vlpa.expman.dao.expense.spec.ExpenseSqlSpecificationGetAllForPeriod;
import vlpa.expman.dao.expense.spec.ExpenseSqlSpecificationGetByCategoryId;
import vlpa.expman.dao.expense.spec.ExpenseSqlSpecificationGetByCategoryIdForPeriod;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ExpensesRepository {

    private static final boolean DEPOSIT_ALLOWED = false;

    private CategoriesRepository categoriesRepository = new CategoriesRepository();
    private ExpensesDAO expensesDAO = ExpenseManagerDAOFactory.ExpensesDAOFactory.getInstance();

    private boolean isDepositAllowed(Expense e) {
        return e.getAmount() > 0 || DEPOSIT_ALLOWED;
    }

    public List<Expense> getAllExpenses() {
        return expensesDAO.queryExpenses(new ExpenseSqlSpecificationGetAll().toSqlClause());
    }

    public List<Expense> getAllExpenses(Date start, Date end) {
        return getExpensesByQuery(new ExpenseSqlSpecificationGetAllForPeriod(start, end).toSqlClause());
    }

    public List<Expense> getExpensesByCategoryId(long categoryId) {
        return getExpensesByQuery(new ExpenseSqlSpecificationGetByCategoryId(categoryId).toSqlClause());
    }

    public List<Expense> getExpensesByCategoryId(long categoryId, Date start, Date end) {
        return getExpensesByQuery(new ExpenseSqlSpecificationGetByCategoryIdForPeriod(categoryId, start, end).toSqlClause());
    }

    public void saveExpenses(Connection conn, Collection<Expense> expenses) {
        //TODO: implement the method
    }

    private List<Expense> getExpensesByQuery(String query) {
        List<Expense> expensesWithEmptyCategories = expensesDAO.queryExpenses(query);
        updateCategoriesData(expensesWithEmptyCategories);
        return expensesWithEmptyCategories;
    }

    private void updateCategoriesData(List<Expense> expenses) {
        for (Expense e : expenses) {
            Category currentCategory = e.getCategory();
            Category fetchedCategory = categoriesRepository.getCategoryById(currentCategory.getId());
            currentCategory.setName(fetchedCategory.getName());
            currentCategory.setLimit(fetchedCategory.getLimit());
        }
    }

    //TODO: move to DAO
    public Map<String, Category> getExpensesMapping(Connection conn) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.GET_EXPENSES_MAPPING);
        ResultSet rs = pstm.executeQuery();

        Map<String, Category> mapping = new HashMap<>();
        while (rs.next()) {
            String pattern = rs.getString("pattern");
            long categoryId = rs.getLong("category_id");
            Category category = categoriesRepository.getCategoryById(categoryId);
            mapping.put(pattern, category);
        }
        return mapping;
    }

    public void addExpense(Expense e) {
        expensesDAO.addExpense(e);
    }

    public void removeExpense(long expenseId) {
        expensesDAO.removeExpense(expenseId);
    }

    public void updateExpense(Expense e) {
        expensesDAO.updateExpense(e);
    }
}
