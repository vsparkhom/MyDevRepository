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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vlpa.expman.controller.MainDataProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.ExpensesReport;
import vlpa.expman.dao.ExpenseManagerDAO;
import vlpa.expman.dao.FakeExpenseManagerDAOImpl;
import vlpa.expman.view.table.TableCell;
import vlpa.expman.view.table.TableHeader;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class UIBuilder {

    public static final String CSS_STYLE_FILE_NAME = "view.css";

    private MainDataProcessor processor = new MainDataProcessor();

    private BorderPane rootPane;
    private Stage primaryStage;

    private UIBuilder() {
    }

    private static class UIBuilderInstanceHolder {
        public static UIBuilder instance = new UIBuilder();
    }

    public static UIBuilder getInstance() {
        return UIBuilderInstanceHolder.instance;
    }

    public Scene buildPrimaryScene(Stage primaryStage) {

        this.primaryStage = primaryStage;

        Scene primaryScene = new Scene(buildGUI(primaryStage), UIConst.SCENE_WIDTH, UIConst.SCENE_HEIGHT);
        primaryScene.getStylesheets().add(CSS_STYLE_FILE_NAME);
        return primaryScene;
    }

    private Pane buildGUI(Stage stage) {
        rootPane = new BorderPane();
//        addBorder(rootPane, "black");

        HBox topMenu = buildTopMenu(stage);
        addBorder(topMenu, "grey");
        topMenu.setPrefWidth(500);
        rootPane.setTop(topMenu);

        Pane leftMenu = buildLeftMenu();
        addBorder(leftMenu, "grey");
        leftMenu.setPrefWidth(150);
        rootPane.setLeft(leftMenu);

        Pane center = buildSummaryPane();
//        addBorder(center, "green");
        center.setPrefWidth(500);
        center.setPrefHeight(500);
        rootPane.setCenter(center);

        return rootPane;
    }

    private void addBorder(Pane pane, String color) {
        pane.setStyle("-fx-border-color: " + color);
    }

    private HBox buildTopMenu(final Stage stage) {

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10); // Gap between nodes
//        hbox.setStyle("-fx-background-color: #336699;");

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

                            //TODO: create dialog

                            Stage dialog = new Stage();

                            // populate dialog with controls.

                            dialog.initOwner(primaryStage);
                            dialog.initModality(Modality.APPLICATION_MODAL);
                            dialog.showAndWait();

                        }
                    }
                });

        Button testButton = new Button("Test");
        testButton.setPrefSize(100, 20);

        hbox.getChildren().addAll(importButton, testButton);

        return hbox;
    }

    private Pane buildLeftMenu() {

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10)); // Set all sides to 10
        vbox.setSpacing(8);              // Gap between nodes

        Text title = new Text("Menu");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);

        final Hyperlink summaryOption = new Hyperlink("Summary");
        summaryOption.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                rootPane.setCenter(buildSummaryPane());
                summaryOption.setVisited(false);
            }
        });
        vbox.setMargin(summaryOption, new Insets(0, 0, 0, 8));
        vbox.getChildren().add(summaryOption);

        for (final Category c : processor.getAllCategories()) {
            // Add offset to left side to indent from title
            final Hyperlink categoryOption = new Hyperlink(c.getName());
            categoryOption.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                public void handle(MouseEvent me) {
                    rootPane.setCenter(buildCategoryDetailsPane(c.getId()));
                    categoryOption.setVisited(false);
                }
            });
            vbox.setMargin(categoryOption, new Insets(0, 0, 0, 8));
            vbox.getChildren().add(categoryOption);
        }

        return vbox;
    }

    private Pane buildCategoryDetailsPane(long categoryId) {

        GridPane grid = new GridPane();
//        addBorder(grid, "violet");

        grid.getStyleClass().add("category-details-grid");

        int currentRow = 0;

        TableHeader dateHeader = new TableHeader("Date",
                "category-details-grid-cell",
                "first-row",
                "first-column"
        );
        TableHeader merchantHeader = new TableHeader("Merchant",
                "category-details-grid-cell",
                "first-row"
        );
        TableHeader amountHeader = new TableHeader("Amount",
                "category-details-grid-cell",
                "first-row"
        );

        ColumnConstraints dateHeaderColumnConstraint = new ColumnConstraints();
        dateHeaderColumnConstraint.setPercentWidth(25);

        ColumnConstraints merchantHeaderColumnConstraint = new ColumnConstraints();
        merchantHeaderColumnConstraint.setPercentWidth(50);

        ColumnConstraints amountHeaderColumnConstraint = new ColumnConstraints();
        amountHeaderColumnConstraint.setPercentWidth(25);

        grid.getColumnConstraints().addAll(dateHeaderColumnConstraint, merchantHeaderColumnConstraint, amountHeaderColumnConstraint);
        grid.addRow(currentRow, dateHeader, merchantHeader, amountHeader);

        currentRow++;

        Collection<Expense> expenses = processor.getExpensesByCategoryId(categoryId);
        for (Expense e : expenses) {
            TableCell currentExpenseDateCell = new TableCell(e.getDate(),
                    "category-details-grid-cell",
                    "first-column"
            );
            TableCell currentExpenseMerchantCell = new TableCell(e.getName(), "category-details-grid-cell");
            TableCell currentExpenseAmountCell = new TableCell(e.getAmount(), "category-details-grid-cell");

            grid.addRow(currentRow, currentExpenseDateCell, currentExpenseMerchantCell, currentExpenseAmountCell);

            currentRow++;
        }

        return grid;
    }

    private Pane buildSummaryPane() {
        VBox vbox = new VBox();
        vbox.setSpacing(5);// Gap between nodes

        HBox topSummaryPane = buildTopSummaryPane();
        vbox.getChildren().add(topSummaryPane);

        HBox categoriesHeaderPane = buildCategoriesHeaderPane();
        vbox.getChildren().add(categoriesHeaderPane);

        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");

        //TODO: implement automatic time period adjustment
        Calendar calendar = Calendar.getInstance();
        System.out.println("[DEBUG] Today [" + format.format(calendar.getTime()) + "]");
        Date end = calendar.getTime();
        calendar.add(Calendar.MONTH, -1);
        Date start = calendar.getTime();
        System.out.println("[DEBUG] Get Period [" + format.format(start) + " - " + format.format(end) + "]");

        for (Category c : processor.getAllCategories()) {
            ExpensesReport reportForCurrentCategory = processor.getExpensesReportForCategory(c.getId(), start, end);
            System.out.println("<addCenterPane>[report]: " + reportForCurrentCategory);
            vbox.getChildren().add(buildCategoryPane(reportForCurrentCategory));
        }

        return vbox;
    }

    private HBox buildTopSummaryPane() {

        Calendar calendar = Calendar.getInstance();
        Date end = calendar.getTime();
        calendar.add(Calendar.MONTH, -1);
        Date start = calendar.getTime();

        ExpensesReport allCategoriesExpensesReport = processor.getExpensesReportForAllCategories(start, end);

//        System.out.println("[DEBUG]<buildTopSummaryPane> allCategoriesExpensesReport: " + allCategoriesExpensesReport);

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10); // Gap between nodes
        hbox.setStyle("-fx-background-color: lightgreen");
        hbox.setAlignment(Pos.CENTER);

        Label alreadySpentLabel = new Label("SPENDING");
        Label alreadySpentAmount = new Label(allCategoriesExpensesReport.getCurrentAmount() + "");
        VBox alreadySpentPane = new VBox();
        alreadySpentPane.setAlignment(Pos.CENTER);
        alreadySpentPane.getChildren().addAll(alreadySpentLabel, alreadySpentAmount);

        HBox progressBarPane = buildProgressBarPane(allCategoriesExpensesReport.getUsagePercent());

        Label left = new Label("[Left: " + allCategoriesExpensesReport.getLeftover() + "]");
        Label budget = new Label("[Budget: " + allCategoriesExpensesReport.getLimit() + "]");
        VBox leftAndBudgetPane = new VBox();
        leftAndBudgetPane.getChildren().addAll(left, budget);

        hbox.getChildren().addAll(alreadySpentPane, progressBarPane, leftAndBudgetPane);

        return hbox;
    }

    private HBox buildCategoriesHeaderPane() {

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

    private HBox buildCategoryPane(ExpensesReport report) {

//        System.out.println("[DEBUG]<buildCategoryPane> report: " + report);

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10, 7, 10, 7));
        hbox.setSpacing(10); // Gap between nodes
        hbox.setStyle("-fx-background-color: gainsboro");

        Label categoryName = new Label(report.getCategory().getName());
        categoryName.setPrefWidth(100);

        Label currentAmount = new Label(report.getCurrentAmount() + "");
        currentAmount.setPrefWidth(50);

        HBox categoryProgressBarPane = buildProgressBarPane(report.getUsagePercent());
//        addBorder(categoryProgressBarPane, "red");

        Label left = new Label(report.getLeftover() + "");
        left.setPrefWidth(50);

        Label limit = new Label(report.getLimit() + "");
        limit.setPrefWidth(50);

        hbox.getChildren().addAll(categoryName, currentAmount, categoryProgressBarPane, left, limit);

        return hbox;
    }

    private HBox buildProgressBarPane(double usagePercent) {
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
