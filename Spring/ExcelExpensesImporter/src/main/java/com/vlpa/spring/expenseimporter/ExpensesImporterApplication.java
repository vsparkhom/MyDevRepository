package com.vlpa.spring.expenseimporter;

import com.vlpa.spring.expenseimporter.importers.PcfCreditCardImporter;
import com.vlpa.spring.expenseimporter.importers.TdCreditCardImporter;
import com.vlpa.spring.expenseimporter.importers.TdDebitCardImporter;
import com.vlpa.spring.expenseimporter.model.*;
import com.vlpa.spring.expenseimporter.repository.CsvRepository;
import com.vlpa.spring.expenseimporter.repository.ExcelRepository;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static com.vlpa.spring.expenseimporter.LoggerUtils.*;

public class ExpensesImporterApplication {

    private static final String UNKNOWN_CATEGORY = "Unknown - To sort manually";
    private static final Map<String, BankStatementImporter> BANK_DATA_IMPORTERS = new HashMap<>();
    private static final String DATABASE_URL = "jdbc:sqlite:c:\\parkhomchuk\\Repositories\\github\\Spring\\ExcelExpensesImporter\\src\\main\\resources\\database\\expenses_caches.db";

    static {
        BANK_DATA_IMPORTERS.put(Bank.TD + "_" + CardType.CREDIT, new TdCreditCardImporter());
        BANK_DATA_IMPORTERS.put(Bank.TD + "_" + CardType.DEBIT, new TdDebitCardImporter());
        BANK_DATA_IMPORTERS.put(Bank.PCF + "_" + CardType.CREDIT, new PcfCreditCardImporter());
    }

    private ExcelRepository excelRepository;

    public void storeData(ImportRequestData requestData) throws IOException, ParseException {

        info("Start import process with following parameters:" );
        info("    Bank: " + requestData.getBank());
        info("    Card type: " + requestData.getCardType());
        info("    Begin date: " + requestData.getBeginningOfTheMonth());

        BankStatementImporter importer = getImporter(requestData);
        List<Expense> expenses = importExpensesFromExcel(requestData, importer);

        storeExpensesToDatabase(requestData, expenses);
    }

    private void storeExpensesToDatabase(ImportRequestData requestData, List<Expense> expenses) {

        int bankId = getBankId(requestData);

        for (Expense e : expenses) {
            String insertStatement = "INSERT INTO Expenses(merchant, amount, purchase_date, bank_id) VALUES(?,?,?,?)";
            try (Connection conn = DriverManager.getConnection(DATABASE_URL);
                 PreparedStatement pstmt = conn.prepareStatement(insertStatement)) {
                pstmt.setString(1, e.getMerchant());
                pstmt.setDouble(2, e.getAmount());
                pstmt.setDate(3, new java.sql.Date(e.getDate().getTime()));
                pstmt.setInt(4, bankId);
                pstmt.executeUpdate();
                info("Record inserted successfully.");
            } catch (SQLException exc) {
                System.out.println(exc.getMessage());
            }
        }

        info("All records have been stored to database.");
    }

