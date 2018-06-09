package vlpa.expman.view;

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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ModalWindowsHelper {

    public static void initCategoriesManagementWindow(Window primaryStage, MainProcessor processor) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        dialog.setTitle("Manage categories");

        Collection<Category> categories = processor.getAllCategories();
        ObservableList<String> categoriesData = FXCollections.observableArrayList();
        for(Category category : categories) {
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

        List<Category> addedCategories = new LinkedList<>();

        Button addCategoryButton = new Button("Add category");
        addCategoryButton.setPrefWidth(110);
        addCategoryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO: validate input data
                String name = nameInput.getText();
                double limit = Double.parseDouble(limitInput.getText());
                Category newCategory = new Category(0, name, limit);
                addedCategories.add(newCategory);
                categoriesData.add(name);
            }
        });

        addControlPane.getChildren().addAll(nameText, nameInput, limitText, limitInput, addCategoryButton);

        dialogVbox.getChildren().add(addControlPane);
        dialogVbox.getChildren().addAll(new Text(), new Separator());
        dialogVbox.getChildren().add(new Text("Existing categories:"));

        HBox existingCategoriesPane = new HBox(8);

        ListView<String> existingCategoriesList = new ListView<>();
        existingCategoriesList.setItems(categoriesData);
        existingCategoriesList.setPrefSize(400, 250);

        Collection<Category> removedCategories = new LinkedList<>();

        Button removeCategoryButton = new Button("Remove category");
        removeCategoryButton.setPrefWidth(110);
        removeCategoryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int selectedIndex = existingCategoriesList.getSelectionModel().getSelectedIndex();
                String removedCategoryName = categoriesData.remove(selectedIndex);
                boolean temporarilyAdded = removeFromAddedCategoriesList(removedCategoryName);
                deleteFromRemovedCategoriesList(removedCategoryName, temporarilyAdded);
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

            private void deleteFromRemovedCategoriesList(String removedCategoryName, boolean temporarilyAdded) {
                if (!temporarilyAdded) {
                    for (Category category : categories) {
                        if (category.getName().equals(removedCategoryName)) {
                            removedCategories.add(category);
                            break;
                        }
                    }
                }
            }
        });
        existingCategoriesPane.getChildren().addAll(existingCategoriesList, removeCategoryButton);

        dialogVbox.getChildren().add(existingCategoriesPane);

        HBox applyCancelPane = new HBox(20);
        applyCancelPane.setAlignment(Pos.CENTER);
        Button applyButton = new Button("Apply");
        applyButton.setPrefWidth(70);
        applyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO; save changes to database
                System.out.println("----- ADDED CATEGORIES -----");
                for (Category addedCategory : addedCategories) {
                    System.out.println("- c: " + addedCategory);
                }
                System.out.println("----- REMOVED CATEGORIES -----");
                for (Category removedCategory : removedCategories) {
                    System.out.println("- c: " + removedCategory);
                }

            }
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(70);
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });

        applyCancelPane.getChildren().addAll(applyButton, cancelButton);
        dialogVbox.getChildren().add(applyCancelPane);

        Scene dialogScene = new Scene(dialogVbox, 550, 420);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
