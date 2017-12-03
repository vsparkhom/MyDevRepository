package vlpa.expman.controller;

import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.dao.ExpensesDAO;
import vlpa.expman.model.dao.FakeExpensesDAOImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SortExpensesConfig {

    private Map<String, Category> config = new HashMap<>();
    private ExpensesDAO dao = FakeExpensesDAOImpl.getInstance();

    private SortExpensesConfig() {
        initConfig();
    }

    private void initConfig() {
        config.putAll(dao.getSortExpensesConfig());
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
