package vlpa.expman.view.modal.categories;

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
import vlpa.expman.view.UIBuilder;
import vlpa.expman.view.modal.ModalWindowsHelper;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CategoriesManagementWindow {

    private UIBuilder builder;
    private MainProcessor processor;
    private Stage stage;

    private List<Category> categories;
    private ObservableList<String> categoriesData;
    private List<Category> addedCategories;
    private List<Category> removedCategories;
    private List<Category> updatedCategories;

    public CategoriesManagementWindow(UIBuilder builder, MainProcessor processor) {
        this.builder = builder;
        this.processor = processor;
        init();
    }

    private void init() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(builder.getPrimaryStage());
        stage.setTitle("Manage categories");

        categories = processor.getAllCategories();
        categoriesData = FXCollections.observableArrayList();
        for (Category category : categories) {
            categoriesData.add(category.getName());
        }

        VBox dialogVbox = new VBox(8);
        dialogVbox.setPadding(new Insets(10));
        dialogVbox.getChildren().add(new Text("New category"));

        HBox addControlPane = new HBox(8);

        Label nameText = new Label("Name:");
        TextField nameInput = new TextField();
        nameInput.setPrefWidth(240);

        Label limitText = new Label("Limit:");
        TextField limitInput = new TextField();
        limitInput.setPrefWidth(70);

        addedCategories = new LinkedList<>();

        Button addCategoryButton = new Button("Add category");
        addCategoryButton.setPrefWidth(110);
        addCategoryButton.setOnAction(event -> {
            //TODO: validate input data
            String name = nameInput.getText();
            double limit = Double.parseDouble(limitInput.getText());
            Category newCategory = new Category(0, name, limit);
            categories.add(newCategory);
            categoriesData.add(name);
            addedCategories.add(newCategory);
            nameInput.clear();
            limitInput.clear();
        });

        addControlPane.getChildren().addAll(nameText, nameInput, limitText, limitInput, addCategoryButton);

        dialogVbox.getChildren().add(addControlPane);
        dialogVbox.getChildren().addAll(new Text(), new Separator());
        dialogVbox.getChildren().add(new Text("Existing categories:"));

        HBox existingCategoriesPane = new HBox(8);

        ListView<String> existingCategoriesList = new ListView<>();
        existingCategoriesList.setItems(categoriesData);
        existingCategoriesList.setPrefSize(400, 250);

        removedCategories = new LinkedList<>();

        Button removeCategoryButton = new Button("Remove category");
        removeCategoryButton.setPrefWidth(110);
        removeCategoryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int selectedIndex = existingCategoriesList.getSelectionModel().getSelectedIndex();
                if (selectedIndex < 0) {
                    return;
                }
                System.out.println("selectedIndex: " + selectedIndex);
                String removedCategoryName = categoriesData.remove(selectedIndex);
                boolean temporarilyAdded = removeFromAddedCategoriesList(removedCategoryName);
                if (!temporarilyAdded) {
                    deleteFromRemovedCategoriesList(removedCategoryName);
                }
            }

            private boolean removeFromAddedCategoriesList(String categoryName) {
                boolean temporarilyAdded = false;
                for (int i = 0; i < addedCategories.size(); i++) {
                    Category addedCategory = addedCategories.get(i);
                    if (addedCategory.getName().equals(categoryName)) {
                        temporarilyAdded = true;
                        addedCategories.remove(i);
                        break;
                    }
                }
                return temporarilyAdded;
            }

            private void deleteFromRemovedCategoriesList(String removedCategoryName) {
                for (Iterator<Category> iterator = categories.iterator(); iterator.hasNext(); ) {
                    Category c = iterator.next();
                    if (c.getName().equals(removedCategoryName)) {
                        removedCategories.add(c);
                        iterator.remove();
                        break;
                    }
                }
            }
        });

        VBox existingCategoriesButtonPanel = new VBox(8);

        updatedCategories = new LinkedList<>();

        Button modifyCategoryButton = new Button("Modify category");
        modifyCategoryButton.setPrefWidth(110);

        modifyCategoryButton.setOnAction(event -> {
            int index = existingCategoriesList.getSelectionModel().getSelectedIndex();
            Category category = categories.get(index);
            ModifyCategoryWindow modifyCategoryWindow = ModalWindowsHelper.getModifyCategoryWindow(builder, category);
            modifyCategoryWindow.setApplyHandler(new EventHandler() {
                @Override
                public void handle(Event event) {
                    if (modifyCategoryWindow.isChanged()) {
                        Category updatedCategory = modifyCategoryWindow.getCategory();
                        category.setName(updatedCategory.getName());
                        category.setLimit(updatedCategory.getLimit());
                        categoriesData.set(index, updatedCategory.getName());
                        addCategoryToUpdatedCategoriesList(category);
                    }
                }

                private void addCategoryToUpdatedCategoriesList(Category categoryToAdd) {
                    boolean isExist = false;
                    for (Category c : updatedCategories) {
                        if (c.getId() == categoryToAdd.getId()) {
                            c.setName(categoryToAdd.getName());
                            c.setLimit(categoryToAdd.getLimit());
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        updatedCategories.add(category);
                    }
                }
            });
            modifyCategoryWindow.show();
        });

        existingCategoriesButtonPanel.getChildren().addAll(removeCategoryButton, modifyCategoryButton);
        existingCategoriesPane.getChildren().addAll(existingCategoriesList, existingCategoriesButtonPanel);

        dialogVbox.getChildren().add(existingCategoriesPane);

        HBox applyCancelPane = new HBox(20);
        applyCancelPane.setAlignment(Pos.CENTER);
        Button applyButton = new Button("Apply");
        applyButton.setPrefWidth(70);
        applyButton.setOnAction(event -> {
            System.out.println("----- ADDED CATEGORIES -----");
            for (Category addedCategory : addedCategories) {
                System.out.println("- c: " + addedCategory);
                processor.addCategory(addedCategory.getName(), addedCategory.getLimit());
            }
            System.out.println("----- REMOVED CATEGORIES -----");
            for (Category removedCategory : removedCategories) {
                System.out.println("- c: " + removedCategory);
                processor.removeCategory(removedCategory.getId());
            }
            System.out.println("----- UPDATED CATEGORIES -----");
            for (Category c : updatedCategories) {
                System.out.println("- c: " + c);
                processor.updateCategory(c);
            }
            System.out.println("----- CATEGORIES LIST -----");
            for (Category c : categories) {
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
