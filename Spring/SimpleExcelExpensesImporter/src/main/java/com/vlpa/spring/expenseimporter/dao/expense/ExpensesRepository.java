package com.vlpa.spring.expenseimporter.dao.expense;

import com.vlpa.spring.expenseimporter.model.Expense;
import com.vlpa.spring.expenseimporter.model.ImportRequest;

import java.util.*;

public class ExpensesRepository {

//    private static final boolean DEPOSIT_ALLOWED = false;

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
//    public void addImportHistoryRecord(Date start, Date end, BankType bankType) {
//        expensesDAO.addImportHistoryRecord(start, end, bankType);
//
//    }
}
