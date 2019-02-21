package vlpa.expman.controller;

import vlpa.expman.controller.imprt.BankType;
import vlpa.expman.dao.category.CategoriesRepository;
import vlpa.expman.dao.expense.ExpensesRepository;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.ImportPattern;
import vlpa.expman.model.PatternType;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportProcessor {

    public static final String ANY_SYMBOL_TEMPLATE = "%";
    private static final String ANY_SYMBOL_SUBSTITUTE = "(.*)";

    private ExpensesRepository expensesRepository = new ExpensesRepository();
    private CategoriesRepository categoriesRepository = new CategoriesRepository();

    public void importExpenses(String fileName, BankType bankType) {
        Collection<Expense> expenses = bankType.getDataImporter().importExpensesFromFile(fileName);
        sortExpensesByCategories(expenses);
        storeExpenses(expenses);
    }

    private void sortExpensesByCategories(Collection<Expense> expenses) {
        List<ImportPattern> importPatternsList = categoriesRepository.getAllPatterns();
        for (Iterator<Expense> iterator = expenses.iterator(); iterator.hasNext();) {
            Expense e = iterator.next();
            String expenseName = e.getName().toUpperCase();
            Category c = null;
            for (ImportPattern ip : importPatternsList) {
                String patternText = ip.getText().replaceAll(ANY_SYMBOL_TEMPLATE, ANY_SYMBOL_SUBSTITUTE);
                Pattern pattern = Pattern.compile(patternText);
                Matcher m = pattern.matcher(expenseName);
                if (m.find()) {
                    if (PatternType.SKIP.equals(ip.getType())) {
                        System.out.println("Skipping " + e + " (pattern: " + ip + ")");
                        iterator.remove();
                    } else {
                        c = ip.getCategory();
                    }
                    break;
                }
            }
            if (c == null) {
                e.setCategory(categoriesRepository.getUnknownCategory());
            } else {
                e.setCategory(c);
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
