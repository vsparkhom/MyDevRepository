package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.ExpensesDAO;
import vlpa.expman.model.FakeExpensesDAOImpl;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(buildGUI(primaryStage), 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
//        test();
    }

    private static void test() {

        ExpensesDAO dao = FakeExpensesDAOImpl.getInstance();

        System.out.println("------------ CATEGORIES ------------");
        for (Category c : dao.getAllCategories()) {
            System.out.println(c);
        }

        System.out.println("------------ EXPENSES ------------");
        for (Expense e : dao.getAllExpenses()) {
            System.out.println(e);
        }

        System.out.println("\n---- Expenses for Category with ID = 1");
        for (Expense e : dao.getExpensesByCategoryId(1)) {
            System.out.println(e);
        }
    }

    private Pane buildGUI(Stage stage) {
        BorderPane root = new BorderPane();
        addBorder(root, "black");

        HBox topMenu = addTopMenu(stage);
        addBorder(topMenu, "red");
        topMenu.setPrefWidth(500);
        root.setTop(topMenu);

        VBox leftMenu = addLeftMenu();
        addBorder(leftMenu, "blue");
        leftMenu.setPrefWidth(150);
        root.setLeft(leftMenu);

        VBox center = addCenterPane();
        addBorder(center, "green");
        center.setPrefWidth(500);
        center.setPrefHeight(500);
        root.setCenter(center);

        return root;
    }

    private void addBorder(Pane pane, String color) {
        pane.setStyle("-fx-border-color: " + color);
//        pane.setStyle("-fx-border-style: solid");
//        pane.setStyle("-fx-border-width: 2");
    }

    private HBox addTopMenu(final Stage stage) {

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);   // Gap between nodes
        hbox.setStyle("-fx-background-color: #336699;");

        final FileChooser fileChooser = new FileChooser();
        Button importButton = new Button("Import...");
        importButton.setPrefSize(100, 20);

        importButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        File file = fileChooser.showOpenDialog(stage);
                        if (file != null) {
                            System.out.println("File has been chosen:" + file.getName());
                        }
                    }
                });

        Button testButton = new Button("Test");
        testButton.setPrefSize(100, 20);

        hbox.getChildren().addAll(importButton, testButton);

        return hbox;
    }

    private VBox addLeftMenu() {

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10)); // Set all sides to 10
        vbox.setSpacing(8);              // Gap between nodes

        Text title = new Text("Menu");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);

        Hyperlink options[] = new Hyperlink[]{
                new Hyperlink("Summary"),
                new Hyperlink("Category 1"),
                new Hyperlink("Category 2"),
                new Hyperlink("Category 3"),
                new Hyperlink("Category N")
        };

        for (int i = 0; i < 4; i++) {
            // Add offset to left side to indent from title
            VBox.setMargin(options[i], new Insets(0, 0, 0, 8));
            vbox.getChildren().add(options[i]);
        }

        return vbox;
    }

    private VBox addCenterPane() {

        VBox vbox = new VBox();
        vbox.setSpacing(8);// Gap between nodes

        HBox topSummaryPane = addTopSummaryPane();
        vbox.getChildren().add(topSummaryPane);

        HBox categoriesHeaderPane = addCategoriesHeaderPane();
        vbox.getChildren().add(categoriesHeaderPane);

        for (int i = 0; i < 3; i++) {
            vbox.getChildren().add(addCategoryPane());
        }

        return vbox;
    }

    private HBox addTopSummaryPane() {

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);   // Gap between nodes
        hbox.setStyle("-fx-background-color: lightgreen");
        hbox.setAlignment(Pos.CENTER);

        Label alreadySpentLabel = new Label("SPENDING");
        Label alreadySpentAmount = new Label("450");
        VBox alreadySpentPane = new VBox();
        alreadySpentPane.setAlignment(Pos.CENTER);
        alreadySpentPane.getChildren().addAll(alreadySpentLabel, alreadySpentAmount);

        HBox progressBarPane = addProgressBarPane();

        Label left = new Label("[Left: 550]");
        Label budget = new Label("[Budget: 1000]");
        VBox leftAndBudgetPane = new VBox();
        leftAndBudgetPane.getChildren().addAll(left, budget);

        hbox.getChildren().addAll(alreadySpentPane, progressBarPane, leftAndBudgetPane);

        return hbox;
    }

    private HBox addCategoriesHeaderPane() {

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10); // Gap between nodes
        hbox.setStyle("-fx-background-color: cornflowerblue");

        Label categoryName = new Label("Category Name");
        categoryName.setPrefWidth(100);

        HBox emptyProgressBarPane = new HBox();
        emptyProgressBarPane.setPrefWidth(200);
//        addBorder(emptyProgressBarPane, "red");

        Label left = new Label("Left");
        left.setPrefWidth(50);

        Label limit = new Label("Limit");
        limit.setPrefWidth(50);

        hbox.getChildren().addAll(categoryName, emptyProgressBarPane, left, limit);

        return hbox;
    }

    private HBox addCategoryPane() {

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);   // Gap between nodes
        hbox.setStyle("-fx-background-color: gainsboro");

        Label categoryName = new Label("My Category 1");
        categoryName.setPrefWidth(100);

        HBox categoryProgressBarPane = addProgressBarPane();
//        addBorder(categoryProgressBarPane, "red");

        Label left = new Label("50");
        left.setPrefWidth(50);

        Label limit = new Label("100");
        limit.setPrefWidth(50);

        hbox.getChildren().addAll(categoryName, categoryProgressBarPane, left, limit);

        return hbox;
    }

    private HBox addProgressBarPane() {
        HBox progressBarPane = new HBox();
        progressBarPane.setAlignment(Pos.CENTER);
        progressBarPane.setPrefWidth(200);
        double testPercent = Math.random();
        ProgressBar pb = new ProgressBar(testPercent);
        ProgressIndicator pi = new ProgressIndicator(testPercent);
        progressBarPane.getChildren().addAll(pb, pi);
        return progressBarPane;
    }
}
