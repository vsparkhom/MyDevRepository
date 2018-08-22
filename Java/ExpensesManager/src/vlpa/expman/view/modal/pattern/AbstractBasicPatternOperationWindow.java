package vlpa.expman.view.modal.pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.view.UIBuilder;
import vlpa.expman.view.modal.AbstractBasicOperationWindow;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractBasicPatternOperationWindow<T> extends AbstractBasicOperationWindow<T> {

    private List<Category> categories;
    private ObservableList<String> categoriesData;
    private ComboBox<String> categoriesComboBox;

    private TextField patternTextInput;
    private ToggleGroup patternTypeRadioButtonGroup;

    AbstractBasicPatternOperationWindow(UIBuilder builder, MainProcessor processor, T dataObject) {
        super(builder, processor, dataObject);
    }

    protected ComboBox<String> getCategoriesComboBox() {
        return categoriesComboBox;
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

    @Override
    protected List<Node> getMainPaneComponents() {
        Pane patternTextPane = getPatternTextPane();
        Pane patternCategoryPane = getPatternCategoryPane();
        Pane patternTypePane = getPatternTypePane();
        return Arrays.asList(patternTextPane, patternCategoryPane, patternTypePane);
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

        patternTypePane.getChildren().addAll(patternTypeText, rb1, rb2);
        return patternTypePane;
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

    protected void selectProperCategory() {
        //DO NOTHING
    }

    @Override
    public void fillFieldsWithData() {
        selectProperCategory();
    }

}
