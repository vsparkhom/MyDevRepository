package com.vlpa.spring.expenseimporter.repository;

import com.vlpa.spring.expenseimporter.ExcelConfig;
import com.vlpa.spring.expenseimporter.model.*;
import com.vlpa.spring.expenseimporter.repository.data.CategoryRow;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

import static com.vlpa.spring.expenseimporter.ExcelConfig.Categories.*;
import static com.vlpa.spring.expenseimporter.ExpenseUtils.fromDateToString;
import static com.vlpa.spring.expenseimporter.LoggerUtils.*;

public class ExcelRepository {

    public List<Category> readCategoriesFromExcel() throws IOException {
        info("Read categories - START");
        FileInputStream excelFile = new FileInputStream(new File(this.getClass()
                .getClassLoader().getResource(ExcelConfig.FILE_NAME).getFile()));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet dataTypeSheet = workbook.getSheetAt(ExcelConfig.TabIndex.CATEGORIES);
        Iterator<Row> iterator = dataTypeSheet.iterator();

        List<CategoryRow> categoryRows = new LinkedList<>();

        int rowNumber = 0;
        while (iterator.hasNext()) {

            Row currentRow = iterator.next();

            debug("Current row number: " + rowNumber);
            if (rowNumber >= START_ROW_INDEX) {//TODO: do not rely on index here and make it more generic

                Iterator<Cell> cellIterator = currentRow.iterator();
                CategoryRow categoryRow = new CategoryRow();

                while (cellIterator.hasNext()) {
                    Cell currentCell = cellIterator.next();
                    if (currentCell.getColumnIndex() == 0 /*&& !currentCell.getStringCellValue().isEmpty()*/) {
                        debug(String.format("[%d;%d][%s]", rowNumber, currentCell.getColumnIndex(), currentCell.getNumericCellValue()));
                        categoryRow.setId(Double.valueOf(currentCell.getNumericCellValue()).intValue());
                    }
                    if (currentCell.getColumnIndex() == 1 /*&& !currentCell.getStringCellValue().isEmpty()*/) {
                        debug(String.format("[%d;%d][%s]", rowNumber, currentCell.getColumnIndex(), currentCell.getStringCellValue()));
                        categoryRow.setCategory(currentCell.getStringCellValue());
                    }
                    if (currentCell.getColumnIndex() == 2 /*&& !currentCell.getStringCellValue().isEmpty()*/) {
                        debug(String.format("[%d;%d][%s]", rowNumber, currentCell.getColumnIndex(), currentCell.getStringCellValue()));
                        categoryRow.setParentCategory(currentCell.getStringCellValue());
                    }
                    if (currentCell.getColumnIndex() == 3 /*&& !currentCell.getStringCellValue().isEmpty()*/) {
                        debug(String.format("[%d;%d][%s]", rowNumber, currentCell.getColumnIndex(), currentCell.getStringCellValue()));
                        categoryRow.setTopCategory(currentCell.getStringCellValue());
                    }
                }

                info(categoryRow.toString());
                categoryRows.add(categoryRow);

            }

            debug();
            rowNumber++;
        }

        //init parent categories

        debug("------------- PARENT CATEGORIES RESOLUTION -------------");

        Set<Category> parentCategories = getParentCategories(categoryRows);
        debug("parentCategories:");
        debug(parentCategories.toString());

        //init regular categories

        debug("------------- REGULAR CATEGORIES RESOLUTION -------------");

        List<Category> categories = new LinkedList<>(parentCategories);

        for (CategoryRow categoryRow : categoryRows) {
            Category parentCategory = parentCategories.stream().filter(c -> c.getName().equals(categoryRow.getParentCategory())).findFirst().get();
            Category category = new Category(categoryRow.getId(), categoryRow.getCategory(), parentCategory, TopCategory.resolve(categoryRow.getTopCategory()));
            categories.add(category);
        }

        info("Read categories - END\n");
        return categories;
    }

    private Set<Category> getParentCategories(List<CategoryRow> categoryRows) {
        Set<String> parentCategoryNames = new HashSet<>();
        int parentCategoryIndex = 1;
        Set<Category> parentCategories = new HashSet<>();
        for (CategoryRow categoryRow : categoryRows) {
            String parentCategoryName = categoryRow.getParentCategory();
            if (parentCategoryNames.contains(parentCategoryName)) {
                continue;
            } else {
                parentCategoryNames.add(parentCategoryName);
            }

            Category parentCategory = new Category(parentCategoryIndex * 100, parentCategoryName, null,null);
            if (!parentCategories.contains(parentCategory)) {
                parentCategories.add(parentCategory);
                parentCategoryIndex++;
            }
        }

        return parentCategories;
    }

