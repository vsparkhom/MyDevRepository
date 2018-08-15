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
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.dao.DBConsts;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.model.ExpensesReport;
import vlpa.expman.view.datepicker.CustomDatePicker;
import vlpa.expman.view.datepicker.MonthlyDatePickerMenu;
import vlpa.expman.view.modal.ModalWindowsHelper;

import static vlpa.expman.view.UIDimensionsConst.*;

import java.io.File;
import java.util.Date;
import java.util.Optional;

public class UIBuilder {

    private static final String CSS_STYLE_FILE_NAME = "view.css";

    private MainProcessor processor = MainProcessor.getInstance();

    //UI elements
    private Stage primaryStage;
    private CustomDatePicker dpm;
    private HBox manageExpensesButtonsPane;
    private TableView<Expense> currentCategoryExpensesTable;

    private long currentCategoryId = 0; //Summary pane by default

    private UIBuilder() {
    }

    private static class UIBuilderInstanceHolder {
        static UIBuilder instance = new UIBuilder();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
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

        BorderPane rootPane = new BorderPane();

        Pane topMenu = buildTopMenu(stage);
        addBorder(topMenu, "grey");
        topMenu.setPrefWidth(TOP_MENU_WIDTH);
        rootPane.setTop(topMenu);

        Pane leftMenu = buildLeftMenu();
        addBorder(leftMenu, "grey");
        leftMenu.setPrefWidth(LEFT_MENU_WIDTH);
        rootPane.setLeft(leftMenu);

        Pane center = currentCategoryId == 0 ? buildSummaryPane() : buildCategoryDetailsMainPane(currentCategoryId);
        center.setPrefWidth(CENTER_MENU_WIDTH);
        center.setPrefHeight(CENTER_MENU_HEIGHT);
        rootPane.setCenter(center);

        return rootPane;
    }

    public void updateView() {
        getPrimaryStage().setScene(buildPrimaryScene(getPrimaryStage()));
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
        if (dpm == null) {
            dpm = new MonthlyDatePickerMenu(this);
        }
        return dpm.getMenuPane();
    }

    private HBox buildMenuButtons(final Stage stage) {
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(15, 12, 15, 12));
//        hbox.setStyle("-fx-background-color: #336699;");

        Button importButton = createMenuButton("Import expense", "img/import.png", event -> {
            ModalWindowsHelper.getImportExpensesWindow(this).show();
        });

        Button addPatternButton = createMenuButton("Add pattern", "img/list.png", event -> {
            ModalWindowsHelper.getPatternManagementWindow(this, processor).show();
        });
        Button addCategoryButton = createMenuButton("Manage categories", "img/add.png",
                event -> ModalWindowsHelper.getCategoriesManagementWindow(UIBuilder.getInstance(), processor).show());

