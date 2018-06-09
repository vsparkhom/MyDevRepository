package vlpa.expman.dao.report;

import vlpa.expman.dao.category.CategoriesRepository;
import vlpa.expman.dao.exception.CategoryNotFoundException;
import vlpa.expman.dao.expense.ExpensesRepository;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.ExpensesReport;

import java.util.Date;

public class ExpenseReportRepository {

    private CategoriesRepository categoriesRepository = new CategoriesRepository();
    private ExpensesRepository expensesRepository = new ExpensesRepository();

    public ExpensesReport getExpensesReportForAllCategories(Date start, Date end) {
        Category summary = new Category(-1, "Summary", categoriesRepository.getLimitForAllCategories());
        ExpensesReport expensesForAllCategories = new ExpensesReport(summary, start, end);
        expensesForAllCategories.addExpenses(expensesRepository.getAllExpenses(start, end));
        return expensesForAllCategories;
    }

    public ExpensesReport getExpensesReportForCategory(long categoryId, Date start, Date end) {
        Category category = categoriesRepository.getCategoryById(categoryId);
        if (category == null) {
            throw new CategoryNotFoundException(categoryId);
        }
        ExpensesReport expensesForCategory = new ExpensesReport(category, start, end);
        for (Expense e : expensesRepository.getExpensesByCategoryId(categoryId, start, end)) {
            expensesForCategory.addExpense(e);
        }
        return expensesForCategory;
    }
}
