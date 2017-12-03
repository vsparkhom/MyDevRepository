package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import vlpa.expman.controller.CSVProcessor;
import vlpa.expman.controller.SortExpensesConfig;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.dao.ExpenseManagerDAO;
import vlpa.expman.model.dao.ExpensesDAO;
import vlpa.expman.model.dao.FakeExpenseManagerDAOImpl;
import vlpa.expman.view.UIBuilder;

import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Expenses Manager");
        primaryStage.setScene(UIBuilder.getInstance().getPrimaryScene(primaryStage));
        primaryStage.show();
    }

    public static void main(String[] args) {
        CSVProcessor.getInstance().importExpenses("res/report.csv");
        testData();
        launch(args);
//        testData();
    }

    private static void testData() {

        ExpenseManagerDAO dao = FakeExpenseManagerDAOImpl.getInstance();

        System.out.println("------------ CATEGORIES ------------");
        for (Category c : dao.getAllCategories()) {
            System.out.println(c);
        }

        System.out.println("------------ EXPENSES ------------");

//        CSVProcessor.getInstance().importExpenses("res/report.csv");

        for (Expense e : dao.getAllExpenses()) {
            System.out.println(e);
        }

//
//        System.out.println("\n---- Expenses for Category with ID = 1");
//        for (Expense e : dao.getExpensesByCategoryId(1)) {
//            System.out.println(e);
//        }

        System.out.println("------------ SORT CONFIG MAP ------------");
        SortExpensesConfig sortExpensesConfig = SortExpensesConfig.getInstance();
        for (Map.Entry<String, Category> entry : sortExpensesConfig.getConfig().entrySet()) {
            System.out.println("[entry] key: " + entry.getKey() + " - " + entry.getValue());
        }

    }

}
