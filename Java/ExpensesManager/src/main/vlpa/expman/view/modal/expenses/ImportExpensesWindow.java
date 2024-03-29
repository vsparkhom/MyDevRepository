package vlpa.expman.view.modal.expenses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vlpa.expman.controller.imprt.ImportProcessor;
import vlpa.expman.controller.imprt.BankType;
import vlpa.expman.dao.exception.ExpensesDatabaseOperationException;
import vlpa.expman.view.UIBuilder;
import vlpa.expman.view.modal.ModalWindowsHelper;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ImportExpensesWindow {

    private final Logger LOGGER = LoggerFactory.getLogger(ImportExpensesWindow.class);

    private ImportProcessor importProcessor;
    private UIBuilder builder;
    private Stage stage;
    private ComboBox<String> accountTypesComboBox;

    private List<BankType> accountTypes = Arrays.asList(
        BankType.TD_BANK_ACCOUNT,
        BankType.PCF_BANK_ACCOUNT,
        BankType.CAPITAL_ONE_BANK_ACCOUNT
    );

    public ImportExpensesWindow(UIBuilder builder) {
        this.builder = builder;
        importProcessor = new ImportProcessor();
        init();
    }

    protected void init() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(builder.getPrimaryStage());
        stage.setTitle("Import expenses");

        VBox verticalPane = new VBox(5);
        verticalPane.setPadding(new Insets(10));

        HBox accountTypePane = new HBox(5);
        accountTypePane.setAlignment(Pos.BOTTOM_LEFT);
        Label accountTypeText = new Label("Account Type:");
        accountTypeText.setPrefWidth(75);

        ObservableList<String> accountTypesData = FXCollections.observableArrayList();
        for (BankType bt : accountTypes) {
            accountTypesData.add(bt.getName());
        }

        accountTypesComboBox = new ComboBox<>(accountTypesData);
        accountTypesComboBox.setPrefWidth(240);
        accountTypesComboBox.getSelectionModel().selectFirst();
        accountTypePane.getChildren().addAll(accountTypeText, accountTypesComboBox);

        HBox fileLocationPane = new HBox(5);
        TextField fileLocationTF = new TextField();
        fileLocationTF.setEditable(false);
        fileLocationTF.setPrefWidth(265);
        FileChooser fileChooser = new FileChooser();
        Button selectFileButton = new Button("Select");
        selectFileButton.setPrefWidth(50);
        selectFileButton.setOnAction(event -> {
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                fileLocationTF.setText(file.getAbsolutePath());
            }
        });
        fileLocationPane.getChildren().addAll(fileLocationTF, selectFileButton);

        verticalPane.getChildren().addAll(accountTypePane, new Label("File location:"), fileLocationPane);

        HBox applyCancelPane = new HBox(20);
        applyCancelPane.setPadding(new Insets(15, 0, 0, 0));
        applyCancelPane.setAlignment(Pos.CENTER);
        Button applyButton = new Button("Apply");
        applyButton.setPrefWidth(80);
        applyButton.setOnAction(event -> {
            String fileLocation = fileLocationTF.getText();
            if (fileLocation == null || "".equals(fileLocation)) {
                ModalWindowsHelper.getWarningDialog("File location can't be empty!",
                        "Please select file with data to import").showAndWait();
            } else {
                int selectedBankTypeIndex = accountTypesComboBox.getSelectionModel().getSelectedIndex();
                try {
                    importProcessor.importExpenses(fileLocation, accountTypes.get(selectedBankTypeIndex));
                    stage.close();
                    ModalWindowsHelper.getInformationDialog("Expenses import status",
                            "Expenses import has been successfully finished!").showAndWait();
                } catch (ExpensesDatabaseOperationException e) {
                    stage.close();
                    LOGGER.debug("Expenses import error", e);
                    ModalWindowsHelper.getErrorDialog("Expenses import error",
                            "Some issue occurred during storing expenses to database. Please contact administrator.").showAndWait();
                } catch (Exception e) {
                    stage.close();
                    LOGGER.debug("Expenses import error", e);
                    ModalWindowsHelper.getErrorDialog("Expenses import error",
                            "Some issue occurred during expenses import. Please contact administrator.").showAndWait();
                }
            }
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(80);
        cancelButton.setOnAction(event -> stage.close());

        applyCancelPane.getChildren().addAll(applyButton, cancelButton);
        verticalPane.getChildren().add(applyCancelPane);

        Scene dialogScene = new Scene(verticalPane, 340, 150);
        stage.setScene(dialogScene);
    }

    public void show() {
        stage.show();
    }
}