    public List<Pattern> readMapping(List<Category> categories) throws IOException {
        info("Load mapping - START");
        FileInputStream excelFile = new FileInputStream(new File(this.getClass().getClassLoader().getResource(ExcelConfig.FILE_NAME).getFile()));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet dataTypeSheet = workbook.getSheetAt(ExcelConfig.TabIndex.MAPPING);
        Iterator<Row> iterator = dataTypeSheet.iterator();

        boolean isHeaderPresent = true;//TODO: move out to properties file
        if (isHeaderPresent && iterator.hasNext()) {
            iterator.next();
        }

        Map<String, String> patternRows = new HashMap<>();

        debug("    Load patterns");
        while (iterator.hasNext()) {

            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();

            String patternText = "";
            String patternCategory = "";

            while (cellIterator.hasNext()) {
                Cell currentCell = cellIterator.next();
                if (currentCell.getColumnIndex() == 0 /*&& !currentCell.getStringCellValue().isEmpty()*/) {
                    patternText = currentCell.getStringCellValue();
                } else if (currentCell.getColumnIndex() == 1 /*&& !currentCell.getStringCellValue().isEmpty()*/) {
                    patternCategory = currentCell.getStringCellValue();
                }
            }

            debug("    - add: " + patternText + " - " + patternCategory);
            patternRows.put(patternText, patternCategory);

            debug();
        }

        List<Pattern> patterns = new LinkedList<>();
        for (Map.Entry<String, String> patternEntry : patternRows.entrySet()) {
            Pattern p = new Pattern();
            p.setExpression(patternEntry.getKey());
            p.setCategory(findCategoryByName(categories, patternEntry.getValue()));
            patterns.add(p);
        }

        info("Load mapping - END\n");
        return patterns;
    }

    private Category findCategoryByName(List<Category> categories, String categoryName) {
        return categories.stream().filter(category -> category.getName().equals(categoryName)).findFirst().get();
    }

    public void exportExpenses(List<Expense> expenses, ImportRequest requestData) throws IOException {
        info("Store matched expenses into Excel file - START");

        String excelFilePath = "src\\main\\resources\\Exported_Expenses_" + fromDateToString(requestData.getBeginningOfTheMonth()) + ".xlsx";

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet newSheet = workbook.createSheet("Expenses");

        int currentRowIndex = 0;

        String[] headers = {"Date", "Merchant", "Amount", "Top Category", "Category", "Parent Category"};

        XSSFRow headerRow = newSheet.createRow(currentRowIndex);//TODO: add style for headers

        Cell dateHeaderCell = headerRow.createCell(0);
        dateHeaderCell.setCellValue(headers[0]);

        Cell merchantHeaderCell = headerRow.createCell(1);
        merchantHeaderCell.setCellValue(headers[1]);

        Cell amountHeaderCell = headerRow.createCell(2);
        amountHeaderCell.setCellValue(headers[2]);

        Cell topCategoryHeaderCell = headerRow.createCell(3);
        topCategoryHeaderCell.setCellValue(headers[3]);

        Cell categoryHeaderCell = headerRow.createCell(4);
        categoryHeaderCell.setCellValue(headers[4]);

        Cell parentCategoryHeaderCell = headerRow.createCell(5);
        parentCategoryHeaderCell.setCellValue(headers[5]);

        currentRowIndex++;

        CellStyle cellDateStyle = newSheet.getWorkbook().createCellStyle();
        cellDateStyle.setDataFormat((short) 14);

        debug("Exported expenses (" + expenses.size() + "):");
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

            if (expense.getCategory() != null) {

                if (expense.getCategory().getTopCategory() != null) {
                    Cell topCategoryCell = currentRow.createCell(3);
                    topCategoryCell.setCellValue(expense.getCategory().getTopCategory().name());
                }

                Cell categoryCell = currentRow.createCell(4);
                categoryCell.setCellValue(expense.getCategory().getName());

                if (expense.getCategory().getParentCategory() != null) {
                    Cell parentCategoryCell = currentRow.createCell(5);
                    parentCategoryCell.setCellValue(expense.getCategory().getParentCategory().getName());
                }
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }
        info("Saved to the file " + excelFilePath);

        workbook.close();

        info("Store expenses into Excel file - END\n");
    }

//    private void setCellsData(Sheet currentMonthSheet, int currentRowIndex, int startColumnIndex, int selectedCardColumnIndex,
//            Expense currentExpense) {
//
//        if (currentExpense.getCategory() == null) {
//            warning("The following expense can't be matched by pattern. Expense: " + currentExpense);
//            return;
//        }
//
//        debug("currentRowIndex: " + currentRowIndex + ", startColumnIndex: " + startColumnIndex + ", selectedCardColumnIndex: " + selectedCardColumnIndex
//                + " -> [" + currentRowIndex + "; " + (startColumnIndex + 2 * selectedCardColumnIndex + 1) + "], " + "[" + currentRowIndex + "; " + (startColumnIndex + 2*selectedCardColumnIndex + 2) + "], ");
//
//        CellStyle cellStyle = currentMonthSheet.getWorkbook().createCellStyle();
//        cellStyle.setDataFormat((short) 14); // 14 corresponds to "m/d/yy"
//        Cell dateCell = currentMonthSheet.getRow(currentRowIndex).getCell(startColumnIndex);
//        dateCell.setCellStyle(cellStyle);
//        dateCell.setCellValue(currentExpense.getDate());
//
//        Cell categoryCell = currentMonthSheet.getRow(currentRowIndex).getCell(startColumnIndex + 2*selectedCardColumnIndex + 1);
//        categoryCell.setCellValue(currentExpense.getCategory().getName());
//
//        Cell amountCell = currentMonthSheet.getRow(currentRowIndex).getCell(startColumnIndex + 2*selectedCardColumnIndex + 2);
//        amountCell.setCellValue(currentExpense.getAmount());
//    }

}
