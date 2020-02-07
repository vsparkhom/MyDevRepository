package vlpa.expman.dao.expense;

import vlpa.expman.controller.imprt.BankType;
import vlpa.expman.model.Expense;

import java.util.Date;
import java.util.List;

public interface ExpensesDAO {

    List<Expense> queryExpenses(String query);

    void addExpense(Expense e);

    void mergeExpense(Expense e);

    void removeExpense(long expenseId);

    void updateExpense(Expense e);

    void addImportHistoryRecord(Date start, Date end, BankType bankType);
}
