package com.vlpa.spring.expenseimporter;

import com.vlpa.spring.expenseimporter.model.Expense;
import com.vlpa.spring.expenseimporter.model.ExpenseCategory;
import com.vlpa.spring.expenseimporter.model.ExpensePattern;
import com.vlpa.spring.expenseimporter.repository.CsvRepository;
import com.vlpa.spring.expenseimporter.repository.ExcelRepository;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExpensesImporterApplication {

    private final static String INPUT_DATE_PATTERN = "dd-MM-yyyy";

    private ExcelRepository excelRepository;

    public void execute(String bank, String cardType, String monthBeginningDate) throws IOException, ParseException {

        SimpleDateFormat dateFormatter = new SimpleDateFormat(INPUT_DATE_PATTERN);
        Date beginningOfTheMonth = dateFormatter.parse(monthBeginningDate);

        System.out.println("Start import process with following parameters:" );
        System.out.println("    Bank: " + bank);
        System.out.println("    Card type: " + cardType);
        System.out.println("    Begin date: " + beginningOfTheMonth);

        List<Expense> expenses = importExpenses(beginningOfTheMonth);
        expenses.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));

        System.out.println("Selected expenses:");
        for (Expense e : expenses) {
            System.out.println("   - e: " + e);
        }

        storeExpensesIntoExcel(expenses, beginningOfTheMonth);

        System.out.println( "Import process has finished" );
    }

    public List<Expense> importExpenses(Date beginningOfTheMonth) throws IOException, ParseException {
        CsvRepository csvRepository = new CsvRepository();
        ArrayList<Expense> expenses = csvRepository.readCsvFile(ApplicationConfig.CSV.CSV_FILE, new TdCreditCardImporter());

        Map<String, List<ExpensePattern>> patternsMapping = getPatternsMappingFromExcel();
        List<Expense> selectedExpenses = new ArrayList<>();

        System.out.println("expenses:");
        for (Expense expense : expenses) {
            if (expense.getAmount() != 0 && isInTimeInterval(expense.getDate(), beginningOfTheMonth)) {
                String categoryByPattern = findCategoryByPattern(expense, patternsMapping);
                System.out.println("[FILTERED]");
                if (categoryByPattern != null && !"".equals(categoryByPattern)) {
                    System.out.print("     [MATCHED: " + categoryByPattern + "]");
                    expense.setCategory(categoryByPattern);
                }
                selectedExpenses.add(expense);
            } else {
                System.out.print("");
            }
            System.out.println("    - " + expense);
        }
        return selectedExpenses;
    }

    public Map<String, List<ExpensePattern>> getPatternsMappingFromExcel() throws IOException {
        Map<String, List<ExpensePattern>> patternsMap = getExcelRepository().readMappingFromExcel();

        System.out.println("PatternsMapping: ");
        for (Map.Entry<String, List<ExpensePattern>> stringListEntry : patternsMap.entrySet()) {
            System.out.println("key - " + stringListEntry.getKey());
            for (ExpensePattern expensePattern : stringListEntry.getValue()) {
                System.out.println("   - expensePattern[" + expensePattern.getExpression() + ";" + expensePattern.getCategory() + "]");
            }
            System.out.println();
        }
        return patternsMap;
    }

    public List<ExpenseCategory> getCategoriesFromExcel() throws IOException {
        return getExcelRepository().readCategoriesFromExcel();
    }

    private static String findCategoryByPattern(Expense expense, Map<String, List<ExpensePattern>> patterns) {

        for (Map.Entry<String, List<ExpensePattern>> stringListEntry : patterns.entrySet()) {
//            System.out.println("         key - " + stringListEntry.getKey());
            for (ExpensePattern expensePattern : stringListEntry.getValue()) {
//                System.out.println("            - expensePattern[" + expensePattern.getExpression() + ";" + expensePattern.getCategory() + "]");
                if (expense.getMerchant().contains(expensePattern.getExpression())) {
                    return expensePattern.getCategory();
                }
            }
//            System.out.println();
        }
        return null;//TODO: return UNKNOWN category
    }

//    public static boolean areDatesEqualsIgnoringTime(Date date1, Date date2) {
//        Calendar calendar1 = new GregorianCalendar();
//        calendar1.setTime(date1);
//        Calendar calendar2 = new GregorianCalendar();
//        calendar2.setTime(date2);
//        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
//                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
//                && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
//    }

    private static boolean isInTimeInterval(Date dateToCompare, Date beginningOfTheMonths) {
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(beginningOfTheMonths);
        endCalendar.add(Calendar.MONTH, 1);
        return dateToCompare.equals(beginningOfTheMonths)
                || (dateToCompare.after(beginningOfTheMonths) && dateToCompare.before(endCalendar.getTime()));
    }

    public ExcelRepository getExcelRepository() {
        if (excelRepository == null) {
            excelRepository = new ExcelRepository();
        }
        return excelRepository;
    }

    public void storeExpensesIntoExcel(List<Expense> expenses, Date beginningOfTheMonth) throws IOException {
        getExcelRepository().storeExpensesIntoExcel(expenses, beginningOfTheMonth);

    }
}
