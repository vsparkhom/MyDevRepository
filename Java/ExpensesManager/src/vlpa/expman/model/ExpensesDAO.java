package vlpa.expman.model;

import java.util.Collection;

public interface ExpensesDAO {

    Collection<Category> getAllCategories();

    Collection<Expense> getAllExpenses();

    Collection<Expense> getExpensesByCategoryId(int categoryId);
}
