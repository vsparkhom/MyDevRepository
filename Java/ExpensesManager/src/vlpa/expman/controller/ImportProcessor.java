package vlpa.expman.controller;

import vlpa.expman.controller.imprt.TDBankCsvDataImporter;
import vlpa.expman.dao.category.CategoriesRepository;
import vlpa.expman.dao.expense.ExpensesRepository;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.ImportPattern;

import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportProcessor {

    public static final String ANY_SYMBOL_TEMPLATE = "%";
    private static final String ANY_SYMBOL_SUBSTITUTE = "(.*)";

    private ExpensesRepository expensesRepository = new ExpensesRepository();
    private CategoriesRepository categoriesRepository = new CategoriesRepository();

    public void importExpenses(String fileName) {
        Collection<Expense> expenses = TDBankCsvDataImporter.getInstance().importExpensesFromFile(fileName);
        sortExpensesByCategories(expenses);
        storeExpenses(expenses);
    }

    private void sortExpensesByCategories(Collection<Expense> expenses) {
        System.out.println("[DEBUG]<sortExpensesByCategories> START");
        List<ImportPattern> importPatternsList = categoriesRepository.getAllPatterns();
        for (Expense e : expenses) {
            System.out.println("[DEBUG]<sortExpensesByCategories> e: " + e);
            String expenseName = e.getName();
            Category c = null;
            for (ImportPattern ip : importPatternsList) {
                System.out.println("[DEBUG]<sortExpensesByCategories>   key: " + ip.getText());
                String patternText = ip.getText().replaceAll(ANY_SYMBOL_TEMPLATE, ANY_SYMBOL_SUBSTITUTE);
                System.out.println("[DEBUG]<sortExpensesByCategories>   patternText: " + patternText);
                Pattern pattern = Pattern.compile(patternText);
                Matcher m = pattern.matcher(expenseName);
                if (m.find()) {
                    c = ip.getCategory();
                    System.out.println("[DEBUG]<sortExpensesByCategories>     category found: " + c);
                    break;
                }
            }
            if (c == null) {
                e.setCategory(categoriesRepository.getUnknownCategory());
                System.out.println("[DEBUG]<sortExpensesByCategories> Unknown category set");
            } else {
                e.setCategory(c);
                System.out.println("[DEBUG]<sortExpensesByCategories> category set: " + c);
            }
        }
    }

    /* This methods assumes that there can't be more then one transaction on the same day for the same merchant
       Otherwise only first transaction will be saved and others will be skipped.
     */
    private void storeExpenses(Collection<Expense> expenses) {
        expensesRepository.addExpenses(expenses, true);
    }

}
