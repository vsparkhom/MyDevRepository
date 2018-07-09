package vlpa.expman.view.modal.pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import vlpa.expman.model.ImportPattern;
import vlpa.expman.view.UIBuilder;

import java.util.List;

public class ModifyPatternWindow {

    private UIBuilder builder;
    private MainProcessor processor;
    private Stage stage;
    private ImportPattern pattern;
    private boolean isChanged;
    private EventHandler handler;

    private List<Category> categories;
    private ObservableList<String> categoriesData;

    public ModifyPatternWindow(UIBuilder builder, MainProcessor processor, ImportPattern pattern) {
        this.builder = builder;
        this.processor = processor;
        this.pattern = pattern;
        init();
    }

    private void init() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(builder.getPrimaryStage());
        stage.setTitle("Modify pattern");

        VBox verticalPane = new VBox(8);
        verticalPane.setPadding(new Insets(10));

        HBox patternTextPane = new HBox(8);
        Label patternTextLabel = new Label("Text: ");
        patternTextLabel.setPrefWidth(55);
        TextField patternTextInput = new TextField(pattern.getText());
        patternTextInput.setPrefWidth(340);
        patternTextPane.getChildren().addAll(patternTextLabel, patternTextInput);

        HBox patternCategoryPane = new HBox(5);
        categories = processor.getAllCategories();
        categoriesData = FXCollections.observableArrayList();
        for (Category category : categories) {
            categoriesData.add(category.getName());
        }
        Label patternCategoryText = new Label("Category:");
        patternCategoryText.setPrefWidth(55);
        ComboBox<String> patternCategoryComboBox = new ComboBox<>(categoriesData);
        patternCategoryComboBox.setPrefWidth(340);
        List<String> patternComboBoxItems = patternCategoryComboBox.getItems();
        for (int i=0; i<patternComboBoxItems.size(); i++) {
            String currentPatternText = patternComboBoxItems.get(i);
            if (currentPatternText.equals(pattern.getCategory().getName())) {
                patternCategoryComboBox.getSelectionModel().select(i);
                break;
            }
        }
        patternCategoryPane.getChildren().addAll(patternCategoryText, patternCategoryComboBox);

        HBox applyCancelPane = new HBox(20);
        applyCancelPane.setAlignment(Pos.CENTER);
        Button applyButton = new Button("Apply");
        applyButton.setPrefWidth(70);
        applyButton.setOnAction(event -> {
            System.out.println("Update category...");
            String updatedPatternText = patternTextInput.getText();
            int selectedIndex = patternCategoryComboBox.getSelectionModel().getSelectedIndex();
            Category updatedCategory = categories.get(selectedIndex);
            if (!pattern.getText().equals(updatedPatternText)) {
                isChanged = true;
                pattern.setText(updatedPatternText);

            }
            if (!pattern.getCategory().equals(updatedCategory)) {
                isChanged = true;
                pattern.setCategory(updatedCategory);
            }
            if (handler != null) {
                handler.handle(null);
            }
            stage.close();
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(70);
        cancelButton.setOnAction(event -> stage.close());

        applyCancelPane.getChildren().addAll(applyButton, cancelButton);

        verticalPane.getChildren().addAll(patternTextPane, patternCategoryPane, new Separator(), applyCancelPane);

        Scene dialogScene = new Scene(verticalPane, 440, 120);
        stage.setScene(dialogScene);
    }

    public ImportPattern getPattern() {
        return pattern;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setApplyHandler(EventHandler handler) {
        this.handler = handler;
    }

    public void show() {
        stage.show();
    }
}
