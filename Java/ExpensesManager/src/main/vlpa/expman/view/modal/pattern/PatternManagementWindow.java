package vlpa.expman.view.modal.pattern;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
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
    public double getWidth() {
        return 700;
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
        PatternDataWindow patternDataWindow = getPatternDataWindow();
        validateTextFieldValue(patternDataWindow.getPatternTextInput(), "Pattern text should not be empty!");
        validateComboBoxValueIsSelected(patternDataWindow.getCategoriesComboBox(), "Please select category for pattern!");
        validateComboBoxValueIsSelected(patternDataWindow.getPrioritiesComboBox(), "Please select priority for pattern!");
        validateComboBoxValueIsSelected(patternDataWindow.getPatternTypesComboBox(), "Please select pattern type!");
    }

    private void validateTextFieldValue(TextField input, String message) {
        if (isEmpty(input.getText())) {
            throw new EntityValidationException("Pattern", message);
        }
    }

    private void validateComboBoxValueIsSelected(ComboBox<String> comboBox, String message) {
        int categorySelectedIndex = comboBox.getSelectionModel().getSelectedIndex();
        if (categorySelectedIndex < 0) {
            throw new EntityValidationException("Pattern", message);
        }
    }

    @Override
    protected TableColumn[] getTableColumns() {
        DoubleBinding smallColumnWidth = getExistingEntitiesTable().widthProperty().multiply(0.10);

        TableColumn idColumn = new TableColumn("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<T, String>("id"));
        idColumn.setSortType(TableColumn.SortType.ASCENDING);
        idColumn.prefWidthProperty().bind(smallColumnWidth);
        idColumn.setResizable(false);

        TableColumn patternColumn = new TableColumn("Pattern");
        patternColumn.setCellValueFactory(new PropertyValueFactory<T, String>("text"));
        patternColumn.prefWidthProperty().bind(getExistingEntitiesTable().widthProperty().subtract(
                smallColumnWidth.multiply(5)).subtract(15));
        patternColumn.setResizable(false);

        TableColumn categoryColumn = new TableColumn("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<T, Double>("category"));
        categoryColumn.prefWidthProperty().bind(smallColumnWidth);
        categoryColumn.setResizable(false);
        categoryColumn.setCellValueFactory((Callback<TableColumn.CellDataFeatures<T, String>, ObservableValue<String>>) pattern -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(pattern.getValue().getCategory().getName());
            return property;
        });

        TableColumn typeColumn = new TableColumn("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<T, Double>("type"));
        typeColumn.prefWidthProperty().bind(smallColumnWidth);
        typeColumn.setResizable(false);
        typeColumn.setCellValueFactory((Callback<TableColumn.CellDataFeatures<T, String>, ObservableValue<String>>) pattern -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(pattern.getValue().getType().getDisplayName());
            return property;
        });

        TableColumn priorityColumn = new TableColumn("Priority");
        priorityColumn.setCellValueFactory(new PropertyValueFactory<T, Double>("priority"));
        priorityColumn.prefWidthProperty().bind(smallColumnWidth);
        priorityColumn.setResizable(false);
        priorityColumn.setCellValueFactory((Callback<TableColumn.CellDataFeatures<T, String>, ObservableValue<String>>) pattern -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(pattern.getValue().getPriority().name());
            return property;
        });

        TableColumn amountColumn = new TableColumn("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<T, Double>("amount"));
        amountColumn.prefWidthProperty().bind(smallColumnWidth);
        amountColumn.setResizable(false);
        amountColumn.setCellValueFactory((Callback<TableColumn.CellDataFeatures<T, String>, ObservableValue<String>>) pattern -> {
            SimpleStringProperty property = new SimpleStringProperty();
            property.setValue(pattern.getValue().getAmount() + "");
            return property;
        });

        return new TableColumn[]{idColumn, patternColumn, categoryColumn, typeColumn, priorityColumn, amountColumn};
    }

    @Override
    protected int getSortingColumnIndex() {
        return 4;
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
