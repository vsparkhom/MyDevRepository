package com.vlpa.spring.expenseimporter;

import com.vlpa.spring.expenseimporter.dao.category.CategoriesRepository;
import com.vlpa.spring.expenseimporter.dao.expense.ExpensesRepository;
import com.vlpa.spring.expenseimporter.importers.BankStatementImporter;
import com.vlpa.spring.expenseimporter.importers.CibcCreditCardImporter;
import com.vlpa.spring.expenseimporter.importers.PcfCreditCardImporter;
import com.vlpa.spring.expenseimporter.importers.TdCreditCardImporter;
import com.vlpa.spring.expenseimporter.importers.TdDebitCardImporter;
import com.vlpa.spring.expenseimporter.model.*;
import com.vlpa.spring.expenseimporter.repository.CsvRepository;
import com.vlpa.spring.expenseimporter.repository.ExcelRepository;

import java.io.*;
import java.text.ParseException;
import java.util.*;

import static com.vlpa.spring.expenseimporter.LoggerUtils.*;

public class ExpensesImporterApplication {

    private static final Map<Card, BankStatementImporter> BANK_DATA_IMPORTERS = new HashMap<>();

    static {
        BANK_DATA_IMPORTERS.put(Card.TdCredit, new TdCreditCardImporter());
        BANK_DATA_IMPORTERS.put(Card.TdDebit, new TdDebitCardImporter());
        BANK_DATA_IMPORTERS.put(Card.PcfCredit, new PcfCreditCardImporter());
        BANK_DATA_IMPORTERS.put(Card.CibcCredit, new CibcCreditCardImporter());
    }

    private ExcelRepository excelRepository;
    private CategoriesRepository categoriesRepository;
    private ExpensesRepository expensesRepository;

    public void storeCategoriesToDatabase(List<Category> categories) {
        getCategoriesRepository().removeAllCategories();
        getCategoriesRepository().saveCategories(categories);
    }

    public void storeExpensesToDatabase(List<Expense> expenses, ImportRequest request) {
        getExpensesRepository().removeExpenses(request);
        getExpensesRepository().saveExpenses(expenses, request);
    }

    public List<Expense> getExpensesFromDatabase(ImportRequest request) {
        return getExpensesRepository().readExpenses(request);
    }

    public void storeExpensesIntoExcel(List<Expense> expenses, ImportRequest requestData) throws IOException {
        getExcelRepository().exportExpenses(expenses, requestData);
    }

    public List<Pattern> getPatternsMappingFromExcel(List<Category> categories) throws IOException {
        List<Pattern> patterns = getExcelRepository().readMapping(categories);

        debug("Patterns Mapping:");
        for (Pattern pattern : patterns) {
            debug("   - " + pattern);
        }
        return patterns;
    }

    public List<Category> getCategoriesFromExcel() throws IOException {
        return getExcelRepository().readCategoriesFromExcel();
    }

    public BankStatementImporter getImporter(ImportRequest requestData) {
        return BANK_DATA_IMPORTERS.get(requestData.getCard());
    }

    public List<Expense> importExpensesFromCsv(ImportRequest requestData, BankStatementImporter importer)
            throws IOException, ParseException {
        CsvRepository csvRepository = new CsvRepository();
        ArrayList<Expense> expenses = csvRepository.readExpensesFromCsvFile(importer);

        List<Category> categoriesFromExcel = getCategoriesFromExcel();
        List<Pattern> patterns = getPatternsMappingFromExcel(categoriesFromExcel);

        List<Expense> importedExpenses = new ArrayList<>();

        debug("Expenses (" + expenses.size() + "):");
        int numberOfSkippedExpenses = 0;

        for (Expense expense : expenses) {
            debug("    - " + expense);
            if (isExpenseApplicable(expense, requestData.getBeginningOfTheMonth())) {
                Pattern matchedPattern = findPatternByExpenseMerchant(expense, patterns);

                if (matchedPattern == null) {
                    warning("         [WARNING] No pattern matches the " + expense);
                    expense.setExpenseType(ExpenseType.Unknown);
                } else {
                    assignCategoryBasedOnPattern(expense, matchedPattern);
                }
                importedExpenses.add(expense);
            } else {
                warning("         [WARNING] Skip the " + expense);
                numberOfSkippedExpenses++;
            }
        }
        debug("Number of skipped expenses: " + numberOfSkippedExpenses + ", added: " + (expenses.size() - numberOfSkippedExpenses));
        return importedExpenses;
    }

    private void assignCategoryBasedOnPattern(Expense expense, Pattern matchedPattern) {
        Category matchedCategory = matchedPattern.getCategory();
        if (matchedCategory == null && matchedPattern.getExpenseType() != ExpenseType.Skip) {
            error("         [ERROR] No matching category was found for " + expense);
        } else {
            debug("         [MATCHED] Matched pattern: " + matchedPattern);
            expense.setCategory(matchedCategory);
        }
        expense.setExpenseType(matchedPattern.getExpenseType());
    }

    private boolean isExpenseApplicable(Expense expense, Date beginningOfTheMonth) {
        return expense.getAmount() != 0 && isInTimeInterval(expense.getDate(), beginningOfTheMonth);
    }

    private Pattern findPatternByExpenseMerchant(Expense expense, List<Pattern> patterns) {
        for (Pattern pattern : patterns) {
            if (expense.getMerchant().toUpperCase().contains(pattern.getExpression().toUpperCase())) {
                return pattern;
            }
        }
        return null;
    }

    private boolean isInTimeInterval(Date dateToCompare, Date beginningOfTheMonths) {
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(beginningOfTheMonths);
        endCalendar.add(Calendar.MONTH, 1);
        return dateToCompare.equals(beginningOfTheMonths)
                || (dateToCompare.after(beginningOfTheMonths) && dateToCompare.before(endCalendar.getTime()));
    }

    protected ExcelRepository getExcelRepository() {
        if (excelRepository == null) {
            excelRepository = new ExcelRepository();
        }
        return excelRepository;
    }

    protected CategoriesRepository getCategoriesRepository() {
        if (categoriesRepository == null) {
            categoriesRepository = new CategoriesRepository();
        }
        return categoriesRepository;
    }

    protected ExpensesRepository getExpensesRepository() {
        if (expensesRepository == null) {
            expensesRepository = new ExpensesRepository();
        }
        return expensesRepository;
    }
}
