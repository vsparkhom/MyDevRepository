package vlpa.expman.view.modal.pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

import java.util.List;

public class BasePatternOperationWindow {

    private UIBuilder builder;
    private MainProcessor processor;

    private Stage stage;
    private TextField patternInput;
    private Button applyButton;
    private ComboBox<String> categoriesComboBox;
    private List<Category> categories;
    private ObservableList<String> categoriesData;

    BasePatternOperationWindow() {
    }

    BasePatternOperationWindow(UIBuilder builder, MainProcessor processor) {
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

        VBox verticalPane = new VBox(5);
        verticalPane.setPadding(new Insets(10));

        HBox patternPane = new HBox(5);

        Label patternText = new Label("Pattern:");
        patternText.setPrefWidth(55);
        patternInput = new TextField();
        patternInput.setPrefWidth(240);

        patternPane.getChildren().addAll(patternText, patternInput);

        HBox categoriesPane = new HBox(8);
        Label chooseCategoryText = new Label("Category:");
        chooseCategoryText.setPrefWidth(55);
        categoriesComboBox = new ComboBox<>(categoriesData);
        categoriesComboBox.setPrefWidth(240);
        categoriesComboBox.getSelectionModel().selectFirst();
        categoriesPane.getChildren().addAll(chooseCategoryText, categoriesComboBox);

        verticalPane.getChildren().addAll(patternPane, categoriesPane);

        HBox applyCancelPane = new HBox(20);
        applyCancelPane.setPadding(new Insets(15, 0, 0, 0));
        applyCancelPane.setAlignment(Pos.CENTER);
        applyButton = new Button("Apply");
        applyButton.setPrefWidth(80);
        applyButton.setOnAction(getApplyButtonAction());
        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(80);
        cancelButton.setOnAction(event -> stage.close());

        applyCancelPane.getChildren().addAll(applyButton, cancelButton);
        verticalPane.getChildren().add(applyCancelPane);

        Scene dialogScene = new Scene(verticalPane, 320, 130);
        stage.setScene(dialogScene);
    }

    protected String getWindowTitle() {
        return "Base pattern operation";
    }

    public void show() {
        stage.show();
    }

    public void close() {
        stage.close();
    }

    protected EventHandler<ActionEvent> getApplyButtonAction() {
        return null;
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

    public List<Category> getCategories() {
        return categories;
    }

    public ComboBox<String> getCategoriesComboBox() {
        return categoriesComboBox;
    }

    TextField getPatternInput() {
        return patternInput;
    }

}
