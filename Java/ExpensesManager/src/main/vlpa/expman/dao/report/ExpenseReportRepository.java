package vlpa.expman.dao.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vlpa.expman.dao.category.CategoriesRepository;
import vlpa.expman.dao.exception.CategoryNotFoundException;
import vlpa.expman.dao.expense.ExpensesRepository;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.ExpensesReport;

import java.util.Date;

public class ExpenseReportRepository {

    private final Logger LOGGER = LoggerFactory.getLogger(ExpenseReportRepository.class);

    private CategoriesRepository categoriesRepository = new CategoriesRepository();
    private ExpensesRepository expensesRepository = new ExpensesRepository();

    public ExpensesReport getExpensesReportForAllCategories(Date start, Date end) {
        LOGGER.debug("Get report for all categories for period between {} and {}", start, end);
        Category summary = new Category(-1, "Summary", categoriesRepository.getLimitForAllCategories());
        ExpensesReport expensesForAllCategories = new ExpensesReport(summary, start, end);
        expensesForAllCategories.addExpenses(expensesRepository.getAllExpenses(start, end));
        return expensesForAllCategories;
    }

    public ExpensesReport getExpensesReportForCategory(long categoryId, Date start, Date end) {
        Category category = categoriesRepository.getCategoryById(categoryId);
        LOGGER.debug("Get report for category '{} - {}' for period between {} and {}", category.getId(), category.getName(), start, end);
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
