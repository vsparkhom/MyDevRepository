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

public class ModifyCategoryWindow {

    private UIBuilder builder;
    private Stage stage;
    private Category category;
    private boolean isChanged;
    private EventHandler handler;

    ModifyCategoryWindow(UIBuilder builder, Category category) {
        this.builder = builder;
        this.category = category;
        init();
    }

    private void init() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(builder.getPrimaryStage());
        stage.setTitle("Update category");

        VBox verticalPane = new VBox(8);
        verticalPane.setPadding(new Insets(10));

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
            stage.close();
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(70);
        cancelButton.setOnAction(event -> stage.close());

        applyCancelPane.getChildren().addAll(applyButton, cancelButton);

        verticalPane.getChildren().addAll(categoryNamePane, categoryLimitPane, new Separator(), applyCancelPane);

        Scene dialogScene = new Scene(verticalPane, 280, 120);
        stage.setScene(dialogScene);
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

    public void show() {
        stage.show();
    }
}
