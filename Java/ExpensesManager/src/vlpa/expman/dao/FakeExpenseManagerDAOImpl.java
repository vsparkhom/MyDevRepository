package vlpa.expman.dao;

import vlpa.expman.model.Category;
import vlpa.expman.dao.exception.CategoryNotFoundException;
import vlpa.expman.model.Expense;
import vlpa.expman.model.ExpensesReport;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FakeExpenseManagerDAOImpl implements ExpenseManagerDAO {

    private static final boolean DEPOSIT_ALLOWED = false;
    private Collection<Category> categories;
    private Collection<Expense> expenses;
    private Map<String, Category> sortExpensesConfig;

    private int categoryIdCounter = 1;
    private int expenseIdCounter = 1000;

    public FakeExpenseManagerDAOImpl() {

        //init test categories
        Category otherCategory = new Category(getNextCategoryId(), "Other", 250);
        Category unknownCategory = new Category(getNextCategoryId(), "Unknown", 250);
        Category carCategory = new Category(getNextCategoryId(), "Car", 400);
        Category homeCategory = new Category(getNextCategoryId(), "Home", 200);
        Category foodCategory = new Category(getNextCategoryId(), "Food", 300);
        Category vovaCategory = new Category(getNextCategoryId(), "Vova", 150);
        Category dogCategory = new Category(getNextCategoryId(), "Dog", 100);

        categories = Arrays.asList(carCategory, homeCategory, foodCategory,
                vovaCategory, dogCategory, otherCategory ,unknownCategory);

        //TODO: init sortExpensesConfig
        sortExpensesConfig = new HashMap<>();
        sortExpensesConfig.put("PET PLANET", dogCategory);
        sortExpensesConfig.put("REAL CANADIAN SUPERSTO", foodCategory);
        sortExpensesConfig.put("1786 GB CALGARY SETON", carCategory);
        sortExpensesConfig.put("HUMBLEBUNDLE.COM", vovaCategory);
        sortExpensesConfig.put("REAL CANADIAN LIQUOR S", vovaCategory);
        sortExpensesConfig.put("IKEA CALGARY", homeCategory);
        sortExpensesConfig.put("WWW.ALIEXPRESS.COM ", homeCategory);

        //init test expenses
        Expense exp1 = new Expense(getNextExpenseId(), "car service", new Date(), 180, carCategory);
        Expense exp2 = new Expense(getNextExpenseId(), "gas", new Date(), 200, carCategory);
        Expense exp3 = new Expense(getNextExpenseId(), "shampoo", new Date(), 55, homeCategory);
        Expense exp4 = new Expense(getNextExpenseId(), "bread", new Date(), 10, foodCategory);
        Expense exp5 = new Expense(getNextExpenseId(), "beer", new Date(), 210, foodCategory);
        expenses = new ArrayList<>();
        expenses.addAll(Arrays.asList(exp1, exp2, exp3, exp4, exp5));
    }

    private int getNextExpenseId() {
        return expenseIdCounter++;
    }

    private int getNextCategoryId() {
        return categoryIdCounter++;
    }

    @Override
    public Collection<Category> getAllCategories(Connection conn) {
        return categories;
    }

    @Override
    public Category getCategoryById(Connection conn, long categoryId) {
        for (Category c : getAllCategories(conn)) {
            if (c.getId() == categoryId) {
                return c;
            }
        }
        return null;
    }

    @Override
    public Collection<Expense> getAllExpenses(Connection conn) {
        return expenses;
    }

    @Override
    public Collection<Expense> getExpensesByCategoryId(Connection conn, long categoryId) {
        Collection<Expense> expensesForCategory = new ArrayList<>();
        for (Expense e : getAllExpenses(conn)) {
            if (e.getCategory().getId() == categoryId) {
                expensesForCategory.add(e);
            }
        }
        return expensesForCategory;
    }

    @Override
    public ExpensesReport getExpensesReportForAllCategories(Connection conn, Date start, Date end) {
        Category summary = new Category(-1, "Summary", getLimitForAllCategories());
        ExpensesReport expensesForAllCategories = new ExpensesReport(summary, start, end);
        for (Expense e : getAllExpenses(conn)) {
            Date currentExpenseDate = e.getDate();
            if (currentExpenseDate.after(start) && currentExpenseDate.before(end)) {
                expensesForAllCategories.addExpense(e);
            }
        }
        return expensesForAllCategories;
    }

    private double getLimitForAllCategories() {
        double totalLimit = 0;
        for (Category c : getAllCategories(null)) {
            totalLimit += c.getLimit();
        }
        return totalLimit;
    }

    @Override
    public ExpensesReport getExpensesReportForCategory(Connection conn, long categoryId, Date start, Date end) {
        Category category = getCategoryById(conn, categoryId);
        if (category == null) {
            throw new CategoryNotFoundException(categoryId);
        }
        ExpensesReport expensesForAllCategories = new ExpensesReport(category, start, end);
        for (Expense e : getAllExpenses(conn)) {
            Date currentExpenseDate = e.getDate();
            if (e.getCategory().getId() == categoryId && currentExpenseDate.after(start) && currentExpenseDate.before(end)
                    && isDepositAllowed(e)) {
                expensesForAllCategories.addExpense(e);
            }
        }
        return expensesForAllCategories;
    }

    private boolean isDepositAllowed(Expense e) {
        return e.getAmount() > 0 || DEPOSIT_ALLOWED;
    }

    @Override
    public void saveExpenses(Connection conn, Collection<Expense> expensesToSave) {
        expenses.addAll(expensesToSave);//TODO set ids to expenses
    }

    @Override
    public Map<String, Category> getExpensesMapping(Connection conn) {
        return sortExpensesConfig;
    }

}
