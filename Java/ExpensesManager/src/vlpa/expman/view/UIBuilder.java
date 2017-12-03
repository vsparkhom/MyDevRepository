package vlpa.expman.view;

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
import vlpa.expman.model.ExpensesReport;
import vlpa.expman.model.dao.ExpensesDAO;
import vlpa.expman.model.dao.FakeExpensesDAOImpl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UIBuilder {

    private ExpensesDAO dao = FakeExpensesDAOImpl.getInstance();

    private UIBuilder() {}

    private static class UIBuilderInstanceHolder {
        public static UIBuilder instance = new UIBuilder();
    }

    public static UIBuilder getInstance() {
        return UIBuilderInstanceHolder.instance;
    }

    public Scene getPrimaryScene(Stage primaryStage) {
        return new Scene(buildGUI(primaryStage), UIConst.SCENE_WIDTH, UIConst.SCENE_HEIGHT);
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

        Hyperlink summaryOption = new Hyperlink("Summary");
        vbox.setMargin(summaryOption, new Insets(0, 0, 0, 8));
        vbox.getChildren().add(summaryOption);

        for (Category c : dao.getAllCategories()) {
            // Add offset to left side to indent from title
            Hyperlink categoryOption = new Hyperlink(c.getName());
            vbox.setMargin(categoryOption, new Insets(0, 0, 0, 8));
            vbox.getChildren().add(categoryOption);
        }

        return vbox;
    }

    private VBox addCenterPane() {

        VBox vbox = new VBox();
        vbox.setSpacing(5);// Gap between nodes

        HBox topSummaryPane = addTopSummaryPane();
        vbox.getChildren().add(topSummaryPane);

        HBox categoriesHeaderPane = addCategoriesHeaderPane();
        vbox.getChildren().add(categoriesHeaderPane);

        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

        //TODO: implement automatic time period adjustment
        Calendar calendar = Calendar.getInstance();
        System.out.println("[DEBUG] Today [" + format.format(calendar.getTime()) + "]");
        Date end = calendar.getTime();
        calendar.add(Calendar.MONTH, -1);
        Date start = calendar.getTime();
        System.out.println("[DEBUG] Get Period [" + format.format(start) + " - " + format.format(end) + "]");

        for (Category c : dao.getAllCategories()) {
            ExpensesReport reportForCurrentCategory = dao.getExpensesReportForCategory(c.getId(), start, end);
            System.out.println("<addCenterPane>[report]: " + reportForCurrentCategory);
            vbox.getChildren().add(addCategoryPane(reportForCurrentCategory));
        }

        return vbox;
    }

    private HBox addTopSummaryPane() {

        Date start = new Date(System.currentTimeMillis() - 86400000);
        Date end = new Date(System.currentTimeMillis() + 86400000);
        ExpensesReport allCategoriesExpensesReport = dao.getExpensesReportForAllCategories(start, end);

//        System.out.println("[DEBUG]<addTopSummaryPane> allCategoriesExpensesReport: " + allCategoriesExpensesReport);

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);   // Gap between nodes
        hbox.setStyle("-fx-background-color: lightgreen");
        hbox.setAlignment(Pos.CENTER);

        Label alreadySpentLabel = new Label("SPENDING");
        Label alreadySpentAmount = new Label(allCategoriesExpensesReport.getCurrentAmount() + "");
        VBox alreadySpentPane = new VBox();
        alreadySpentPane.setAlignment(Pos.CENTER);
        alreadySpentPane.getChildren().addAll(alreadySpentLabel, alreadySpentAmount);

        HBox progressBarPane = addProgressBarPane(allCategoriesExpensesReport.getUsagePercent());

        Label left = new Label("[Left: " + allCategoriesExpensesReport.getLeftover() + "]");
        Label budget = new Label("[Budget: " + allCategoriesExpensesReport.getLimit() + "]");
        VBox leftAndBudgetPane = new VBox();
        leftAndBudgetPane.getChildren().addAll(left, budget);

        hbox.getChildren().addAll(alreadySpentPane, progressBarPane, leftAndBudgetPane);

        return hbox;
    }

    private HBox addCategoriesHeaderPane() {

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 7, 10, 7));
        hbox.setSpacing(10); // Gap between nodes
        hbox.setStyle("-fx-background-color: cornflowerblue");

        Label categoryName = new Label("Category Name");
        categoryName.setStyle("-fx-font-weight: bold");
        categoryName.setPrefWidth(100);

        Label currentAmount = new Label("Spent");
        currentAmount.setStyle("-fx-font-weight: bold");
        currentAmount.setPrefWidth(50);

        HBox emptyProgressBarPane = new HBox();
        emptyProgressBarPane.setPrefWidth(200);
//        addBorder(emptyProgressBarPane, "red");

        Label left = new Label("Left");
        left.setStyle("-fx-font-weight: bold");
        left.setPrefWidth(50);

        Label limit = new Label("Limit");
        limit.setStyle("-fx-font-weight: bold");
        limit.setPrefWidth(50);

        hbox.getChildren().addAll(categoryName, currentAmount, emptyProgressBarPane, left, limit);

        return hbox;
    }

    private HBox addCategoryPane(ExpensesReport report) {

//        System.out.println("[DEBUG]<addCategoryPane> report: " + report);

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 7, 10, 7));
        hbox.setSpacing(10);   // Gap between nodes
        hbox.setStyle("-fx-background-color: gainsboro");

        Label categoryName = new Label(report.getCategory().getName());
        categoryName.setPrefWidth(100);

        Label currentAmount = new Label(report.getCurrentAmount() + "");
        currentAmount.setPrefWidth(50);

        HBox categoryProgressBarPane = addProgressBarPane(report.getUsagePercent());
//        addBorder(categoryProgressBarPane, "red");

        Label left = new Label(report.getLeftover() + "");
        left.setPrefWidth(50);

        Label limit = new Label(report.getLimit() + "");
        limit.setPrefWidth(50);

        hbox.getChildren().addAll(categoryName, currentAmount, categoryProgressBarPane, left, limit);

        return hbox;
    }

    private HBox addProgressBarPane(double usagePercent) {
        HBox progressBarPane = new HBox();
        progressBarPane.setAlignment(Pos.CENTER);
        progressBarPane.setPrefWidth(200);
        ProgressBar pb = new ProgressBar(usagePercent);
        String colorByPercent = getColorByPercent(usagePercent);
        pb.setStyle("-fx-accent: " + colorByPercent);
        ProgressIndicator pi = new ProgressIndicator(usagePercent);
        pi.setStyle("-fx-progress-color: " + colorByPercent);
        progressBarPane.getChildren().addAll(pb, pi);
        return progressBarPane;
    }

    private String getColorByPercent(double perc) {
        String color = "green";
        if (perc >= 0.7 && perc < 0.9) {
            color = "orange";
        } else if (perc >= 0.9) {
            color = "red";
        }
        return color;
    }

}
