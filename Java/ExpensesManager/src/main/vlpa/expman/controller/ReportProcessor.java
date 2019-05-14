package vlpa.expman.controller;

import com.gembox.spreadsheet.*;
import com.gembox.spreadsheet.charts.ChartType;
import com.gembox.spreadsheet.charts.ExcelChart;
import vlpa.expman.model.Category;
import vlpa.expman.model.ExpensesReport;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;

public class ReportProcessor {

    private final int NUMBER_OF_MONTHS = 5;
    private final String CHART_POSITION_FROM = "I2";
    private final String CHART_POSITION_TO = "R25";

    private MainProcessor processor = MainProcessor.getInstance();

    public void generateReport() throws IOException {
        Map<String, Double[]> diagramData = getDiagramData(NUMBER_OF_MONTHS);
        printReportToConsole(diagramData);

        int numberOfCategories = diagramData.keySet().size();

        SpreadsheetInfo.setLicense("FREE-LIMITED-KEY");

        ExcelFile workbook = new ExcelFile();
        ExcelWorksheet worksheet = getExcelWorksheet(workbook, numberOfCategories);

        generateHeader(worksheet);
        generateMainTable(worksheet, diagramData);

        workbook.save(getReportName());
    }

    private ExcelWorksheet getExcelWorksheet(ExcelFile workbook, int numberOfCategories) {
        ExcelWorksheet worksheet = workbook.addWorksheet("Chart");

        ExcelChart chart = worksheet.getCharts().add(ChartType.BAR, CHART_POSITION_FROM, CHART_POSITION_TO);
        chart.selectData(worksheet.getCells().getSubrangeAbsolute(0, 0,
                numberOfCategories, NUMBER_OF_MONTHS + 1), true);
        return worksheet;
    }

    private String getReportName() {
        String baseName = "reports/Report_";
        String ext = ".xlsx";
        return baseName + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()) + ext;
    }

    private void generateMainTable(ExcelWorksheet worksheet, Map<String, Double[]> diagramData) {
        String[] categoryNames = diagramData.keySet().toArray(new String[]{});
        for (int i = 0; i < categoryNames.length; i++) {
            Double[] categoryValues = diagramData.get(categoryNames[i]);
            int rowIndex = i + 1;
            setCategoryNameValue(worksheet, rowIndex, 0, categoryNames[i]);
            setLimitValue(worksheet, rowIndex, NUMBER_OF_MONTHS + 1, categoryValues[NUMBER_OF_MONTHS]);
            for (int j = 0; j < NUMBER_OF_MONTHS; j++) {
                worksheet.getCell(rowIndex, j + 1).setValue(categoryValues[j]); //set monthly category values
            }
        }
    }

    private void setCategoryNameValue(ExcelWorksheet worksheet, int rowIndex, int categoryNameColumnIndex, String categoryName) {
        worksheet.getCell(rowIndex, categoryNameColumnIndex).setValue(categoryName);
    }

    private void setLimitValue(ExcelWorksheet worksheet, int rowIndex, int limitColumnIndex, double limitValue) {
        worksheet.getCell(rowIndex, limitColumnIndex).setValue(limitValue);
    }

    private void generateHeader(ExcelWorksheet worksheet) {
        setColumnHeader(worksheet, 0, 0, "Category name");
        for (int j = 1; j < NUMBER_OF_MONTHS + 1; j++) {
            setColumnHeader(worksheet, 0, j, getPastMonthByNumber(NUMBER_OF_MONTHS - j).getMonth().toString());
        }
        setColumnHeader(worksheet, 0, NUMBER_OF_MONTHS + 1, "Limit");
        worksheet.getColumn(0).setWidth((int) LengthUnitConverter.convert(3, LengthUnit.CENTIMETER, LengthUnit.ZERO_CHARACTER_WIDTH_256_TH_PART));
//        worksheet.getColumn(1).getStyle().setNumberFormat("\"$\"#,##0");
    }

    private void setColumnHeader(ExcelWorksheet worksheet, int rowIndex, int columnIndex, String value) {
        worksheet.getCell(rowIndex, columnIndex).setValue(value);
        worksheet.getCell(rowIndex, columnIndex).getStyle().getFont().setWeight(ExcelFont.BOLD_WEIGHT);
    }

    private Map<String, Double[]> getDiagramData(int monthsCount) {
        List<Category> categories = processor.getAllCategories();
        Map<String, Double[]> excelData = new LinkedHashMap<>();
        for (Category category : categories) {
            Double[] expenses = new Double[monthsCount + 1];
            for (int i = 0; i < monthsCount; i++) {
                YearMonth month = getPastMonthByNumber( monthsCount - i - 1);
                ExpensesReport report = processor.getExpensesReportForCategory(category.getId(),
                        getBeginningOfMonth(month), getEndOfMonth(month));
                expenses[i] = report.getCurrentAmount();
            }
            expenses[monthsCount] = category.getLimit();
            excelData.put(category.getName(), expenses);
        }
        return excelData;
    }

    private YearMonth getPastMonthByNumber(int n) {
        LocalDate today = LocalDate.now();
        return YearMonth.of(today.getYear(), today.getMonth().minus(n));
    }

    private Date getBeginningOfMonth(YearMonth month) {
        LocalDate startLocalDate = month.atDay(1);
        return convertLocalDateToDate(startLocalDate);
    }

    private Date getEndOfMonth(YearMonth month) {
        LocalDate endLocalDate = month.atEndOfMonth();
        return convertLocalDateToDate(endLocalDate);
    }

    private Date convertLocalDateToDate(LocalDate startLocalDate) {
        return Date.from(startLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private void printReportToConsole(Map<String, Double[]> excelData) {
        System.out.println("[DEBUG]<generateReport> --------------- REPORT RESULTS ---------------");
        for (String key : excelData.keySet()) {
            System.out.printf("%20s | ", key);
            for (Double d : excelData.get(key)) {
                System.out.printf("%10.2f | ", d);
            }
            System.out.println();
        }
    }

}