        hbox.getChildren().addAll(importButton, addPatternButton, addCategoryButton);
        return hbox;
    }

    private HBox getExpensesManageButtonsPane() {

        if (manageExpensesButtonsPane != null) {
            return manageExpensesButtonsPane;
        }

        manageExpensesButtonsPane = new HBox(10);

        Button addButton = createManageExpenseButton("Add", event -> {
            ModalWindowsHelper.getAddExpenseWindow(this, processor).show();
        });
        Button removeButton = createManageExpenseButton("Remove", event -> {
            Alert alert = ModalWindowsHelper.getConfirmationDialog(
                    "Records about selected expense will be removed. Please confirm.",
                    "Are you sure you want to remove expense?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK){
                Expense expenseToRemove = currentCategoryExpensesTable.getSelectionModel().getSelectedItem();
                processor.removeExpense(expenseToRemove.getId());
                updateView();
            }
        });
        Button changeButton = createManageExpenseButton("Modify", event -> {
            Expense expenseToRemove = currentCategoryExpensesTable.getSelectionModel().getSelectedItem();
            ModalWindowsHelper.getModifyExpenseWindow(this, processor, expenseToRemove).show();
        });

        manageExpensesButtonsPane.getChildren().addAll(addButton, removeButton, changeButton);

        return manageExpensesButtonsPane;
    }

    private Button createMenuButton(String tooltip, String imgPath) {
        return createMenuButton(tooltip, imgPath, null);
    }

    private Button createMenuButton(String tooltip, String imgPath, EventHandler<ActionEvent> handler) {
        return createCustomButton(null, tooltip, imgPath, TOP_MENU_BUTTON_WIDTH, TOP_MENU_BUTTON_HEIGHT, handler);
    }

    private Button createManageExpenseButton(String name, EventHandler<ActionEvent> handler) {
        return createCustomButton(name, null, null, 70, 40, handler);
    }

    private Button createCustomButton(String name, String tooltip, String imgPath, double width, double height,
            EventHandler<ActionEvent> handler) {
        Button button = new Button();
        button.setText(name);
        button.setPrefSize(width, height);
        if (tooltip != null && !"".equals(tooltip)) {
            button.setTooltip(new Tooltip(tooltip));
        }
        if (imgPath != null && !"".equals(imgPath)) {
            Image buttonImg = new Image(getClass().getClassLoader().getResourceAsStream(imgPath));
            button.setGraphic(new ImageView(buttonImg));
        }
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
        summaryOption.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->  {
            currentCategoryId = 0;
            updateView();
            summaryOption.setVisited(false);
        });
        VBox.setMargin(summaryOption, new Insets(0, 0, 0, 8));
        vbox.getChildren().add(summaryOption);

        for (final Category c : processor.getAllCategories()) {
            // Add offset to left side to indent from title
            final Hyperlink categoryOption = new Hyperlink(c.getName());
            categoryOption.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                currentCategoryId = c.getId();
                updateView();
                categoryOption.setVisited(false);
            });
            VBox.setMargin(categoryOption, new Insets(0, 0, 0, 8));
            vbox.getChildren().add(categoryOption);
        }

        return vbox;
    }

    private Pane buildCategoryDetailsMainPane(long categoryId) {
        currentCategoryExpensesTable = new TableView<>();
        ObservableList<Expense> currentCategoryExpensesList = FXCollections.observableArrayList(processor.getExpensesByCategoryId(categoryId, dpm.getStartDate(), dpm.getEndDate()));

        currentCategoryExpensesTable.setEditable(true);

        TableColumn dateColumn = new TableColumn("Date");
        dateColumn.setMinWidth(CATEGORY_DETAILS_COLUMN_DATE_WIDTH);
        dateColumn.setCellValueFactory(new PropertyValueFactory<Expense, Date>("date"));//TODO; adjust date format through setCellFactory method

        TableColumn merchantColumn = new TableColumn("Merchant");
        merchantColumn.setMinWidth(CATEGORY_DETAILS_COLUMN_MERCHANT_WIDTH);
        merchantColumn.setCellValueFactory(new PropertyValueFactory<Expense, String>("name"));

        TableColumn amountColumn = new TableColumn("Amount");
        amountColumn.setMinWidth(CATEGORY_DETAILS_COLUMN_AMOUNT_WIDTH);
        amountColumn.setCellValueFactory(new PropertyValueFactory<Expense, Double>("amount"));

        dateColumn.prefWidthProperty().bind(currentCategoryExpensesTable.widthProperty().divide(4));
        merchantColumn.prefWidthProperty().bind(currentCategoryExpensesTable.widthProperty().divide(2));
        amountColumn.prefWidthProperty().bind(currentCategoryExpensesTable.widthProperty().divide(4));

        currentCategoryExpensesTable.setItems(currentCategoryExpensesList);
        currentCategoryExpensesTable.getColumns().addAll(dateColumn, merchantColumn, amountColumn);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(getCategoryDetailsTopPane(categoryId), currentCategoryExpensesTable);

        return vbox;
    }

    private Pane getCategoryDetailsTopPane(long categoryId) {

        ExpensesReport report = processor.getExpensesReportForCategory(categoryId, dpm.getStartDate(), dpm.getEndDate());

        HBox categoryInfoPane = new HBox(10);
        categoryInfoPane.setPadding(new Insets(0, 10, 0, 0));
        categoryInfoPane.setAlignment(Pos.BOTTOM_RIGHT);
        categoryInfoPane.getChildren().add(new Label("Limit: " + report.getCategory().getLimit()
                + " / Spent: " + report.getCurrentAmount() + " (" + report.getUsagePercent() * 100 + "%) / Leftover: "
                + report.getLeftover()));
        HBox.setHgrow(categoryInfoPane, Priority.ALWAYS);

        HBox categoryDetailsTopPane = new HBox(10);
        categoryDetailsTopPane.getChildren().add(getExpensesManageButtonsPane());

        System.out.println("[DEBUG]<getCategoryDetailsTopPane> currentCategoryId: " + currentCategoryId);
        if (currentCategoryId == DBConsts.UNKNOWN_CATEGORY_ID) {
            Button createPatternButton = new Button("Create pattern");
            createPatternButton.setOnAction(event -> {
                Expense selectedExpense = currentCategoryExpensesTable.getSelectionModel().getSelectedItem();
                if (selectedExpense != null) {
                    ModalWindowsHelper.getGeneratePatternWindow(this, processor, selectedExpense.getName()).show();
                }
            });
            categoryDetailsTopPane.getChildren().add(createPatternButton);
        }

        categoryDetailsTopPane.getChildren().add(categoryInfoPane);
        return categoryDetailsTopPane;
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

    public long getCurrentCategoryId() {
        return currentCategoryId;
    }
}
