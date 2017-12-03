package vlpa.expman.controller;

import vlpa.expman.model.Category;
import vlpa.expman.model.dao.ExpenseManagerDAO;
import vlpa.expman.model.dao.FakeExpenseManagerDAOImpl;

import java.util.HashMap;
import java.util.Map;

public class SortExpensesConfig {

    private Map<String, Category> config = new HashMap<>();
    private ExpenseManagerDAO dao = FakeExpenseManagerDAOImpl.getInstance();

    private SortExpensesConfig() {
        initConfig();
    }

    private void initConfig() {
        config.putAll(dao.getSortConfig());
    }

    private static class SortExpensesConfigInstanceHolder {
        public static SortExpensesConfig instance = new SortExpensesConfig();
    }

    public static SortExpensesConfig getInstance() {
        return SortExpensesConfigInstanceHolder.instance;
    }

    public Map<String, Category> getConfig() {
        return config;
    }

}
