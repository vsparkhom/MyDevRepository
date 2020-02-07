package vlpa.expman.controller.imprt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vlpa.expman.dao.category.CategoriesRepository;
import vlpa.expman.dao.expense.ExpensesRepository;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.ImportPattern;
import vlpa.expman.model.PatternType;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportProcessor {

    public static final String ANY_SYMBOL_TEMPLATE = "%";
    private static final String ANY_SYMBOL_SUBSTITUTE = "(.*)";
    private final Logger LOGGER = LoggerFactory.getLogger(ImportProcessor.class);

    private ExpensesRepository expensesRepository;
    private CategoriesRepository categoriesRepository;

    public ImportProcessor() {
        this(new ExpensesRepository(), new CategoriesRepository());
    }

    public ImportProcessor(ExpensesRepository expensesRepository, CategoriesRepository categoriesRepository) {
        this.expensesRepository = expensesRepository;
        this.categoriesRepository = categoriesRepository;
    }

    public void importExpenses(String fileName, BankType bankType) {
        Collection<Expense> expenses = importExpensesFromFile(fileName, bankType);
        sortExpensesByCategories(expenses);
        storeExpenses(expenses);
        updateHistory(expenses, bankType);
    }

    protected void updateHistory(Collection<Expense> expenses, BankType bankType) {
        expensesRepository.addImportHistoryRecord(
                getEarliestExpense(expenses).getDate(), getLatestExpense(expenses).getDate(), bankType);
    }

    private Expense getEarliestExpense(Collection<Expense> expenses) {
        return Collections.min(expenses, Comparator.comparing(Expense::getDate));
    }

    private Expense getLatestExpense(Collection<Expense> expenses) {
        return Collections.max(expenses, Comparator.comparing(Expense::getDate));
    }

    protected Collection<Expense> importExpensesFromFile(String fileName, BankType bankType) {
        return bankType.getDataImporter().importExpensesFromFile(fileName);
    }

    protected void sortExpensesByCategories(Collection<Expense> expenses) {
        List<ImportPattern> importPatternsList = categoriesRepository.getAllPatterns();
        categoriesRepository.sortPatternsList(importPatternsList);

        LOGGER.debug("Sorted patterns: ");
        for (ImportPattern p : importPatternsList) {
            LOGGER.debug("  - p: {}", p);
        }

        LOGGER.debug("Import expenses: ");
        for (Iterator<Expense> iterator = expenses.iterator(); iterator.hasNext();) {
            Expense e = iterator.next();
            String expenseName = e.getName().toUpperCase();
            LOGGER.debug("  - expenseName: {}", expenseName);
            Category c = null;
            for (ImportPattern ip : importPatternsList) {
                String patternText = ip.getText().replaceAll(ANY_SYMBOL_TEMPLATE, ANY_SYMBOL_SUBSTITUTE);
                Pattern pattern = Pattern.compile(patternText);
                Matcher m = pattern.matcher(expenseName);
                if (m.find()) {
                    if (PatternType.SKIP.equals(ip.getType())) {
                        LOGGER.debug("Skipping {} (pattern: {})", e, ip);
                        iterator.remove();
                        break;
                    } else if ((PatternType.AMOUNT.equals(ip.getType()) && ip.getAmount() == e.getAmount())
                            || PatternType.REGULAR.equals(ip.getType())) {
                        c = ip.getCategory();
                        LOGGER.debug("Amount-based or regular expense was found for pattern: {}", ip);
                        break;
                    }
                }
            }
            if (c == null) {
                LOGGER.debug("Pattern was not found for {}. Setting Unknown category.", e);
                e.setCategory(categoriesRepository.getUnknownCategory());
            } else {
                e.setCategory(c);
                LOGGER.debug("Updated expense: {}", e);
            }
        }
        LOGGER.debug("Expenses have been sorted and categorized.");
    }

    /* This methods assumes that there can't be more then one transaction on the same day for the same merchant
       Otherwise only first transaction will be saved and others will be skipped.
     */
    protected void storeExpenses(Collection<Expense> expenses) {
        expensesRepository.addExpenses(expenses, isMergeAllowed());
    }

    private boolean isMergeAllowed() {
        return true;
    }
}
