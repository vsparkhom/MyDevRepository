package vlpa.expman.model.dao;

import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.ExpensesReport;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

public interface ExpensesDAO {

    Collection<Expense> getAllExpenses();

    Collection<Expense> getExpensesByCategoryId(int categoryId);

    void saveExpenses(Collection<Expense> expenses);

    Map<String, Category> getSortConfig();
}
