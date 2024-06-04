package com.vlpa.spring.expenseimporter.repository;

import com.vlpa.spring.expenseimporter.ApplicationConfig;
import com.vlpa.spring.expenseimporter.BankStatementImporter;
import com.vlpa.spring.expenseimporter.model.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

import static com.vlpa.spring.expenseimporter.ApplicationConfig.Excel.Tabs.*;
import static com.vlpa.spring.expenseimporter.LoggerUtils.*;

public class ExcelRepository {

    private final String templateFileName = "c:\\parkhomchuk\\Repositories\\github\\Spring\\ExcelExpensesImporter\\src\\main\\resources\\Housing_2024_vlpa.xlsx";

    public List<ExpenseCategory> readCategoriesFromExcel() throws IOException {
        info("Read categories - START");
        FileInputStream excelFile = new FileInputStream(new File(this.getClass()
                .getClassLoader().getResource(ApplicationConfig.Excel.HOUSE_EXCEL_FILE).getFile()));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet dataTypeSheet = workbook.getSheetAt(ApplicationConfig.Excel.Tabs.CATEGORIES_TAB_INDEX);
        Iterator<Row> iterator = dataTypeSheet.iterator();

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
                        debug(String.format("[%d;%d][%s]", rowNumber, currentCell.getColumnIndex(), currentCell.getStringCellValue()));
                        categories.get(categories.size() - 1).getSubCategories().add(currentCell.getStringCellValue());
                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
                        debug(String.format("[%d;%d][%d]", rowNumber, currentCell.getColumnIndex(), currentCell.getNumericCellValue()));
                        warning("Incorrect cell format (NUMERIC). Skip the cell.");
                    } else {
                        warning("Incorrect cell format (Other). Skip the cell.");
                    }
                }
            }
            debug();
            rowNumber++;
        }
        info("Read categories - END\n");
        return categories;
    }

    public Map<String, List<ExpensePattern>> readMappingFromExcel() throws IOException {
        info("Load mapping - START");
        FileInputStream excelFile = new FileInputStream(new File(this.getClass().getClassLoader().getResource(ApplicationConfig.Excel.HOUSE_EXCEL_FILE).getFile()));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(ApplicationConfig.Excel.Tabs.MAPPING_TAB_INDEX);
        Iterator<Row> iterator = datatypeSheet.iterator();

        int rowNumber = 0;

        debug("    Load header");
        List<String> headers = new ArrayList<>();
        //header
        if (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();

            while (cellIterator.hasNext()) {
                Cell currentCell = cellIterator.next();
                if (currentCell.getCellType() == CellType.STRING && !currentCell.getStringCellValue().isEmpty()) {
                    debug(String.format("[%d;%d][%s]", rowNumber, currentCell.getColumnIndex(), currentCell.getStringCellValue()));
                    headers.add(currentCell.getStringCellValue());
                } else if (!currentCell.getStringCellValue().isEmpty()) {
                    warning("Incorrect cell format (Other). Skip the cell.");
                }
            }
        }

        debug();
        debug("    HEADERS: " + headers);

        Map<String, List<ExpensePattern>> patternsMap = new LinkedHashMap<>();
        //patterns
        debug("    Load patterns");
        while (iterator.hasNext()) {

            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();

            while (cellIterator.hasNext()) {
                Cell currentCell = cellIterator.next();
                List<ExpensePattern> expensePatterns = getListOfExpensePatternsForCategory(patternsMap, headers.get(currentCell.getColumnIndex() / 2));

                if (currentCell.getCellType() == CellType.STRING && !currentCell.getStringCellValue().isEmpty()) {
                    if (currentCell.getColumnIndex() % 2 == 0) {
                        debug(String.format("[%d;%d][%s](add expression)", rowNumber, currentCell.getColumnIndex(), currentCell.getStringCellValue()));

                        String cellValue = currentCell.getStringCellValue();
                        if (ExpenseParsingHelper.isAmountBasedPattern(cellValue)) {
                            AmountBasedExpensePattern currentAmountBasedPattern = new AmountBasedExpensePattern();
                            currentAmountBasedPattern.setExpression(ExpenseParsingHelper.parseExpressionFromAmountBasedPattern(cellValue));
                            currentAmountBasedPattern.setAmount(ExpenseParsingHelper.parseAmountValue(cellValue));
                            expensePatterns.add(currentAmountBasedPattern);
                        } else {
                            ExpensePattern currentRegularPattern = new ExpensePattern();
                            currentRegularPattern.setExpression(cellValue);
                            expensePatterns.add(currentRegularPattern);
                        }
                    } else {
                        debug(String.format("[%d;%d][%s](add category)", rowNumber, currentCell.getColumnIndex(), currentCell.getStringCellValue()));
                        ExpensePattern expensePattern = expensePatterns.get(expensePatterns.size() - 1);
                        expensePattern.setCategory(currentCell.getStringCellValue());
                    }
                }
            }
            debug();
            rowNumber++;
        }
        info("Load mapping - END\n");
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

    public void storeExpensesIntoExcel(List<Expense> expenses, ImportRequestData requestData, BankStatementImporter importer)
            throws IOException {
        info("Store expenses into Excel file - START");
        Calendar calendarBeginning = Calendar.getInstance();
        calendarBeginning.setTime(requestData.getBeginningOfTheMonth());

        int currentMonthTabIndex = TAB_INDEX_PREFIX + calendarBeginning.get(Calendar.MONTH) + 1;
        debug("currentMonthTabIndex: " + currentMonthTabIndex);

        debug("Start cell: [" + START_ROW_INDEX + ";" + START_COLUMN_INDEX + "]");

//        LocalDate localDate = LocalDate.of(2024, 1, 1);
//
//        debug("localDate.getDayOfMonth: " + localDate.getDayOfMonth());
//        debug("localDate.getMonth().name: " + localDate.getMonth().name());

//        FileInputStream excelFile = new FileInputStream(this.getClass().getClassLoader().getResource(templateFileName).getFile());
        File file = new File(templateFileName);
        info("Input file: " + file.getAbsolutePath());

        FileInputStream excelFile = new FileInputStream(file);

        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet currentMonthSheet = workbook.getSheetAt(currentMonthTabIndex);
        info("Current tab name: " + currentMonthSheet.getSheetName());

        int lastDayFilledNumber = 0;
        int sameDayRecordNumber = 0;

        for (Expense currentExpense : expenses) {

            debug("currentExpense: " + currentExpense);

            Calendar tempCalendar = Calendar.getInstance();
            tempCalendar.setTime(currentExpense.getDate());
            int currentDayNumber = tempCalendar.get(Calendar.DAY_OF_MONTH);

            debug("currentDayNumber: " + currentDayNumber);
            debug("lastDayFilledNumber: " + lastDayFilledNumber);

            if (currentDayNumber == lastDayFilledNumber) {

                sameDayRecordNumber++;

                int currentRowIndex = START_ROW_INDEX + (currentDayNumber - 1) * 5 + sameDayRecordNumber;
                debug("    add expense to the SAME day:");
                debug("        - set value to [" + currentRowIndex + ";" + START_COLUMN_INDEX + "]");

                setCellsData(currentMonthSheet, currentRowIndex, START_COLUMN_INDEX, importer.getTemplateColumnNumber(), currentExpense);

            } else {
                sameDayRecordNumber = 0;

                int currentRowIndex = START_ROW_INDEX + (currentDayNumber - 1) * 5 + sameDayRecordNumber;
                debug("    add expense to the NEW day:");
                debug("        - set value to [" + currentRowIndex + ";" + START_COLUMN_INDEX + "]");

                setCellsData(currentMonthSheet, currentRowIndex, START_COLUMN_INDEX, importer.getTemplateColumnNumber(), currentExpense);

                lastDayFilledNumber = currentDayNumber;
            }
        }

        File resultExcelFile = new File("c:\\parkhomchuk\\Repositories\\github\\Spring\\ExcelExpensesImporter\\src\\main\\resources\\RESULT_Housing_2024_vlpa.xlsx");
        info("Result templateFile: " + resultExcelFile.getAbsolutePath());

        FileOutputStream outFile = new FileOutputStream(resultExcelFile);
        workbook.write(outFile);

        workbook.close();
        excelFile.close();
        outFile.close();

        info("Store expenses into Excel file - END\n");
    }

    private void setCellsData(Sheet currentMonthSheet, int currentRowIndex, int startColumnIndex, int selectedCardColumnIndex,
            Expense currentExpense) {

        if (currentExpense.getCategory() == null) {
            warning("The following expense can't be matched by pattern. Expense: " + currentExpense);
            return;
        }

        debug("currentRowIndex: " + currentRowIndex + ", startColumnIndex: " + startColumnIndex + ", selectedCardColumnIndex: " + selectedCardColumnIndex
                + " -> [" + currentRowIndex + "; " + (startColumnIndex + 2 * selectedCardColumnIndex + 1) + "], " + "[" + currentRowIndex + "; " + (startColumnIndex + 2*selectedCardColumnIndex + 2) + "], ");

        CellStyle cellStyle = currentMonthSheet.getWorkbook().createCellStyle();
        cellStyle.setDataFormat((short) 14); // 14 corresponds to "m/d/yy"
        Cell dateCell = currentMonthSheet.getRow(currentRowIndex).getCell(startColumnIndex);
        dateCell.setCellStyle(cellStyle);
        dateCell.setCellValue(currentExpense.getDate());

        Cell categoryCell = currentMonthSheet.getRow(currentRowIndex).getCell(startColumnIndex + 2*selectedCardColumnIndex + 1);
        categoryCell.setCellValue(currentExpense.getCategory());

        Cell amountCell = currentMonthSheet.getRow(currentRowIndex).getCell(startColumnIndex + 2*selectedCardColumnIndex + 2);
        amountCell.setCellValue(currentExpense.getAmount());
    }

    public void storeUnknownExpenses(List<Expense> expenses, ImportRequestData requestData) throws IOException {
        info("Store unknown expenses - START");

        String excelFilePath = "src\\main\\resources\\" + requestData.getBank() + "_" + requestData.getCardType() + "_Unknown_Expenses.xlsx";

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet newSheet = workbook.createSheet("Expenses");

        int currentRowIndex = 0;

        String[] headers = {"Date", "Merchant", "Amount"};

        XSSFRow headerRow = newSheet.createRow(currentRowIndex);//TODO: add style for headers

        Cell dateHeaderCell = headerRow.createCell(0);
        dateHeaderCell.setCellValue(headers[0]);

        Cell merchantHeaderCell = headerRow.createCell(1);
        merchantHeaderCell.setCellValue(headers[1]);

        Cell amountHeaderCell = headerRow.createCell(2);
        amountHeaderCell.setCellValue(headers[2]);

        currentRowIndex++;

        CellStyle cellDateStyle = newSheet.getWorkbook().createCellStyle();
        cellDateStyle.setDataFormat((short) 14);

        debug("Unknown expenses:");
        for (Expense expense : expenses) {
            debug("    - " + expense);

            XSSFRow currentRow = newSheet.createRow(currentRowIndex++);

            Cell dateCell = currentRow.createCell(0);
            dateCell.setCellStyle(cellDateStyle);
            dateCell.setCellValue(expense.getDate());

            Cell merchantCell = currentRow.createCell(1);
            merchantCell.setCellValue(expense.getMerchant());

            Cell amountCell = currentRow.createCell(2);
            amountCell.setCellValue(expense.getAmount());
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }
        info("Saved to the file " + excelFilePath);

        workbook.close();

        info("Store unknown expenses - END\n");
    }
}
