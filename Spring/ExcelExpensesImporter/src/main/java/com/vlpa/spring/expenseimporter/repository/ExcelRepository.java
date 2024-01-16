package com.vlpa.spring.expenseimporter.repository;

import com.vlpa.spring.expenseimporter.ApplicationConfig;
import com.vlpa.spring.expenseimporter.model.Expense;
import com.vlpa.spring.expenseimporter.model.ExpenseCategory;
import com.vlpa.spring.expenseimporter.model.ExpensePattern;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class ExcelRepository {

    public List<ExpenseCategory> readCategoriesFromExcel() throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(this.getClass()
                .getClassLoader().getResource(ApplicationConfig.Excel.HOUSE_EXCEL_FILE).getFile()));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(ApplicationConfig.Excel.Tabs.CATEGORIES_TAB_INDEX);
        Iterator<Row> iterator = datatypeSheet.iterator();

        List<ExpenseCategory> categories = new LinkedList<>();
        int rowNumber = 0;
        while (iterator.hasNext()) {

            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();

            while (cellIterator.hasNext()) {
                Cell currentCell = cellIterator.next();
                if (currentCell.getColumnIndex() == 0 && !currentCell.getStringCellValue().isEmpty()) {
                    categories.add(new ExpenseCategory(currentCell.getStringCellValue()));
                }
                if (currentCell.getColumnIndex() == 1) {
                    if (currentCell.getCellType() == CellType.STRING) {
                        System.out.print(String.format("[%d;%d][%s]", rowNumber, currentCell.getColumnIndex(), currentCell.getStringCellValue()));
                        categories.get(categories.size() - 1).getSubCategories().add(currentCell.getStringCellValue());
                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
                        System.out.print(String.format("[%d;%d][%d]", rowNumber, currentCell.getColumnIndex(), currentCell.getNumericCellValue()));
                        System.out.println("WARNING: Incorrect cell format (NUMERIC). Skip the cell.");
                    } else {
                        System.out.println("WARNING: Incorrect cell format (Other). Skip the cell.");
                    }
                }
            }
            System.out.println();
            rowNumber++;
        }
        System.out.println("categories.size: " + categories.size());
        return categories;
    }

    public Map<String, List<ExpensePattern>> readMappingFromExcel() throws IOException {
        System.out.println("Load mapping - START");
        FileInputStream excelFile = new FileInputStream(new File(this.getClass().getClassLoader().getResource(ApplicationConfig.Excel.HOUSE_EXCEL_FILE).getFile()));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(ApplicationConfig.Excel.Tabs.MAPPING_TAB_INDEX);
        Iterator<Row> iterator = datatypeSheet.iterator();

        int rowNumber = 0;

        System.out.println("    Load header");
        List<String> headers = new ArrayList<>();
        //header
        if (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();

            while (cellIterator.hasNext()) {
                Cell currentCell = cellIterator.next();
                if (currentCell.getCellType() == CellType.STRING && !currentCell.getStringCellValue().isEmpty()) {
                    System.out.print(String.format("[%d;%d][%s]", rowNumber, currentCell.getColumnIndex(), currentCell.getStringCellValue()));
                    headers.add(currentCell.getStringCellValue());
                } else if (!currentCell.getStringCellValue().isEmpty()) {
                    System.out.println("WARNING: Incorrect cell format (Other). Skip the cell.");
                }
            }
        }

        System.out.println();
        System.out.println("    HEADERS: " + headers);

        Map<String, List<ExpensePattern>> patternsMap = new LinkedHashMap<>();
        //patterns
        System.out.println("    Load patterns");
        while (iterator.hasNext()) {

            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();

            while (cellIterator.hasNext()) {
                Cell currentCell = cellIterator.next();
                List<ExpensePattern> expensePatterns = getListOfExpensePatternsForCategory(patternsMap, headers.get(currentCell.getColumnIndex() / 2));

                if (currentCell.getCellType() == CellType.STRING && !currentCell.getStringCellValue().isEmpty()) {
                    if (currentCell.getColumnIndex() % 2 == 0) {
                        System.out.println(String.format("[%d;%d][%s](add expression)", rowNumber, currentCell.getColumnIndex(), currentCell.getStringCellValue()));
                        ExpensePattern currentPattern = new ExpensePattern();
                        currentPattern.setExpression(currentCell.getStringCellValue());
                        expensePatterns.add(currentPattern);
                    } else {
                        System.out.println(String.format("[%d;%d][%s](add category)", rowNumber, currentCell.getColumnIndex(), currentCell.getStringCellValue()));
                        ExpensePattern expensePattern = expensePatterns.get(expensePatterns.size() - 1);
                        expensePattern.setCategory(currentCell.getStringCellValue());
                    }
                }
            }
            System.out.println();
            rowNumber++;
        }

        System.out.println("Load mapping - END");

        return patternsMap;
    }

    private List<ExpensePattern> getListOfExpensePatternsForCategory(Map<String, List<ExpensePattern>> patternsMap, String key) {
        List<ExpensePattern> expensePatterns = patternsMap.get(key);
        if (expensePatterns == null) {
            patternsMap.put(key, new LinkedList<>());
            return patternsMap.get(key);
        }
        return expensePatterns;
    }

    public void storeExpensesIntoExcel(List<Expense> expenses, Date beginningOfTheMonth) throws IOException {

        int TAB_INDEX_PREFIX = 2;

        Calendar calendarBeginning = Calendar.getInstance();
        calendarBeginning.setTime(beginningOfTheMonth);
        int CURRENT_MONTH_TAB_INDEX = TAB_INDEX_PREFIX + calendarBeginning.get(Calendar.MONTH) + 1;
        System.out.println("CURRENT_MONTH_TAB_INDEX: " + CURRENT_MONTH_TAB_INDEX);

        int START_ROW_INDEX = 20;
        int START_COLUMN_INDEX = 8;

        System.out.println("Start cell: [" + START_ROW_INDEX + ";" + START_COLUMN_INDEX + "]");

//        LocalDate localDate = LocalDate.of(2024, 1, 1);
//
//        System.out.println("localDate.getDayOfMonth: " + localDate.getDayOfMonth());
//        System.out.println("localDate.getMonth().name: " + localDate.getMonth().name());

//        FileInputStream excelFile = new FileInputStream(new File(this.getClass().getClassLoader().getResource(ApplicationConfig.Excel.HOUSE_EXCEL_FILE).getFile()));
        FileInputStream excelFile = new FileInputStream(new File("C:\\parkhomchuk\\downloads\\Housing_2024_vlpa.xlsx"));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(CURRENT_MONTH_TAB_INDEX);

        int lastDayFilledNumber = 0;
        int sameDayRecordNumber = 0;

        for (Expense currentExpense : expenses) {

            System.out.println("currentExpense: " + currentExpense);

            Calendar tempCalendar = Calendar.getInstance();
            tempCalendar.setTime(currentExpense.getDate());
            int currentDayNumber = tempCalendar.get(Calendar.DAY_OF_MONTH);

            System.out.println("currentDayNumber: " + currentDayNumber);
            System.out.println("lastDayFilledNumber: " + lastDayFilledNumber);

            if (currentDayNumber == lastDayFilledNumber) {

                sameDayRecordNumber++;

                int currentRowIndex = START_ROW_INDEX + (currentDayNumber - 1) * 5 + sameDayRecordNumber;
                System.out.println("    add expense to the SAME day:");
                System.out.println("        - currentRowIndex: " + currentRowIndex);

                setCellsData(datatypeSheet, currentRowIndex, START_COLUMN_INDEX, currentExpense);

            } else {
                sameDayRecordNumber = 0;

                int currentRowIndex = START_ROW_INDEX + (currentDayNumber - 1) * 5 + sameDayRecordNumber;
                System.out.println("    add expense to the NEW day:");
                System.out.println("        - currentRowIndex: " + currentRowIndex);

                setCellsData(datatypeSheet, currentRowIndex, START_COLUMN_INDEX, currentExpense);

                lastDayFilledNumber = currentDayNumber;
            }
        }

//        workbook.close();

        FileOutputStream outFile =new FileOutputStream(new File("C:\\parkhomchuk\\downloads\\Housing_2024_vlpa_result.xlsx"));
        workbook.write(outFile);

        excelFile.close();
        outFile.close();

        System.out.println("Done");

    }

    private void setCellsData(Sheet datatypeSheet, int currentRowIndex, int startColumnIndex, Expense currentExpense) {

        if (currentExpense.getCategory() == null) {
            System.out.println("[WARNING] The following expense can't be matched by pattern. Expense: " + currentExpense);
            return;//TODO: add such expenses to the specific column to sort them out manually
        }

        Cell dateCell = datatypeSheet.getRow(currentRowIndex).getCell(startColumnIndex + 0 /* TODO: depends on card type */);
        dateCell.setCellValue(currentExpense.getDate());

        Cell categoryCell = datatypeSheet.getRow(currentRowIndex).getCell(startColumnIndex + 0 /* TODO: depends on card type */ + 1);
        categoryCell.setCellValue(currentExpense.getCategory());

        Cell amountCell = datatypeSheet.getRow(currentRowIndex).getCell(startColumnIndex + 0 /* TODO: depends on card type */ + 2);
        amountCell.setCellValue(currentExpense.getAmount());
    }

}
