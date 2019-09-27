package vlpa.expman;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.controller.ReportProcessor;
import vlpa.expman.model.ExpensesReport;
import vlpa.expman.view.UIBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application {

    protected static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    /**
     * TODO list:
     * - Add pattern name
     * - Implement JUnit tests for Repository classes
     * - Add history/log of imports
     * - Add builders for Expense and ImportPattern classes
     */


    @Override
    public void start(Stage primaryStage) {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Expenses Manager");
        primaryStage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("img/icon.png")));
        primaryStage.setScene(UIBuilder.getInstance().buildPrimaryScene(primaryStage));
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        LOGGER.info("Application has started.");

//        ImportProcessor importProcessor = new ImportProcessor();
//        importProcessor.importExpenses("res/import/td_credit_test.csv");

//        testData();

        launch(args);

//        generateReport();

        LOGGER.info("Application is closed.");
    }

    private static void generateReport() throws IOException {
        ReportProcessor reportProcessor = new ReportProcessor();
        reportProcessor.generateReport();
    }

    private static void testData() {

        MainProcessor processor = MainProcessor.getInstance();

        Date start = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        ExpensesReport report = processor.getExpensesReportForAllCategories(start, end);
        LOGGER.info("report: {}", report);

//        LOGGER.info("------------ CATEGORIES/EXPENSES ------------");
//        for (Category c : processor.getAllCategories()) {
//            LOGGER.info("----- CATEGORY: {} -----", c);
//            for (Expense e : processor.getExpensesByCategoryId(c.getId())) {
//                LOGGER.info("   - expense: {}", e);
//            }
//        }
//
//        LOGGER.info("------------ SORT CONFIG MAP ------------");
//        for (Map.Entry<String, Category> entry : processor.getAllPatterns().entrySet()) {
//            LOGGER.info("[entry] key: {} - {}", entry.getKey(), entry.getValue());
//        }

//        processor.importExpenses("res/report.csv");

//        LOGGER.info("------------ EXPENSES REPORT FOR ALL CATEGORIES ------------");
//        Date start = new Date(System.currentTimeMillis() - 86400000);
//        Date end = new Date(System.currentTimeMillis() + 86400000);
//        LOGGER.info("start: {}, end: {}", start, end);
//        ExpensesReport allCategoriesExpensesReport = processor.getExpensesReportForAllCategories(start, end);
//        LOGGER.info("allCategoriesExpensesReport: {}", allCategoriesExpensesReport);

    }

}
