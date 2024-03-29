package vlpa.expman.dao.expense;

import vlpa.expman.controller.imprt.BankType;
import vlpa.expman.dao.ExpenseManagerDAOFactory;
import vlpa.expman.dao.category.CategoriesRepository;
import vlpa.expman.dao.expense.spec.ExpenseSqlSpecificationGetAll;
import vlpa.expman.dao.expense.spec.ExpenseSqlSpecificationGetAllForPeriod;
import vlpa.expman.dao.expense.spec.ExpenseSqlSpecificationGetByCategoryId;
import vlpa.expman.dao.expense.spec.ExpenseSqlSpecificationGetByCategoryIdForPeriod;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;

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

    public void addExpenses(Collection<Expense> expenses) {
        addExpenses(expenses, false);
    }

    public void addExpenses(Collection<Expense> expenses, boolean isMergeAllowed) {
        for (Expense exp : expenses) {
            addExpense(exp, isMergeAllowed);
        }
    }

    public void addExpense(Expense e) {
        addExpense(e, false);
    }

    public void addExpense(Expense e, boolean mergeAllowed) {
        if (mergeAllowed) {
            expensesDAO.mergeExpense(e);
        } else {
            expensesDAO.addExpense(e);
        }
    }

    public void removeExpense(long expenseId) {
        expensesDAO.removeExpense(expenseId);
    }

    public void updateExpense(Expense e) {
        expensesDAO.updateExpense(e);
    }

    public void addImportHistoryRecord(Date start, Date end, BankType bankType) {
        expensesDAO.addImportHistoryRecord(start, end, bankType);

    }
}
