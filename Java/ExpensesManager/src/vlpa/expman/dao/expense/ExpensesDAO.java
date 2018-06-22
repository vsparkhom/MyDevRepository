package vlpa.expman.dao.expense;


import vlpa.expman.model.Expense;

import java.util.List;

public interface ExpensesDAO {

    List<Expense> queryExpenses(String query);

    void addExpense(Expense e);

    void removeExpense(long expenseId);

    void updateExpense(Expense e);
}
