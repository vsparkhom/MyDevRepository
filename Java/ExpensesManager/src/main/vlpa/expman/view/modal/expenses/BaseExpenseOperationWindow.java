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
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import vlpa.expman.controller.ExpenseUtils;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.controller.imprt.BankType;
import vlpa.expman.model.Category;
import vlpa.expman.model.Expense;
import vlpa.expman.view.UIBuilder;

import javafx.event.EventHandler;
import vlpa.expman.view.modal.exception.EntityValidationException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static vlpa.expman.view.UIDimensionsConst.DATE_PICKER_WIDTH;

public class BaseExpenseOperationWindow {

    private UIBuilder builder;
    private MainProcessor processor;

    private Stage stage;
    private TextField nameInput;
    private TextField amountInput;
    private DatePicker datePicker;
    private ComboBox<String> categoriesComboBox;
    private Button applyButton;
    private LocalDate date;
    private List<Category> categories;
    private ObservableList<String> categoriesData;
    private ComboBox bankComboBox;
    private ObservableList<String> bankList;
    private TextArea descrTextArea;

    BaseExpenseOperationWindow() {
    }

    BaseExpenseOperationWindow(UIBuilder builder, MainProcessor processor) {
        this.builder = builder;
        this.processor = processor;
        init();
    }

    protected void init() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(builder.getPrimaryStage());
        stage.setTitle(getWindowTitle());
        stage.setResizable(false);

        categories = processor.getAllCategories();
        categoriesData = FXCollections.observableArrayList();
        for (Category category : categories) {
            categoriesData.add(category.getName());
        }

        VBox verticalPane = new VBox(5);
        verticalPane.setPadding(new Insets(10));

        HBox expenseNamePane = new HBox(5);

        Label nameText = new Label("Name:");
        nameText.setPrefWidth(55);
        nameInput = new TextField();
        nameInput.setPrefWidth(240);

        expenseNamePane.getChildren().addAll(nameText, nameInput);

        HBox expenseInformationPane = new HBox(5);

        Label dateText = new Label("Date:");
        dateText.setPrefWidth(55);
        datePicker = new DatePicker();
        datePicker.setPrefWidth(DATE_PICKER_WIDTH);
        datePicker.setValue(LocalDate.now());
        setDate(LocalDate.now());
        datePicker.setOnAction(event -> {
            date = datePicker.getValue();
        });

        Label amountText = new Label("Amount:");
        amountText.setPrefWidth(50);
        amountInput = new TextField();
        amountInput.setPrefWidth(75);

        expenseInformationPane.getChildren().addAll(dateText, datePicker, amountText, amountInput);

        HBox categoriesPane = new HBox(5);
        Label chooseCategoryText = new Label("Category:");
        chooseCategoryText.setPrefWidth(55);
        categoriesComboBox = new ComboBox<>(categoriesData);
        categoriesComboBox.setPrefWidth(240);
        selectProperCategory();
        categoriesPane.getChildren().addAll(chooseCategoryText, categoriesComboBox);

        HBox bankPane = new HBox(5);
        Label bankText = new Label("Bank:");
        bankText.setPrefWidth(55);
        bankList = FXCollections.observableArrayList();
        bankList.addAll(CollectionUtils.collect(Arrays.asList(BankType.values()),
                new Transformer<BankType, String>() {
                    @Override
                    public String transform(BankType i) {
                        return i.getName();
                    }
                }));
        bankComboBox = new ComboBox<>(bankList);
        bankComboBox.setPrefWidth(240);
        selectProperBank();
        bankPane.getChildren().addAll(bankText, bankComboBox);

        VBox descrPane = new VBox(5);
        Label descrText = new Label("Description:");
        descrTextArea = new TextArea();
        descrTextArea.setPrefWidth(240);
        descrPane.getChildren().addAll(descrText, descrTextArea);

        verticalPane.getChildren().addAll(expenseNamePane, expenseInformationPane, categoriesPane, bankPane, descrPane);

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

        Scene dialogScene = new Scene(verticalPane, 320, 250);
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

    ComboBox getBankComboBox() {
        return bankComboBox;
    }

    ObservableList<String> getBanks() {
        return bankList;
    }

    TextArea getDescrTextArea() {
        return descrTextArea;
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

    UIBuilder getBuilder() {
        return builder;
    }

    void setBuilder(UIBuilder builder) {
        this.builder = builder;
    }

    MainProcessor getProcessor() {
        return processor;
    }

    void setProcessor(MainProcessor processor) {
        this.processor = processor;
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

    protected void selectProperBank() {
        //DO NOTHING
    }

    protected EventHandler<ActionEvent> getApplyButtonAction() {
        return event -> {
            String expenseName = getNameInput().getText();
            String expenseAmountText = getAmountInput().getText();
            double expenseAmount = Double.valueOf(expenseAmountText);
            int selectedCategoryIndex = getCategoriesComboBox().getSelectionModel().getSelectedIndex();

            validateInputData(expenseName, expenseAmountText, selectedCategoryIndex);

            Date expenseDate = ExpenseUtils.fromLocalDateToDate(getDate());
            Category selectedCategory = getCategories().get(selectedCategoryIndex);

            String selectedBank = (String) getBankComboBox().getSelectionModel().getSelectedItem();
            String description = getDescrTextArea().getText();

            Expense updatedExpense = Expense.builder()
                    .setName(expenseName)
                    .setDate(expenseDate)
                    .setAmount(expenseAmount)
                    .setCategory(selectedCategory)
                    .setBank(selectedBank)
                    .setDescription(description).build();

            performApplyActions(updatedExpense);
        };
    }

    protected void performApplyActions(Expense updatedExpense) {
        //DO_NOTHING
    }

    protected void validateInputData(String expenseName, String expenseAmountText, int selectedCategoryIndex) {
        if (isEmpty(expenseName) || isEmpty(expenseAmountText)) {
            throw new EntityValidationException("Expense", "Expense name and amount should not be empty!");
        }

        double amount = 0;
        try {
            amount = Double.parseDouble(expenseAmountText);
        } catch (NumberFormatException e) {
            throw new EntityValidationException("Expense", "Expense limit should be a number!");
        }

        if (amount <= 0) {
            throw new EntityValidationException("Expense", "Expense amount should be greater than zero!");
        }
    }

    protected boolean isEmpty(String s) {
        return s == null || "".equals(s);
    }

    public void show() {
        stage.show();
    }
}
