package vlpa.expman.model.dao;

import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.ExpensesReport;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

public interface ExpensesDAO {

    Collection<Category> getAllCategories();

    Category getCategoryById(int categoryId);

    Collection<Expense> getAllExpenses();

    Collection<Expense> getExpensesByCategoryId(int categoryId);

    ExpensesReport getExpensesReportForAllCategories(Date start, Date end);

    ExpensesReport getExpensesReportForCategory(int categoryId, Date start, Date end);

    void saveExpenses(Collection<Expense> expenses);

    Map<String, Category> getSortExpensesConfig();
}
