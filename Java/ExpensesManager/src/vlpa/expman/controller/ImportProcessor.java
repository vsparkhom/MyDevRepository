package vlpa.expman.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger LOGGER = LoggerFactory.getLogger(ImportProcessor.class);

    private ExpensesRepository expensesRepository = new ExpensesRepository();
    private CategoriesRepository categoriesRepository = new CategoriesRepository();

    public void importExpenses(String fileName, BankType bankType) {
        Collection<Expense> expenses = bankType.getDataImporter().importExpensesFromFile(fileName);
        sortExpensesByCategories(expenses);
        storeExpenses(expenses);
    }

    private void sortExpensesByCategories(Collection<Expense> expenses) {
        List<ImportPattern> importPatternsList = categoriesRepository.getAllPatterns();
        categoriesRepository.sortPatternsList(importPatternsList);

//        LOGGER.debug("Sorted patterns: ");
//        for (ImportPattern p : importPatternsList) {
//            LOGGER.debug("  - p: {}", p);
//        }

        LOGGER.debug("Import expenses: ");
        for (Iterator<Expense> iterator = expenses.iterator(); iterator.hasNext();) {
            Expense e = iterator.next();
            String expenseName = e.getName().toUpperCase();
//            LOGGER.debug("  - expenseName: {}", expenseName);
            Category c = null;
            for (ImportPattern ip : importPatternsList) {
                String patternText = ip.getText().replaceAll(ANY_SYMBOL_TEMPLATE, ANY_SYMBOL_SUBSTITUTE);
                Pattern pattern = Pattern.compile(patternText);
                Matcher m = pattern.matcher(expenseName);
                if (m.find()) {
//                    LOGGER.debug("    - match found with pattern: {}", ip);
                    if (PatternType.SKIP.equals(ip.getType())) {
                        LOGGER.debug("Skipping {} (pattern: {})", e, ip);
                        iterator.remove();
                        break;
                    } else if ((PatternType.AMOUNT.equals(ip.getType()) && ip.getAmount() == e.getAmount())
                            || PatternType.REGULAR.equals(ip.getType())) {
                        LOGGER.debug("Amount-based or regular expense was found: {} (pattern: {})", e, ip);
                        c = ip.getCategory();
                        break;
                    }
                }
            }
            if (c == null) {
                LOGGER.debug("Pattern was not found for {}. Setting Unknown category.");
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
