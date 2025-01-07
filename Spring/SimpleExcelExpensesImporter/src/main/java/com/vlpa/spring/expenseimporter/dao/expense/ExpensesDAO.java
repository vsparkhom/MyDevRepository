package com.vlpa.spring.expenseimporter.dao.expense;

import com.vlpa.spring.expenseimporter.model.Expense;
import com.vlpa.spring.expenseimporter.model.ImportRequest;

import java.util.List;

public interface ExpensesDAO {

    List<Expense> readExpenses(ImportRequest request);

    void saveExpenses(List<Expense> expenses, ImportRequest request);

    void removeExpenses(ImportRequest request);

}
