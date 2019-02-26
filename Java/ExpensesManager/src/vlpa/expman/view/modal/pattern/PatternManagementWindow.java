package vlpa.expman.view.modal.pattern;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.ImportPattern;
import vlpa.expman.view.UIBuilder;
import vlpa.expman.view.modal.AbstractBasicOperationWindow;
import vlpa.expman.view.modal.AbstractEntityManagementWindow;
import vlpa.expman.view.modal.ModalWindowsHelper;
import vlpa.expman.view.modal.exception.EntityValidationException;

import java.util.Arrays;
import java.util.List;

public class PatternManagementWindow<T extends ImportPattern> extends AbstractEntityManagementWindow<T> {

    private PatternDataWindow patternDataWindow;

    public PatternManagementWindow(UIBuilder builder, MainProcessor processor) {
        super(builder, processor);
    }

    public PatternDataWindow getPatternDataWindow() {
        if (patternDataWindow == null) {
            patternDataWindow = new PatternDataWindow(getProcessor());
        }
        return patternDataWindow;
    }

    @Override
    public double getHeight() {
        return 430;
    }

    @Override
    protected List<Node> getMainPaneComponents() {
        HBox addPatternPane = new HBox(5);
        addPatternPane.getChildren().addAll(getPatternDataWindow(), getAddButton());
        return Arrays.asList(
                new Text("New pattern"),
                addPatternPane,
                new Text(), new Separator(),
                new Text("Existing patterns:"),
                getExistingEntitiesPane()
        );
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
        String patternText = getPatternDataWindow().getPatternTextInput().getText();
        if (isEmpty(patternText)) {
            throw new EntityValidationException("Pattern", "Pattern text should not be empty!");
        }
        int categorySelectedIndex = getPatternDataWindow().getCategoriesComboBox().getSelectionModel().getSelectedIndex();
        if (categorySelectedIndex < 0) {
            throw new EntityValidationException("Pattern", "Please select category for pattern!");
        }
        int prioritySelectedIndex = getPatternDataWindow().getPrioritiesComboBox().getSelectionModel().getSelectedIndex();
        if (prioritySelectedIndex < 0) {
            throw new EntityValidationException("Pattern", "Please select priority for pattern!");
        }
    }

    @Override
    protected T createNewEntity() {
        return (T) PatternCreator.create(getPatternDataWindow());
    }

    @Override
    protected void clearInputFields() {
        getPatternDataWindow().getPatternTextInput().clear();
        getPatternDataWindow().getCategoriesComboBox().getSelectionModel().clearSelection();
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
        getProcessor().addPattern(addedEntity);
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
