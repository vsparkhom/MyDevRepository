package vlpa.expman.view.modal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vlpa.expman.controller.ExpenseUtils;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.view.UIBuilder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static vlpa.expman.view.UIDimensionsConst.DATE_PICKER_WIDTH;

public class ModifyExpenseWindow {

    private UIBuilder builder;
    private MainProcessor processor;
    private Stage stage;

    protected LocalDate date;
    private Expense expense;

    ModifyExpenseWindow(UIBuilder builder, MainProcessor processor, Expense expense) {
        this.builder = builder;
        this.processor = processor;
        this.expense = expense;
        init();
    }

    private void init() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(builder.getPrimaryStage());
        stage.setTitle("Modify expense");

        List<Category> categories = processor.getAllCategories();
        ObservableList<String> categoriesData = FXCollections.observableArrayList();
        for (Category category : categories) {
            categoriesData.add(category.getName());
        }

        VBox verticalPane = new VBox(8);
        verticalPane.setPadding(new Insets(10));

        HBox expenseNamePane = new HBox(8);

        Label nameText = new Label("Name:");
        nameText.setPrefWidth(55);
        TextField nameInput = new TextField();
        nameInput.setPrefWidth(240);
        nameInput.setText(expense.getName());

        expenseNamePane.getChildren().addAll(nameText, nameInput);

        HBox expenseInformationPane = new HBox(8);

        Label dateText = new Label("Date:");
        dateText.setPrefWidth(55);
        DatePicker datePicker = new DatePicker();
        datePicker.setPrefWidth(DATE_PICKER_WIDTH);
        datePicker.setValue(LocalDate.now());
        date = ExpenseUtils.fromDateToLocalDate(expense.getDate());
        datePicker.setOnAction(event -> {
            date = datePicker.getValue();
            System.out.println("Selected date: " + date);
        });

        Label amountText = new Label("Amount:");
        amountText.setPrefWidth(50);
        TextField amountInput = new TextField();
        amountInput.setText("" + expense.getAmount());
        amountInput.setPrefWidth(75);

        expenseInformationPane.getChildren().addAll(dateText, datePicker, amountText, amountInput);

        HBox categoriesPane = new HBox(8);
        Label chooseCategoryText = new Label("Category:");
        chooseCategoryText.setPrefWidth(55);
        ComboBox<String> categoriesComboBox = new ComboBox<>(categoriesData);
        categoriesComboBox.setPrefWidth(240);
        for (int i = 0; i < categoriesData.size(); i++) {
            if (categoriesData.get(i).equals(expense.getCategory().getName())) {
                categoriesComboBox.getSelectionModel().select(i);
                break;
            }
        }
        categoriesPane.getChildren().addAll(chooseCategoryText, categoriesComboBox);

        verticalPane.getChildren().addAll(expenseNamePane, expenseInformationPane, categoriesPane);

        HBox applyCancelPane = new HBox(20);
        applyCancelPane.setAlignment(Pos.CENTER);
        Button applyButton = new Button("Apply");
        applyButton.setPrefWidth(80);
        applyButton.setOnAction(event -> {

            String expenseName = nameInput.getText();
            Date expenseDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
            double expenseAmount = Double.valueOf(amountInput.getText());//TODO: add validation
            int selectedCategoryIndex = categoriesComboBox.getSelectionModel().getSelectedIndex();
            Category selectedCategory = categories.get(selectedCategoryIndex);

            boolean isChanged = false;
            if (!expense.getName().equals(expenseName)) {
                expense.setName(expenseName);
                isChanged = true;
            }
            if (!expense.getDate().equals(expenseDate)) {
                expense.setDate(expenseDate);
                isChanged = true;
            }
            if (expense.getAmount() != expenseAmount) {
                expense.setAmount(expenseAmount);
                isChanged = true;
            }
            if (expense.getCategory().getId() != selectedCategory.getId()) {
                expense.setCategory(selectedCategory);
                isChanged = true;
            }

            if (isChanged) {
                processor.updateExpense(expense);
            }

            builder.updateView();
            stage.close();
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(80);
        cancelButton.setOnAction(event -> stage.close());

        applyCancelPane.getChildren().addAll(applyButton, cancelButton);
        verticalPane.getChildren().add(applyCancelPane);

        Scene dialogScene = new Scene(verticalPane, 320, 150);
        stage.setScene(dialogScene);
    }

    public void show() {
        stage.show();
    }

}