    private int getBankId(ImportRequestData requestData) {
        String getBankByNameAndTypeQuery = String.format("SELECT * FROM Banks WHERE name = '%s' AND type = '%s'",
                requestData.getBank().getValue(), requestData.getCardType().getValue());
        debug("Execute query: " + getBankByNameAndTypeQuery);

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement pstmt = conn.prepareStatement(getBankByNameAndTypeQuery);
             ResultSet rs = pstmt.executeQuery()) {

            // Loop through the result set and print data
            while (rs.next()) {
                int bankId = rs.getInt("id");
                debug("Bank id = " + bankId + " was found");
                return bankId;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public void execute(ImportRequestData requestData) throws IOException, ParseException {

        info("Start import process with following parameters:" );
        info("    Bank: " + requestData.getBank());
        info("    Card type: " + requestData.getCardType());
        info("    Begin date: " + requestData.getBeginningOfTheMonth());

        BankStatementImporter importer = getImporter(requestData);
        List<Expense> expenses = importExpensesFromExcel(requestData, importer);

        expenses.sort(Comparator.comparing(Expense::getDate));

        debug("Selected expenses:");
        for (Expense e : expenses) {
            debug("   - e: " + e);
        }

        List<Expense> unknownExpenses = expenses.stream().filter(e -> UNKNOWN_CATEGORY.equals(e.getCategory()))
                .collect(Collectors.toList());

        info("Unknown expenses:");
        unknownExpenses.forEach(e -> info("    - " + e));

        storeExpensesIntoExcel(expenses, requestData, importer);
        
        storeUnknownExpenses(unknownExpenses, requestData);

        info( "Import process has finished" );
    }

    public List<Expense> importExpensesFromExcel(ImportRequestData requestData, BankStatementImporter importer)
            throws IOException, ParseException {
        CsvRepository csvRepository = new CsvRepository();
        ArrayList<Expense> expenses = csvRepository.readCsvFile(importer);

        Map<String, List<ExpensePattern>> patternsMapping = getPatternsMappingFromExcel();
        List<Expense> selectedExpenses = new ArrayList<>();

        debug("Expenses:");
        for (Expense expense : expenses) {
            debug("    - " + expense);
            if (expense.getAmount() != 0 && isInTimeInterval(expense.getDate(), requestData.getBeginningOfTheMonth())) {
                String matchedCategory = findCategoryByPattern(expense, patternsMapping);
                if (isEmpty(matchedCategory)) {
                    debug("         [WARNING] Empty category was found for " + expense);
                } else {
                    debug("         [MATCHED] Category: " + matchedCategory);
                    expense.setCategory(matchedCategory);
                }
                selectedExpenses.add(expense);
            } else {
                debug("");
            }

        }
        return selectedExpenses;
    }

    private boolean isEmpty(String s) {
        return s == null || "".equals(s);
    }

    protected BankStatementImporter getImporter(ImportRequestData requestData) {
        return BANK_DATA_IMPORTERS.get(requestData.getBank() + "_" + requestData.getCardType());
    }

    public Map<String, List<ExpensePattern>> getPatternsMappingFromExcel() throws IOException {
        Map<String, List<ExpensePattern>> patternsMap = getExcelRepository().readMappingFromExcel();

        debug("Patterns Mapping:");
        for (Map.Entry<String, List<ExpensePattern>> stringListEntry : patternsMap.entrySet()) {
            debug("key - " + stringListEntry.getKey());
            for (ExpensePattern expensePattern : stringListEntry.getValue()) {
                debug("   - expensePattern[" + expensePattern.getExpression() + ";" + expensePattern.getCategory() + "]");
            }
            debug();
        }
        return patternsMap;
    }

    public List<ExpenseCategory> getCategoriesFromExcel() throws IOException {
        return getExcelRepository().readCategoriesFromExcel();
    }

    private static String findCategoryByPattern(Expense expense, Map<String, List<ExpensePattern>> patterns) {
        for (Map.Entry<String, List<ExpensePattern>> stringListEntry : patterns.entrySet()) {
            for (ExpensePattern expensePattern : stringListEntry.getValue()) {
                if (expense.getMerchant().toUpperCase().contains(expensePattern.getExpression())) {
                    return expensePattern.getCategory();
                }
            }
        }
        return UNKNOWN_CATEGORY;
    }

    private static boolean isInTimeInterval(Date dateToCompare, Date beginningOfTheMonths) {
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

    protected void storeExpensesIntoExcel(List<Expense> expenses, ImportRequestData requestData,
                                          BankStatementImporter importer) throws IOException {
        getExcelRepository().storeExpensesIntoExcel(expenses, requestData, importer);
    }

    protected void storeUnknownExpenses(List<Expense> unknownExpenses, ImportRequestData requestData) throws IOException {
        getExcelRepository().storeUnknownExpenses(unknownExpenses, requestData);
    }
}
