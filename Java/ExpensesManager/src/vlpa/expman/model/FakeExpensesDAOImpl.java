package vlpa.expman.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class FakeExpensesDAOImpl implements ExpensesDAO {

    private Collection<Category> categories;
    private Collection<Expense> expenses;

    private FakeExpensesDAOImpl() {

        //init test categories
        Category cat1 = new Category(1, "cat1", 100);
        Category cat2 = new Category(2, "cat2", 200);
        Category cat3 = new Category(3, "cat3", 300);
        categories =  Arrays.asList(cat1, cat2, cat3);

        //init test expenses
        Expense exp1 = new Expense(1000, "car service", new Date(), 150, cat1);
        Expense exp2 = new Expense(1001, "gas", new Date(), 35, cat1);
        Expense exp3 = new Expense(1002, "shampoo", new Date(), 5, cat2);
        Expense exp4 = new Expense(1003, "bread", new Date(), 3.50, cat3);
        Expense exp5 = new Expense(1004, "beer", new Date(), 11.40, cat3);
        expenses = Arrays.asList(exp1, exp2, exp3, exp4, exp5);
    }

    private static class DAOInstanceHolder {
        public static FakeExpensesDAOImpl instance = new FakeExpensesDAOImpl();
    }

    public static FakeExpensesDAOImpl getInstance() {
        return DAOInstanceHolder.instance;
    }

    @Override
    public Collection<Category> getAllCategories() {
        return categories;
    }

    @Override
    public Collection<Expense> getAllExpenses() {
        return expenses;
    }

    @Override
    public Collection<Expense> getExpensesByCategoryId(int categoryId) {
        Collection<Expense> expensesForCategory = new ArrayList<>();
        for (Expense e : getAllExpenses()) {
//            System.out.println("[DEBUG]<FakeExpensesDAOImpl.getExpensesByCategoryId> e: " + e);
            if (e.getCategory().getId() == categoryId) {
                expensesForCategory.add(e);
            }
        }
        return expensesForCategory;
    }

}
