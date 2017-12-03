package vlpa.expman.controller;

import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.dao.ExpenseManagerDAO;
import vlpa.expman.model.dao.ExpensesDAO;
import vlpa.expman.model.dao.FakeExpenseManagerDAOImpl;

import java.util.Collection;

public abstract class AbstractDataProcessor implements DataProcessor {

    private static final String UNKNOWN_CATEGORY_NAME = "Unknown";

    private ExpenseManagerDAO dao = FakeExpenseManagerDAOImpl.getInstance();
    private SortExpensesConfig sortExpensesConfig = SortExpensesConfig.getInstance();

    @Override
    public Collection<Expense> importExpenses(String fileName) {
        Collection<Expense> expenses = getDataFromFile(fileName);
        sortExpensesByCategories(expenses);
        storeImportedData(expenses);
        return expenses;
    }

    protected void storeImportedData(Collection<Expense> expenses) {
        getDAO().saveExpenses(expenses);
    }

    protected abstract Collection<Expense> getDataFromFile(String fileName);

    protected void sortExpensesByCategories(Collection<Expense> expenses) {
        for (Expense e : expenses) {
            Category c = sortExpensesConfig.getConfig().get(e.getName());
            e.setCategory((c == null) ? getUnknownCategory() : c);
        }
    }

    protected ExpenseManagerDAO getDAO() {
        return dao;
    }

    private Category getUnknownCategory() {
        for (Category c : getDAO().getAllCategories()) {
            if (UNKNOWN_CATEGORY_NAME.equals(c.getName())) {
                return c;
            }
        }
        return null;
    }

}
