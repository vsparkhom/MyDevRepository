package vlpa.expman.view.modal.expenses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.view.UIBuilder;

import javafx.event.EventHandler;
import java.time.LocalDate;
import java.util.List;

import static vlpa.expman.view.UIDimensionsConst.DATE_PICKER_WIDTH;

public class BaseExpenseOperationWindow {

    UIBuilder builder;
    MainProcessor processor;

    private Stage stage;
    private TextField nameInput;
    private TextField amountInput;
    private DatePicker datePicker;
    private ComboBox<String> categoriesComboBox;
    private Button applyButton;
    private LocalDate date;
    private List<Category> categories;
    private ObservableList<String> categoriesData;

    BaseExpenseOperationWindow() {
    }

    public BaseExpenseOperationWindow(UIBuilder builder, MainProcessor processor) {
        this.builder = builder;
        this.processor = processor;
        init();
    }

    protected void init() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(builder.getPrimaryStage());
        stage.setTitle(getWindowTitle());

        categories = processor.getAllCategories();
        categoriesData = FXCollections.observableArrayList();
        for (Category category : categories) {
            categoriesData.add(category.getName());
        }

        VBox verticalPane = new VBox(8);
        verticalPane.setPadding(new Insets(10));

        HBox expenseNamePane = new HBox(8);

        Label nameText = new Label("Name:");
        nameText.setPrefWidth(55);
        nameInput = new TextField();
        nameInput.setPrefWidth(240);

        expenseNamePane.getChildren().addAll(nameText, nameInput);

        HBox expenseInformationPane = new HBox(8);

        Label dateText = new Label("Date:");
        dateText.setPrefWidth(55);
        datePicker = new DatePicker();
        datePicker.setPrefWidth(DATE_PICKER_WIDTH);
        datePicker.setValue(LocalDate.now());
        setDate(LocalDate.now());
        datePicker.setOnAction(event -> {
            date = datePicker.getValue();
            System.out.println("Selected date: " + date);
        });

        Label amountText = new Label("Amount:");
        amountText.setPrefWidth(50);
        amountInput = new TextField();
        amountInput.setPrefWidth(75);

        expenseInformationPane.getChildren().addAll(dateText, datePicker, amountText, amountInput);

        HBox categoriesPane = new HBox(8);
        Label chooseCategoryText = new Label("Category:");
        chooseCategoryText.setPrefWidth(55);
        categoriesComboBox = new ComboBox<>(categoriesData);
        categoriesComboBox.setPrefWidth(240);
        selectProperCategory();
        categoriesPane.getChildren().addAll(chooseCategoryText, categoriesComboBox);

        verticalPane.getChildren().addAll(expenseNamePane, expenseInformationPane, categoriesPane);

        HBox applyCancelPane = new HBox(20);
        applyCancelPane.setAlignment(Pos.CENTER);
        applyButton = new Button("Apply");
        applyButton.setPrefWidth(80);
        applyButton.setOnAction(getApplyButtonAction());
        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(80);
        cancelButton.setOnAction(event -> stage.close());

        applyCancelPane.getChildren().addAll(applyButton, cancelButton);
        verticalPane.getChildren().add(applyCancelPane);

        Scene dialogScene = new Scene(verticalPane, 320, 150);
        stage.setScene(dialogScene);
    }

    TextField getNameInput() {
        return nameInput;
    }

    TextField getAmountInput() {
        return amountInput;
    }

    DatePicker getDatePicker() {
        return datePicker;
    }

    ComboBox<String> getCategoriesComboBox() {
        return categoriesComboBox;
    }

    Button getApplyButton() {
        return applyButton;
    }

    List<Category> getCategories() {
        return categories;
    }

    ObservableList<String> getCategoriesData() {
        return categoriesData;
    }

    LocalDate getDate() {
        return date;
    }

    void setDate(LocalDate date) {
        this.date = date;
    }

    Stage getStage() {
        return stage;
    }

    protected String getWindowTitle() {
        return "Base expense operation";
    }

    protected void selectProperCategory() {
        //DO NOTHING
    }

    protected EventHandler<ActionEvent> getApplyButtonAction() {
        return null;
    }

    public void show() {
        stage.show();
    }
}
