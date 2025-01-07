package com.vlpa.spring.expenseimporter;

import com.vlpa.spring.expenseimporter.dao.category.CategoriesRepository;
import com.vlpa.spring.expenseimporter.dao.expense.ExpensesRepository;
import com.vlpa.spring.expenseimporter.importers.BankStatementImporter;
import com.vlpa.spring.expenseimporter.importers.PcfCreditCardImporter;
import com.vlpa.spring.expenseimporter.importers.TdCreditCardImporter;
import com.vlpa.spring.expenseimporter.importers.TdDebitCardImporter;
import com.vlpa.spring.expenseimporter.model.*;
import com.vlpa.spring.expenseimporter.repository.CsvRepository;
import com.vlpa.spring.expenseimporter.repository.ExcelRepository;

import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static com.vlpa.spring.expenseimporter.LoggerUtils.*;

public class ExpensesImporterApplication {

    private static final Map<Card, BankStatementImporter> BANK_DATA_IMPORTERS = new HashMap<>();
    private static final String DATABASE_URL = "jdbc:sqlite:c:\\parkhomchuk\\Repositories\\github\\Spring\\SimpleExcelExpensesImporter\\src\\main\\resources\\database\\expenses_importer.db";

    static {
        BANK_DATA_IMPORTERS.put(Card.TdCredit, new TdCreditCardImporter());
        BANK_DATA_IMPORTERS.put(Card.TdDebit, new TdDebitCardImporter());
        BANK_DATA_IMPORTERS.put(Card.PcfCredit, new PcfCreditCardImporter());
        //TODO: add CIBC bank importer
    }

    private ExcelRepository excelRepository;
    private CategoriesRepository categoriesRepository;
    private ExpensesRepository expensesRepository;

    public void storeCategoriesToDatabase(List<Category> categories) {
        getCategoriesRepository().removeAllCategories();
        getCategoriesRepository().saveCategories(categories);
    }

//    public void storeData(ImportRequest requestData) throws IOException, ParseException {
//
//        info("Start import process with following parameters:" );
//        info("    Bank: " + requestData.getBank());
//        info("    Card type: " + requestData.getCardType());
//        info("    Begin date: " + requestData.getBeginningOfTheMonth());
//
//        BankStatementImporter importer = getImporter(requestData);
//        List<Expense> expenses = importExpensesFromCsv(requestData, importer);
//
//        storeExpensesToDatabase(requestData, expenses);
//    }

    protected void storeExpensesToDatabase(List<Expense> expenses, ImportRequest request) {
        getExpensesRepository().removeExpenses(request);
        getExpensesRepository().saveExpenses(expenses, request);
    }

    protected List<Expense> getExpensesFromDatabase(ImportRequest request) {
        return getExpensesRepository().readExpenses(request);
    }

//    private int getBankId(ImportRequest requestData) {
//        String getBankByNameAndTypeQuery = String.format("SELECT * FROM Banks WHERE name = '%s' AND type = '%s'",
//                requestData.getCard().getBank().name(), requestData.getCard().getCardType().name());
//        debug("Execute query: " + getBankByNameAndTypeQuery);
//
//        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
//             PreparedStatement pstmt = conn.prepareStatement(getBankByNameAndTypeQuery);
//             ResultSet rs = pstmt.executeQuery()) {
//
//            // Loop through the result set and print data
//            while (rs.next()) {
//                int bankId = rs.getInt("id");
//                debug("Bank id = " + bankId + " was found");
//                return bankId;
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return 0;
//    }

//    public void execute(ImportRequest requestData) throws IOException, ParseException {
//
//        info("Start import process with following parameters:" );
//        info("    Bank: " + requestData.getCard().getBank());
//        info("    Card type: " + requestData.getCard().getCardType());
//        info("    Begin date: " + requestData.getBeginningOfTheMonth());
//
//        BankStatementImporter importer = getImporter(requestData);
//        List<Expense> allExpenses = importExpensesFromCsv(requestData, importer);
//
//        allExpenses.sort(Comparator.comparing(Expense::getDate));
//
//        debug("All selected expenses:");
//        for (Expense e : allExpenses) {
//            debug("   - e: " + e);
//        }
//
//        List<Expense> matchedExpenses = allExpenses.stream().filter(e -> e.getCategory() != null)
//                .collect(Collectors.toList());
//
//        info("Matched expenses:");
//        matchedExpenses.forEach(e -> info("    - " + e));
//
//        List<Expense> unknownExpenses = allExpenses.stream().filter(e -> e.getCategory() == null)
//                .collect(Collectors.toList());
//
//        info("Unknown expenses:");
//        unknownExpenses.forEach(e -> info("    - " + e));
//
//        // TODO: rework: store all expenses to database and create a separate method for transferring expenses from
//        // database to a separate excel spreadsheet
//
//        storeExpensesIntoExcel(matchedExpenses, requestData, importer);
//
//        storeUnknownExpenses(unknownExpenses, requestData);
//
//        info( "Import process has finished" );
//    }

    public List<Expense> importExpensesFromCsv(ImportRequest requestData, BankStatementImporter importer)
            throws IOException, ParseException {
        CsvRepository csvRepository = new CsvRepository();
        ArrayList<Expense> expenses = csvRepository.readCsvFile(importer);

        List<Category> categoriesFromExcel = getCategoriesFromExcel();
        List<Pattern> patterns = getPatternsMappingFromExcel(categoriesFromExcel);

        List<Expense> selectedExpenses = new ArrayList<>();

        debug("Expenses:");
        for (Expense expense : expenses) {
            debug("    - " + expense);
            if (expense.getAmount() != 0 && isInTimeInterval(expense.getDate(), requestData.getBeginningOfTheMonth())) {
                Category matchedCategory = findCategoryByPattern(expense, patterns);
                if (matchedCategory == null) {
                    warning("         [WARNING] No matching category was found for " + expense);
                } else {
                    debug("         [MATCHED] Category: " + matchedCategory);
                    expense.setCategory(matchedCategory);
                }
                selectedExpenses.add(expense);
            } else {
                warning("         [WARNING] Skip the expense");
            }

        }
        return selectedExpenses;
    }

//    private boolean isEmpty(String s) {
//        return s == null || "".equals(s);
//    }

    protected BankStatementImporter getImporter(ImportRequest requestData) {
        return BANK_DATA_IMPORTERS.get(requestData.getCard());
    }

    public List<Pattern> getPatternsMappingFromExcel(List<Category> categories) throws IOException {
        List<Pattern> patterns = getExcelRepository().readMapping(categories);

        debug("Patterns Mapping:");
        for (Pattern pattern : patterns) {
            debug("   - " + pattern);
            debug();
        }
        return patterns;
    }

    public List<Category> getCategoriesFromExcel() throws IOException {
        return getExcelRepository().readCategoriesFromExcel();
    }

    private Category findCategoryByPattern(Expense expense, List<Pattern> patterns) {
        for (Pattern pattern : patterns) {
            if (expense.getMerchant().toUpperCase().contains(pattern.getExpression().toUpperCase())) {
                return pattern.getCategory();
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

    protected void storeExpensesIntoExcel(List<Expense> expenses, ImportRequest requestData) throws IOException {
        getExcelRepository().exportExpenses(expenses, requestData);
    }
}
