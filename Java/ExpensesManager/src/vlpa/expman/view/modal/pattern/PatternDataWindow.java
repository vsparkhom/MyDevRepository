package vlpa.expman.view.modal.pattern;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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
    private ToggleGroup patternTypeRadioButtonGroup;
    private ComboBox<String> prioritiesComboBox;
    private TextField amountInput = new TextField();

    public PatternDataWindow(MainProcessor processor) {
        this.processor = processor;
        initContent();
    }

    private void initContent() {
        setSpacing(5);
        Pane patternTextPane = getPatternTextPane();
        Pane patternCategoryPane = getPatternCategoryPane();
        Pane patternTypePane = getPatternTypePane();
        Pane patternPriorityPane = getPatternPriorityAndAmountPane();
        getChildren().addAll(patternTextPane, patternCategoryPane, patternTypePane, patternPriorityPane);
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

    protected List<Category> getCategories() {
        return categories;
    }

    protected ObservableList<String> getCategoriesData() {
        return categoriesData;
    }

    protected TextField getPatternTextInput() {
        return patternTextInput;
    }

    public ToggleGroup getPatternTypeRadioButtonGroup() {
        return patternTypeRadioButtonGroup;
    }

    public TextField getAmountInput() {
        return amountInput;
    }

    protected Pane getPatternTextPane() {
        HBox patternTextPane = new HBox(5);
        Label patternTextLabel = new Label("Text: ");
        patternTextLabel.setPrefWidth(55);
        patternTextInput = new TextField();
        patternTextInput.setPrefWidth(340);
        patternTextPane.getChildren().addAll(patternTextLabel, patternTextInput);
        return patternTextPane;
    }

    protected Pane getPatternCategoryPane() {
        HBox patternCategoryPane = new HBox(5);
        Label patternCategoryText = new Label("Category:");
        patternCategoryText.setPrefWidth(55);
        initCategoriesComponents();
        patternCategoryPane.getChildren().addAll(patternCategoryText, getCategoriesComboBox());
        return patternCategoryPane;
    }

    protected Pane getPatternTypePane() {
        HBox patternTypePane = new HBox(5);
        Label patternTypeText = new Label("Type:");
        patternTypeText.setPrefWidth(55);

        patternTypeRadioButtonGroup = new ToggleGroup();

        RadioButton rb1 = new RadioButton(PatternType.REGULAR.getDisplayName());
        rb1.setToggleGroup(patternTypeRadioButtonGroup);
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton(PatternType.SKIP.getDisplayName());
        rb2.setToggleGroup(patternTypeRadioButtonGroup);

        RadioButton rb3 = new RadioButton(PatternType.AMOUNT.getDisplayName());
        rb3.setToggleGroup(patternTypeRadioButtonGroup);

        patternTypeRadioButtonGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle oldToggle, Toggle newToggle) {
                if (newToggle != null) {
                    RadioButton rb = (RadioButton) newToggle;
                    if (PatternType.AMOUNT == PatternType.getPatternTypeByDisplayName(rb.getText())) {
                        getAmountInput().setDisable(false);
                    } else {
                        getAmountInput().setDisable(true);
                    }
                }
            }
        });

        patternTypePane.getChildren().addAll(patternTypeText, rb1, rb2, rb3);
        return patternTypePane;
    }

    protected Pane getPatternPriorityAndAmountPane() {
        HBox patternPriorityPane = new HBox(5);
        Label patternPriorityText = new Label("Priority:");
        patternPriorityText.setPrefWidth(55);

        initPriorityCollection();

        prioritiesComboBox.setPrefWidth(110);

        Label patternAmountText = new Label("Amount:");
        patternAmountText.setPrefWidth(75);
        patternAmountText.setPadding(new Insets(0, 0, 0, 22));
        amountInput = new TextField();
        amountInput.setPrefWidth(100);
        amountInput.setDisable(true);

        patternPriorityPane.getChildren().addAll(patternPriorityText, prioritiesComboBox, patternAmountText, amountInput);
        return patternPriorityPane;
    }

    private void initPriorityCollection() {
        prioritiesComboBox = new ComboBox<>(FXCollections.observableArrayList(Arrays.asList(
                PatternPriority.LOW.name(),
                PatternPriority.MEDIUM.name(),
                PatternPriority.HIGH.name(),
                PatternPriority.CRITICAL.name()
        )));
    }

    private void initCategoriesComponents() {
        categories = getProcessor().getAllCategories();
        categoriesData = FXCollections.observableArrayList();
        for (Category category : categories) {
            categoriesData.add(category.getName());
        }
        categoriesComboBox = new ComboBox<>(categoriesData);
        categoriesComboBox.setPrefWidth(340);
    }

}
