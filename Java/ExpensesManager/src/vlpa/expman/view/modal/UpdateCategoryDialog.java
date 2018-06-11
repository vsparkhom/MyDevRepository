package vlpa.expman.view.modal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vlpa.expman.model.Category;
import vlpa.expman.view.UIBuilder;
import javafx.event.EventHandler;

public class UpdateCategoryDialog {

    private UIBuilder builder;
    private Stage dialog;
    private Category category;
    private boolean isChanged;
    private EventHandler handler;

    public UpdateCategoryDialog(UIBuilder builder, Category category) {
        this.builder = builder;
        this.category = category;
        init();
    }

    private void init() {
        dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(builder.getPrimaryStage());
        dialog.setTitle("Update category");

        VBox dialogVbox = new VBox(8);
        dialogVbox.setPadding(new Insets(10));

        HBox categoryNamePane = new HBox(8);
        Label categoryNameLabel = new Label("Name: ");
        categoryNameLabel.setPrefWidth(40);
        TextField categoryNameTF = new TextField(category.getName());
        categoryNameTF.setPrefWidth(200);
        categoryNamePane.getChildren().addAll(categoryNameLabel, categoryNameTF);

        HBox categoryLimitPane = new HBox(8);
        Label categoryLimitLabel = new Label("Limit: ");
        categoryLimitLabel.setPrefWidth(40);
        TextField categoryLimitTF = new TextField(String.valueOf(category.getLimit()));
        categoryLimitTF.setPrefWidth(200);
        categoryLimitPane.getChildren().addAll(categoryLimitLabel, categoryLimitTF);

        HBox applyCancelPane = new HBox(20);
        applyCancelPane.setAlignment(Pos.CENTER);
        Button applyButton = new Button("Apply");
        applyButton.setPrefWidth(70);
        applyButton.setOnAction(event -> {
            System.out.println("Update category...");
            String updatedCategoryName = categoryNameTF.getText();
            double updatedCategoryLimit = Double.valueOf(categoryLimitTF.getText());
            if (!category.getName().equals(updatedCategoryName)) {
                isChanged = true;
                category.setName(updatedCategoryName);

            }
            if (category.getLimit() != updatedCategoryLimit) {
                isChanged = true;
                category.setLimit(updatedCategoryLimit);
            }
            if (handler != null) {
                handler.handle(null);
            }
            dialog.close();
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(70);
        cancelButton.setOnAction(event -> dialog.close());

        applyCancelPane.getChildren().addAll(applyButton, cancelButton);

        dialogVbox.getChildren().addAll(categoryNamePane, categoryLimitPane, new Separator(), applyCancelPane);

        Scene dialogScene = new Scene(dialogVbox, 280, 120);
        dialog.setScene(dialogScene);
    }

    public Stage getStage() {
        return dialog;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setApplyHandler(EventHandler handler) {
        this.handler = handler;
    }
}
