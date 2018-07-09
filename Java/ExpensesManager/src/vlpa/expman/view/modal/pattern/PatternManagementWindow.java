package vlpa.expman.view.modal.pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.model.ImportPattern;
import vlpa.expman.view.UIBuilder;
import vlpa.expman.view.modal.ModalWindowsHelper;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PatternManagementWindow {

    private UIBuilder builder;
    private MainProcessor processor;
    private Stage stage;

    private List<Category> categories;
    private ObservableList<String> categoriesData;
    private List<ImportPattern> patterns;
    private ObservableList<String> patternsData;
    private List<ImportPattern> addedPatterns;
    private List<ImportPattern> removedPatterns;
    private List<ImportPattern> updatedPatterns;

    public PatternManagementWindow(UIBuilder builder, MainProcessor processor) {
        this.builder = builder;
        this.processor = processor;
        init();
    }

    private void init() {

        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(builder.getPrimaryStage());
        stage.setTitle("Manage patterns");

        patterns = processor.getAllPatterns();
        patternsData = FXCollections.observableArrayList();
        for (ImportPattern importPattern : patterns) {
            patternsData.add(importPattern.getText());
        }

        VBox dialogVbox = new VBox(5);
        dialogVbox.setPadding(new Insets(10));
        dialogVbox.getChildren().add(new Text("New pattern"));

        HBox addPatternPaneCategory = new HBox(5);
        categories = processor.getAllCategories();
        categoriesData = FXCollections.observableArrayList();
        for (Category category : categories) {
            categoriesData.add(category.getName());
        }
        Label patternCategoryText = new Label("Category:");
        patternCategoryText.setPrefWidth(55);
        ComboBox<String> categoriesComboBox = new ComboBox<>(categoriesData);
        categoriesComboBox.setPrefWidth(340);

        addPatternPaneCategory.getChildren().addAll(patternCategoryText, categoriesComboBox);

        HBox addPatternPaneText = new HBox(5);
        Label patternTextLabel = new Label("Text:");
        patternTextLabel.setPrefWidth(55);
        TextField patternTextInput = new TextField();
        patternTextInput.setPrefWidth(340);
        addedPatterns = new LinkedList<>();
        Button addPatternButton = new Button("Add pattern");
        addPatternButton.setPrefWidth(110);
        addPatternButton.setOnAction(event -> {
            //TODO: validate input data
            System.out.println("Add pattern...");
            String patternText = patternTextInput.getText();
            int categorySelectedIndex = categoriesComboBox.getSelectionModel().getSelectedIndex();
            Category category = categories.get(categorySelectedIndex);
            ImportPattern newPattern = new ImportPattern(0, patternText, category);
            patterns.add(newPattern);
            patternsData.add(newPattern.getText());
            addedPatterns.add(newPattern);
            patternTextInput.clear();
            categoriesComboBox.getSelectionModel().clearSelection();
        });
        addPatternPaneText.getChildren().addAll(patternTextLabel, patternTextInput, addPatternButton);

        dialogVbox.getChildren().addAll(addPatternPaneText, addPatternPaneCategory);
        dialogVbox.getChildren().addAll(new Text(), new Separator());
        dialogVbox.getChildren().add(new Text("Existing patterns:"));

        HBox existingPatternsPane = new HBox(5);

        ListView<String> existingPatternsList = new ListView<>();
        existingPatternsList.setItems(patternsData);
        existingPatternsList.setPrefSize(400, 250);

        removedPatterns = new LinkedList<>();

        Button removePatternButton = new Button("Remove pattern");
        removePatternButton.setPrefWidth(110);
        removePatternButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int selectedIndex = existingPatternsList.getSelectionModel().getSelectedIndex();
                if (selectedIndex < 0) {
                    return;
                }
                System.out.println("selectedIndex: " + selectedIndex);
                String removedPatternName = patternsData.remove(selectedIndex);
                boolean temporarilyAdded = removeFromAddedList(removedPatternName);
                if (!temporarilyAdded) {
                    deleteFromRemovedList(removedPatternName);
                }
            }

            private boolean removeFromAddedList(String patternText) {
                boolean temporarilyAdded = false;
                for (int i = 0; i < addedPatterns.size(); i++) {
                    ImportPattern addedPattern = addedPatterns.get(i);
                    if (addedPattern.getText().equals(patternText)) {
                        temporarilyAdded = true;
                        addedPatterns.remove(i);
                        break;
                    }
                }
                return temporarilyAdded;
            }

            private void deleteFromRemovedList(String removedCategoryName) {
                for (Iterator<ImportPattern> iterator = patterns.iterator(); iterator.hasNext(); ) {
                    ImportPattern p = iterator.next();
                    if (p.getText().equals(removedCategoryName)) {
                        removedPatterns.add(p);
                        iterator.remove();
                        break;
                    }
                }
            }
        });

        VBox existingPatternsButtonPanel = new VBox(5);

        updatedPatterns = new LinkedList<>();

        Button modifyPatternButton = new Button("Modify pattern");
        modifyPatternButton.setPrefWidth(110);

        modifyPatternButton.setOnAction(event -> {
            int index = existingPatternsList.getSelectionModel().getSelectedIndex();
            ImportPattern pattern = patterns.get(index);
            ModifyPatternWindow modifyPatternWindow = ModalWindowsHelper.getModifyPatternWindow(builder, processor, pattern);
            modifyPatternWindow.setApplyHandler(new EventHandler() {
                @Override
                public void handle(Event event) {
                    if (modifyPatternWindow.isChanged()) {
                        ImportPattern updatedPattern = modifyPatternWindow.getPattern();
                        pattern.setText(updatedPattern.getText());
                        pattern.setCategory(updatedPattern.getCategory());
                        patternsData.set(index, updatedPattern.getText());
                        addPatternToUpdatedList(pattern);
                    }
                }

                private void addPatternToUpdatedList(ImportPattern patternToAdd) {
                    boolean isExist = false;
                    for (ImportPattern p : updatedPatterns) {
                        if (p.getId() == patternToAdd.getId()) {
                            p.setText(patternToAdd.getText());
                            p.setCategory(patternToAdd.getCategory());
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        updatedPatterns.add(patternToAdd);
                    }
                }
            });
            modifyPatternWindow.show();
        });

        existingPatternsButtonPanel.getChildren().addAll(removePatternButton, modifyPatternButton);
        existingPatternsPane.getChildren().addAll(existingPatternsList, existingPatternsButtonPanel);

        dialogVbox.getChildren().add(existingPatternsPane);

        HBox applyCancelPane = new HBox(20);
        applyCancelPane.setAlignment(Pos.CENTER);
        Button applyButton = new Button("Apply");
        applyButton.setPrefWidth(70);
        applyButton.setOnAction(event -> {
            System.out.println("----- ADDED PATTERNS -----");
            for (ImportPattern addedPattern : addedPatterns) {
                System.out.println("- c: " + addedPattern);
                processor.addPattern(addedPattern.getText(), addedPattern.getCategory());
            }
            System.out.println("----- REMOVED PATTERNS -----");
            for (ImportPattern removedPattern : removedPatterns) {
                System.out.println("- c: " + removedPattern);
                processor.removePattern(removedPattern.getId());
            }
            System.out.println("----- UPDATED PATTERNS -----");
            for (ImportPattern c : updatedPatterns) {
                System.out.println("- c: " + c);
                processor.updatePattern(c);
            }
            System.out.println("----- PATTERNS LIST -----");
            for (ImportPattern c : patterns) {
                System.out.println("- c: " + c);
            }

            builder.updateView();
            stage.close();
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(70);
        cancelButton.setOnAction(event -> stage.close());

        applyCancelPane.getChildren().addAll(applyButton, cancelButton);
        dialogVbox.getChildren().add(applyCancelPane);

        Scene dialogScene = new Scene(dialogVbox, 550, 420);
        stage.setScene(dialogScene);
    }

    public void show() {
        stage.show();
    }
}
