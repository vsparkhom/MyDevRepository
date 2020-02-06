package vlpa.expman.view.modal.pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.model.PatternPriority;
import vlpa.expman.model.PatternType;

import java.util.Arrays;
import java.util.List;

public class PatternDataWindow extends VBox {

    private MainProcessor processor;
    private List<Category> categories;
    private ObservableList<String> categoriesData;
    private ComboBox<String> categoriesComboBox;

    private TextField patternTextInput;
    private ComboBox<String> prioritiesComboBox;
    private ComboBox<String> patternTypesComboBox;
    private TextField amountInput = new TextField();

    public PatternDataWindow(MainProcessor processor) {
        this.processor = processor;
        initContent();
    }

    private void initContent() {
        setSpacing(5);
        Pane patternTextPane = getPatternTextPane();
        Pane patternPriorityAndTypePane = getPatternPriorityAndTypePane();
        getChildren().addAll(patternTextPane, patternPriorityAndTypePane);
    }

    protected MainProcessor getProcessor() {
        return processor;
    }

    protected ComboBox<String> getCategoriesComboBox() {
        return categoriesComboBox;
    }

    protected ComboBox<String> getPrioritiesComboBox() {
        return prioritiesComboBox;
    }

    public ComboBox<String> getPatternTypesComboBox() {
        return patternTypesComboBox;
    }

    protected List<Category> getCategories() {
        return categories;
    }

    protected ObservableList<String> getCategoriesData() {
        return categoriesData;
    }

    protected TextField getPatternTextInput() {
        return patternTextInput;
    }

    public TextField getAmountInput() {
        return amountInput;
    }

    protected Pane getPatternTextPane() {
        HBox patternTextPane = new HBox(5);
        Label patternTextLabel = new Label("Text: ");
        patternTextLabel.setPrefWidth(45);
        patternTextInput = new TextField();
        patternTextInput.setPrefWidth(250);
        Label patternCategoryText = new Label("Category:");
        patternCategoryText.setPrefWidth(55);
        initCategoriesComponents();
        patternTextPane.getChildren().addAll(patternTextLabel, patternTextInput, patternCategoryText, getCategoriesComboBox());
        return patternTextPane;
    }

    protected Pane getPatternPriorityAndTypePane() {
        HBox patternTypePane = new HBox(5);
        Label patternPriorityText = new Label("Priority:");
        patternPriorityText.setPrefWidth(45);
        initPriorityCollection();

        Label patternTypeText = new Label("Type:");
        ObservableList<String> patternTypes = FXCollections.observableArrayList(Arrays.asList(
                PatternType.REGULAR.getDisplayName(),
                PatternType.SKIP.getDisplayName(),
                PatternType.AMOUNT.getDisplayName()
        ));
        patternTypesComboBox = new ComboBox<>(patternTypes);
        patternTypesComboBox.setPrefWidth(100);
        patternTypesComboBox.setOnAction(e -> {
            String selectedItem = patternTypesComboBox.getSelectionModel().getSelectedItem();
            if (PatternType.AMOUNT == PatternType.getPatternTypeByDisplayName(selectedItem)) {
                getAmountInput().setDisable(false);
            } else {
                getAmountInput().setDisable(true);
            }
        });

        Label patternAmountText = new Label("Amount:");
        patternAmountText.setPrefWidth(55);
        amountInput = new TextField();
        amountInput.setPrefWidth(100);
        amountInput.setDisable(true);

        patternTypePane.getChildren().addAll(patternPriorityText, prioritiesComboBox, patternTypeText, patternTypesComboBox, patternAmountText, amountInput);
        return patternTypePane;
    }

    private void initPriorityCollection() {
        prioritiesComboBox = new ComboBox<>(FXCollections.observableArrayList(Arrays.asList(
                PatternPriority.LOW.name(),
                PatternPriority.MEDIUM.name(),
                PatternPriority.HIGH.name(),
                PatternPriority.CRITICAL.name()
        )));
        prioritiesComboBox.setPrefWidth(110);
    }

    private void initCategoriesComponents() {
        categories = getProcessor().getAllCategories();
        categoriesData = FXCollections.observableArrayList();
        for (Category category : categories) {
            categoriesData.add(category.getName());
        }
        categoriesComboBox = new ComboBox<>(categoriesData);
        categoriesComboBox.setPrefWidth(200);
    }

}
