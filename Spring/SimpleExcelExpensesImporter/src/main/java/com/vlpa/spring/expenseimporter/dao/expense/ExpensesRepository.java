package com.vlpa.spring.expenseimporter.dao.expense;

import com.vlpa.spring.expenseimporter.model.Expense;
import com.vlpa.spring.expenseimporter.model.ImportRequest;

import java.util.*;

public class ExpensesRepository {

//    private static final boolean DEPOSIT_ALLOWED = false;
//
//    private CategoriesRepository categoriesRepository = new CategoriesRepository();
    private ExpensesDAO expensesDAO = ExpensesDAOFactory.getInstance();

    public void saveExpenses(List<Expense> expenses, ImportRequest request) {
        expensesDAO.saveExpenses(expenses, request);
    }

    public void removeExpenses(ImportRequest request) {
        expensesDAO.removeExpenses(request);
    }

    public List<Expense> readExpenses(ImportRequest request) {
        return expensesDAO.readExpenses(request);
    }

//    private boolean isDepositAllowed(Expense e) {
//        return e.getAmount() > 0 || DEPOSIT_ALLOWED;
//    }
//
//    public List<Expense> getAllExpenses() {
//        return expensesDAO.queryExpenses(new ExpenseSqlSpecificationGetAll().toSqlClause());
//    }
//
//    public List<Expense> getAllExpenses(Date start, Date end) {
//        return getExpensesByQuery(new ExpenseSqlSpecificationGetAllForPeriod(start, end).toSqlClause());
//    }
//
//    public List<Expense> getExpensesByCategoryId(long categoryId) {
//        return getExpensesByQuery(new ExpenseSqlSpecificationGetByCategoryId(categoryId).toSqlClause());
//    }
//
//    public List<Expense> getExpensesByCategoryId(long categoryId, Date start, Date end) {
//        return getExpensesByQuery(new ExpenseSqlSpecificationGetByCategoryIdForPeriod(categoryId, start, end).toSqlClause());
//    }
//
//    private List<Expense> getExpensesByQuery(String query) {
//        List<Expense> expensesWithEmptyCategories = expensesDAO.queryExpenses(query);
//        updateCategoriesData(expensesWithEmptyCategories);
//        return expensesWithEmptyCategories;
//    }
//
//    private void updateCategoriesData(List<Expense> expenses) {
//        for (Expense e : expenses) {
//            Category currentCategory = e.getCategory();
//            Category fetchedCategory = categoriesRepository.getCategoryById(currentCategory.getId());
//            currentCategory.setName(fetchedCategory.getName());
//            currentCategory.setLimit(fetchedCategory.getLimit());
//        }
//    }
//
//    public void addExpenses(Collection<Expense> expenses) {
//        addExpenses(expenses, false);
//    }
//
//    public void addExpenses(Collection<Expense> expenses, boolean isMergeAllowed) {
//        for (Expense exp : expenses) {
//            addExpense(exp, isMergeAllowed);
//        }
//    }
//
//    public void addExpense(Expense e) {
//        addExpense(e, false);
//    }
//
//    public void addExpense(Expense e, boolean mergeAllowed) {
//        if (mergeAllowed) {
//            expensesDAO.mergeExpense(e);
//        } else {
//            expensesDAO.addExpense(e);
//        }
//    }
//
//    public void removeExpense(long expenseId) {
//        expensesDAO.removeExpense(expenseId);
//    }
//
//    public void updateExpense(Expense e) {
//        expensesDAO.updateExpense(e);
//    }
//
//    public void addImportHistoryRecord(Date start, Date end, BankType bankType) {
//        expensesDAO.addImportHistoryRecord(start, end, bankType);
//
//    }
}
