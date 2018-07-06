package vlpa.expman;

import javafx.application.Application;
import javafx.stage.Stage;
import vlpa.expman.controller.ImportProcessor;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.ExpensesReport;
import vlpa.expman.view.UIBuilder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Expenses Manager");
        primaryStage.setScene(UIBuilder.getInstance().buildPrimaryScene(primaryStage));
        primaryStage.show();
    }

    public static void main(String[] args) {
//        ImportProcessor importProcessor = new ImportProcessor();
//        importProcessor.importExpenses("res/import/td_credit_test.csv");

//        testData();
        launch(args);
    }

    private static void testData() {

        MainProcessor processor = MainProcessor.getInstance();

        Date start = Date.from(LocalDate.now().minusMonths(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        ExpensesReport report = processor.getExpensesReportForAllCategories(start, end);
        System.out.println("report: " + report);

//        System.out.println("------------ CATEGORIES/EXPENSES ------------");
//        for (Category c : processor.getAllCategories()) {
//            System.out.println("----- CATEGORY: " + c + " -----");
//            for (Expense e : processor.getExpensesByCategoryId(c.getId())) {
//                System.out.println("   - expense: " + e);
//            }
//        }
//
//        System.out.println("------------ SORT CONFIG MAP ------------");
//        for (Map.Entry<String, Category> entry : processor.getExpensesMapping().entrySet()) {
//            System.out.println("[entry] key: " + entry.getKey() + " - " + entry.getValue());
//        }

//        processor.importExpenses("res/report.csv");

//        System.out.println("------------ EXPENSES REPORT FOR ALL CATEGORIES ------------");
//        Date start = new Date(System.currentTimeMillis() - 86400000);
//        Date end = new Date(System.currentTimeMillis() + 86400000);
//        System.out.println("start: " + start);
//        System.out.println("end: " + end);
//        ExpensesReport allCategoriesExpensesReport = processor.getExpensesReportForAllCategories(start, end);
//        System.out.println("allCategoriesExpensesReport: " + allCategoriesExpensesReport);

    }

}
