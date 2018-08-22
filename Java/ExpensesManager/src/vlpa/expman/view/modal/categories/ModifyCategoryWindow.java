package vlpa.expman.view.modal.categories;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import vlpa.expman.model.Category;
import vlpa.expman.view.UIBuilder;
import javafx.event.EventHandler;
import vlpa.expman.view.modal.AbstractBasicOperationWindow;

import java.util.Arrays;
import java.util.List;

public class ModifyCategoryWindow<T extends Category> extends AbstractBasicOperationWindow<T> {

    private boolean isChanged;

    private TextField categoryNameInput;
    private TextField categoryLimitInput;

    public ModifyCategoryWindow(UIBuilder builder, T dataObject) {
        super(builder, dataObject);
    }

    @Override
    public T getDataObject() {
        return super.getDataObject();
    }

    @Override
    public double getWidth() {
        return 290;
    }

    @Override
    public double getHeight() {
        return 100;
    }

    @Override
    protected List<Node> getMainPaneComponents() {
        Pane categoryNamePane = getCategoryNamePane();
        Pane categoryLimitPane = getCategoryLimitPane();
        return Arrays.asList(categoryNamePane, categoryLimitPane);
    }

    private Pane getCategoryNamePane() {
        HBox categoryNamePane = new HBox(5);
        Label categoryNameLabel = new Label("Name: ");
        categoryNameLabel.setPrefWidth(40);
        categoryNameInput = new TextField();
        categoryNameInput.setPrefWidth(225);
        categoryNamePane.getChildren().addAll(categoryNameLabel, categoryNameInput);
        return categoryNamePane;
    }

    private Pane getCategoryLimitPane() {
        HBox categoryLimitPane = new HBox(5);
        Label categoryLimitLabel = new Label("Limit: ");
        categoryLimitLabel.setPrefWidth(40);
        categoryLimitInput = new TextField();
        categoryLimitInput.setPrefWidth(225);
        categoryLimitPane.getChildren().addAll(categoryLimitLabel, categoryLimitInput);
        return categoryLimitPane;
    }

    public EventHandler<ActionEvent> getApplyActionHandler() {
        return event -> {
            performPreApplyActions();
            EventHandler<ActionEvent> handler = super.getApplyActionHandler();
            if (handler != null) {
                handler.handle(null);
            }
            close();
        };
    }

    private void performPreApplyActions() {
        System.out.println("Update category...");
        String updatedCategoryName = categoryNameInput.getText();
        double updatedCategoryLimit = Double.valueOf(categoryLimitInput.getText());
        if (!getDataObject().getName().equals(updatedCategoryName)) {
            isChanged = true;
            getDataObject().setName(updatedCategoryName);

        }
        if (getDataObject().getLimit() != updatedCategoryLimit) {
            isChanged = true;
            getDataObject().setLimit(updatedCategoryLimit);
        }
    }

    public boolean isChanged() {
        return isChanged;
    }

    @Override
    protected String getWindowTitle() {
        return "Modify category";
    }

    @Override
    public void fillFieldsWithData() {
        categoryNameInput.setText(getDataObject().getName());
        categoryLimitInput.setText(String.valueOf(getDataObject().getLimit()));
    }

}
