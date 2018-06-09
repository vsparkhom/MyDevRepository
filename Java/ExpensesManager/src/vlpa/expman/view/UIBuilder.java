package vlpa.expman.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.ExpensesReport;
import vlpa.expman.view.datepicker.CustomDatePicker;
import vlpa.expman.view.datepicker.MonthlyDatePickerMenu;

import static vlpa.expman.view.UIDimensionsConst.*;

import java.io.File;
import java.util.Date;

public class UIBuilder {

    public static final String CSS_STYLE_FILE_NAME = "view.css";

    private MainProcessor processor = MainProcessor.getInstance();
    private BorderPane rootPane;
    private Stage primaryStage;
    private CustomDatePicker dpm;

    private long currentCategoryId = 0; //Summary by default

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

        Scene primaryScene = new Scene(buildGUI(primaryStage), UIDimensionsConst.SCENE_WIDTH, UIDimensionsConst.SCENE_HEIGHT);
        primaryScene.getStylesheets().add(CSS_STYLE_FILE_NAME);
        return primaryScene;
    }

    private Pane buildGUI(Stage stage) {

        stage.setMinWidth(SCENE_WIDTH + 20);
        stage.setMinHeight(SCENE_HEIGHT + 40);

        rootPane = new BorderPane();

        Pane topMenu = buildTopMenu(stage);
        addBorder(topMenu, "grey");
        topMenu.setPrefWidth(TOP_MENU_WIDTH);
        rootPane.setTop(topMenu);

        Pane leftMenu = buildLeftMenu();
        addBorder(leftMenu, "grey");
        leftMenu.setPrefWidth(LEFT_MENU_WIDTH);
        rootPane.setLeft(leftMenu);

        Pane center = buildSummaryPane();
        center.setPrefWidth(CENTER_MENU_WIDTH);
        center.setPrefHeight(CENTER_MENU_HEIGHT);
        rootPane.setCenter(center);

        return rootPane;
    }

    public void updateView() {
        rootPane.setCenter(currentCategoryId == 0 ? buildSummaryPane() : buildCategoryDetailsPane(currentCategoryId));
    }

    private void addBorder(Pane pane, String color) {
        pane.setStyle("-fx-border-color: " + color);
    }

    private Pane buildTopMenu(final Stage stage) {

        HBox topMenuBox = new HBox();
        topMenuBox.getChildren().addAll(buildMenuButtons(stage), buildDatePickerComponent());

        return topMenuBox;
    }

    private Pane buildDatePickerComponent() {
        dpm = new MonthlyDatePickerMenu(this);
        return dpm.getMenuPane();
    }

    private HBox buildMenuButtons(final Stage stage) {
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(15, 12, 15, 12));
//        hbox.setStyle("-fx-background-color: #336699;");

        final FileChooser fileChooser = new FileChooser();

        Button importButton = createMenuButton("Import expense", "img/import.png", new EventHandler<ActionEvent>() {
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

        Button addPatternButton = createMenuButton("Add pattern", "img/list.png", null);
        Button addCategoryButton = createMenuButton("Manage categories", "img/add.png",
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        ModalWindowsHelper.initCategoriesManagementWindow(primaryStage, processor);
                    }
                });

        hbox.getChildren().addAll(importButton, addPatternButton, addCategoryButton);
        return hbox;
    }

    private Button createMenuButton(String name, String imgPath, EventHandler<ActionEvent> handler) {
        Image buttonImg = new Image(getClass().getClassLoader().getResourceAsStream(imgPath));
        Button button = new Button();
        button.setTooltip(new Tooltip(name));
        button.setGraphic(new ImageView(buttonImg));
        button.setPrefSize(TOP_MENU_BUTTON_SIZE, TOP_MENU_BUTTON_SIZE);
        if (handler != null) {
            button.setOnAction(handler);
        }
        return button;
    }

    private Pane buildLeftMenu() {

        VBox vbox = new VBox(8);
        vbox.setPadding(new Insets(10)); // Set all sides to 10

        Text title = new Text("Menu");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);

        final Hyperlink summaryOption = new Hyperlink("Summary");
        summaryOption.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                currentCategoryId = 0;
                updateView();
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
                    currentCategoryId = c.getId();
                    updateView();
                    categoryOption.setVisited(false);
                }
            });
            vbox.setMargin(categoryOption, new Insets(0, 0, 0, 8));
            vbox.getChildren().add(categoryOption);
        }

        return vbox;
    }

    private Pane buildCategoryDetailsPane(long categoryId) {
        TableView<Expense> table = new TableView<>();
        final ObservableList<Expense> data = FXCollections.observableArrayList(processor.getExpensesByCategoryId(categoryId, dpm.getStartDate(), dpm.getEndDate()));

        table.setEditable(true);

        TableColumn dateColumn = new TableColumn("Date");
        dateColumn.setMinWidth(CATEGORY_DETAILS_COLUMN_DATE_WIDTH);
        dateColumn.setCellValueFactory(new PropertyValueFactory<Expense, Date>("date"));//TODO; adjust date format through setCellFactory method

        TableColumn merchantColumn = new TableColumn("Merchant");
        merchantColumn.setMinWidth(CATEGORY_DETAILS_COLUMN_MERCHANT_WIDTH);
        merchantColumn.setCellValueFactory(new PropertyValueFactory<Expense, String>("name"));

        TableColumn amountColumn = new TableColumn("Amount");
        amountColumn.setMinWidth(CATEGORY_DETAILS_COLUMN_AMOUNT_WIDTH);
        amountColumn.setCellValueFactory(new PropertyValueFactory<Expense, Double>("amount"));

        dateColumn.prefWidthProperty().bind(table.widthProperty().divide(4));
        merchantColumn.prefWidthProperty().bind(table.widthProperty().divide(2));
        amountColumn.prefWidthProperty().bind(table.widthProperty().divide(4));

        table.setItems(data);
        table.getColumns().addAll(dateColumn, merchantColumn, amountColumn);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().add(table);

        return vbox;
    }

    private Pane buildSummaryPane() {
        VBox summaryBox = new VBox();
        summaryBox.setSpacing(5);// Gap between nodes

        HBox topSummaryPane = buildTopSummaryPane();
        summaryBox.getChildren().add(topSummaryPane);

        HBox categoriesHeaderPane = buildCategoriesHeaderPane();
        summaryBox.getChildren().add(categoriesHeaderPane);

        Date start = dpm.getStartDate();
        Date end = dpm.getEndDate();

        VBox categoriesSummaryBox = new VBox();
        categoriesSummaryBox.setSpacing(5);

        for (Category c : processor.getAllCategories()) {
            ExpensesReport reportForCurrentCategory = processor.getExpensesReportForCategory(c.getId(), start, end);
            System.out.println("<addCenterPane>[report][from " + start + " to " + end + "]: " + reportForCurrentCategory);
            categoriesSummaryBox.getChildren().add(buildCategorySummaryPane(reportForCurrentCategory));
        }

        ScrollPane sp = new ScrollPane();
        sp.setContent(categoriesSummaryBox);
        sp.setFitToWidth(true);

        summaryBox.getChildren().add(sp);

        return summaryBox;
    }

    private HBox buildTopSummaryPane() {

        ExpensesReport allCategoriesExpensesReport = processor.getExpensesReportForAllCategories(dpm.getStartDate(), dpm.getEndDate());
        System.out.println("[DEBUG]<buildTopSummaryPane> allCategoriesExpensesReport[" + dpm.getStartDate() + "; " +
                dpm.getEndDate() + " ]: " + allCategoriesExpensesReport);

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
        Label monthlyCategoriesLimit = new Label("[Limit: " + allCategoriesExpensesReport.getMonthlyCategoryLimit() + "]");
        VBox leftAndBudgetPane = new VBox();
        leftAndBudgetPane.getChildren().addAll(left, monthlyCategoriesLimit);

        hbox.getChildren().addAll(alreadySpentPane, progressBarPane, leftAndBudgetPane);

        return hbox;
    }

    private HBox buildCategoriesHeaderPane() {

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5, 5, 5, 5));
        hbox.setSpacing(5);
        hbox.setStyle("-fx-background-color: cornflowerblue");

        Label categoryName = new Label("Category Name");
        categoryName.setStyle("-fx-font-weight: bold");
        categoryName.setPrefWidth(SUMMARY_PANEL_COLUMN_CATEGORY_NAME_WIDTH);

        Label currentAmount = new Label("Spent");
        currentAmount.setStyle("-fx-font-weight: bold");
        currentAmount.setPrefWidth(SUMMARY_PANEL_COLUMN_SPENT_WIDTH);

        HBox progressBarPane = new HBox();
        progressBarPane.setPrefWidth(SUMMARY_PANEL_COLUMN_PROGRESS_BAR_WIDTH);

        Label left = new Label("Leftover");
        left.setStyle("-fx-font-weight: bold");
        left.setPrefWidth(SUMMARY_PANEL_COLUMN_LEFTOVER_WIDTH);

        Label monthlyCategoryLimit = new Label("Limit");
        monthlyCategoryLimit.setStyle("-fx-font-weight: bold");
        monthlyCategoryLimit.setPrefWidth(SUMMARY_PANEL_COLUMN_MONTHLY_CAT_LIMIT_WIDTH);

        hbox.getChildren().addAll(categoryName, currentAmount, progressBarPane, left, monthlyCategoryLimit);

        return hbox;
    }

    private HBox buildCategorySummaryPane(ExpensesReport report) {

//        System.out.println("[DEBUG]<buildCategorySummaryPane> report: " + report);

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(5, 5, 5, 5));
        hbox.setSpacing(5);
        hbox.setStyle("-fx-background-color: gainsboro");

        Label categoryName = new Label(report.getCategory().getName());
        categoryName.setPrefWidth(SUMMARY_PANEL_COLUMN_CATEGORY_NAME_WIDTH);

        Label currentAmount = new Label(report.getCurrentAmount() + "");
        currentAmount.setPrefWidth(SUMMARY_PANEL_COLUMN_SPENT_WIDTH);

        HBox categoryProgressBarPane = buildProgressBarPane(report.getUsagePercent());
        categoryProgressBarPane.setPrefWidth(SUMMARY_PANEL_COLUMN_PROGRESS_BAR_WIDTH);

        Label left = new Label(report.getLeftover() + "");
        left.setPrefWidth(SUMMARY_PANEL_COLUMN_LEFTOVER_WIDTH);

        Label monthlyCategoryLimit = new Label(report.getMonthlyCategoryLimit() + "");
        monthlyCategoryLimit.setPrefWidth(SUMMARY_PANEL_COLUMN_MONTHLY_CAT_LIMIT_WIDTH);

        hbox.getChildren().addAll(categoryName, currentAmount, categoryProgressBarPane, left, monthlyCategoryLimit);

        return hbox;
    }

    private HBox buildProgressBarPane(double usagePercent) {
        HBox progressBarPane = new HBox();
        progressBarPane.setAlignment(Pos.CENTER);
        progressBarPane.setPrefWidth(PROGRESS_BAR_PANE_WIDTH);
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
