package com.vlpa.spring.expenseimporter.dao.expense;

import com.vlpa.spring.expenseimporter.dao.expense.ExpensesDAO;
import com.vlpa.spring.expenseimporter.dao.expense.ExpensesDAOImpl;

import static com.vlpa.spring.expenseimporter.LoggerUtils.warning;

public class ExpensesDAOFactory {

    private static ExpensesDAO instance;

    public static ExpensesDAO getInstance() {
        try {
            if (instance == null) {
                instance = ExpensesDAOImpl.class.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            warning("DAO object can't be instantiated due to error: " + e);
        }
        return instance;
    }
}
