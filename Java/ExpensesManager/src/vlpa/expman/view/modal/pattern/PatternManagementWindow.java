package vlpa.expman.view.modal.pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.model.ImportPattern;
import vlpa.expman.view.UIBuilder;
import vlpa.expman.view.modal.AbstractBasicOperationWindow;
import vlpa.expman.view.modal.AbstractEntityManagementWindow;
import vlpa.expman.view.modal.ModalWindowsHelper;
import vlpa.expman.view.modal.exception.EntityValidationException;

import java.util.Arrays;
import java.util.List;

public class PatternManagementWindow<T extends ImportPattern> extends AbstractEntityManagementWindow<T> {

    private ComboBox<String> categoriesComboBox;
    private List<Category> categories;
    private ObservableList<String> categoriesData;

    private TextField patternTextInput;
    private ToggleGroup patternTypeRadioButtonGroup;

    public PatternManagementWindow(UIBuilder builder, MainProcessor processor) {
        super(builder, processor);
    }

    @Override
    public double getHeight() {
        return 430;
    }

    @Override
    protected List<Node> getMainPaneComponents() {
        return Arrays.asList(
                new Text("New pattern"),
                getAddPatternPane(),
                new Text(), new Separator(),
                new Text("Existing patterns:"),
                getExistingEntitiesPane()
        );
    }

    private Pane getAddPatternPane() {
        VBox addPatternMainPane = new VBox(5);
        addPatternMainPane.getChildren().addAll(getAddPatternTextPane(), getAddPatternCategoryPane(), getAddPatternTypePane());
        return addPatternMainPane;
    }

    private Pane getAddPatternCategoryPane() {
        HBox addPatternCategoryPane = new HBox(5);
        categories = getProcessor().getAllCategories();
        categoriesData = FXCollections.observableArrayList();
        for (Category category : categories) {
            categoriesData.add(category.getName());
        }
        Label patternCategoryText = new Label("Category:");
        patternCategoryText.setPrefWidth(55);
        categoriesComboBox = new ComboBox<>(categoriesData);
        categoriesComboBox.setPrefWidth(340);

        addPatternCategoryPane.getChildren().addAll(patternCategoryText, categoriesComboBox);
        return addPatternCategoryPane;
    }

    private Pane getAddPatternTextPane() {
        HBox addPatternTextPane = new HBox(5);
        Label patternTextLabel = new Label("Text:");
        patternTextLabel.setPrefWidth(55);
        patternTextInput = new TextField();
        patternTextInput.setPrefWidth(340);
        Button addPatternButton = getAddButton();
        addPatternTextPane.getChildren().addAll(patternTextLabel, patternTextInput, addPatternButton);
        return addPatternTextPane;
    }

    private Pane getAddPatternTypePane() {
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

    @Override
    protected String getWindowTitle() {
        return "Manage patterns";
    }

    @Override
    protected String getAddButtonName() {
        return "Add pattern";
    }

    @Override
    protected String getRemoveButtonName() {
        return "Remove pattern";
    }

    @Override
    protected String getModifyButtonName() {
        return "Modify pattern";
    }

    @Override
    protected void validateData() {
        String patternText = patternTextInput.getText();
        if (isEmpty(patternText)) {
            throw new EntityValidationException("Pattern", "Pattern text should not be empty!");
        }
        int categorySelectedIndex = categoriesComboBox.getSelectionModel().getSelectedIndex();
        if (categorySelectedIndex < 0) {
            throw new EntityValidationException("Pattern", "Please select category for pattern!");
        }
    }

    @Override
    protected T createNewEntity() {
        String patternText = patternTextInput.getText();
        int categorySelectedIndex = categoriesComboBox.getSelectionModel().getSelectedIndex();
        Category category = categories.get(categorySelectedIndex);
        PatternType type = PatternType.REGULAR;
        String selectedType = ((RadioButton) patternTypeRadioButtonGroup.getSelectedToggle()).getText();
        if (PatternType.SKIP.getDisplayName().equals(selectedType)) {
            type = PatternType.SKIP;
        }
        return (T) new ImportPattern(0, patternText, category, type);
    }

    @Override
    protected void clearInputFields() {
        patternTextInput.clear();
        categoriesComboBox.getSelectionModel().clearSelection();
    }

    @Override
    protected AbstractBasicOperationWindow<T> getModifyActionWindow(T entity) {
        return ModalWindowsHelper.getModifyPatternWindow(getBuilder(), getProcessor(), entity);
    }

    @Override
    protected List<T> loadEntities() {
        return (List<T>) getProcessor().getAllPatterns();
    }

    @Override
    protected boolean areEntitiesEqual(T pattern1, T pattern2) {
        return pattern1.getId() == pattern2.getId();
    }

    @Override
    protected void updateEntityParameters(T fromEntity, T toEntity) {
        toEntity.setText(fromEntity.getText());
        toEntity.setCategory(fromEntity.getCategory());
    }

    @Override
    protected String getListValueForEntity(T entity) {
        return entity.getText();
    }

    @Override
    protected void addEntity(T addedEntity) {
        getProcessor().addPattern(addedEntity.getText(), addedEntity.getCategory(), addedEntity.getType());
    }

    @Override
    protected void removeEntity(T removedEntity) {
        getProcessor().removePattern(removedEntity.getId());
    }

    @Override
    protected void updateEntity(T p) {
        getProcessor().updatePattern(p);
    }

}
