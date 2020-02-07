package vlpa.expman.view.modal.categories;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.view.UIBuilder;
import vlpa.expman.view.modal.AbstractBasicOperationWindow;
import vlpa.expman.view.modal.AbstractEntityManagementWindow;
import vlpa.expman.view.modal.ModalWindowsHelper;
import vlpa.expman.view.modal.exception.EntityValidationException;

import java.util.Arrays;
import java.util.List;

public class CategoriesManagementWindow<T extends Category> extends AbstractEntityManagementWindow<T> {

    private TextField nameInput;
    private TextField limitInput;

    public CategoriesManagementWindow(UIBuilder builder, MainProcessor processor) {
        super(builder, processor);
    }

    @Override
    protected List<Node> getMainPaneComponents() {
        return Arrays.asList(
                new Text("New category"),
                getAddCategoryPane(),
                new Text(), new Separator(),
                new Text("Existing categories:"),
                getExistingEntitiesPane()
        );
    }

    private HBox getAddCategoryPane() {
        HBox addCategoryPane = new HBox(5);

        Label nameText = new Label("Name:");
        nameInput = new TextField();
        nameInput.setPrefWidth(240);

        Label limitText = new Label("Limit:");
        limitInput = new TextField();
        limitInput.setPrefWidth(80);

        Button addEntityButton = getAddButton();

        addCategoryPane.getChildren().addAll(nameText, nameInput, limitText, limitInput, addEntityButton);
        return addCategoryPane;
    }

    @Override
    protected TableColumn[] getTableColumns() {
        DoubleBinding smallColumnWidth = getExistingEntitiesTable().widthProperty().multiply(0.15);

        TableColumn idColumn = new TableColumn("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<T, String>("id"));
        idColumn.setSortType(TableColumn.SortType.ASCENDING);
        idColumn.prefWidthProperty().bind(smallColumnWidth);
        idColumn.setResizable(false);

        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<T, String>("name"));
        nameColumn.prefWidthProperty().bind(getExistingEntitiesTable().widthProperty().subtract(
                smallColumnWidth.multiply(2)).subtract(15));
        nameColumn.setResizable(false);

        TableColumn limitColumn = new TableColumn("Limit");
        limitColumn.setCellValueFactory(new PropertyValueFactory<T, Double>("limit"));
        limitColumn.prefWidthProperty().bind(smallColumnWidth);
        limitColumn.setResizable(false);

        return new TableColumn[]{idColumn, nameColumn, limitColumn};
    }

    @Override
    protected int getSortingColumnIndex() {
        return 0;
    }

    @Override
    protected String getWindowTitle() {
        return "Manage categories";
    }

    @Override
    protected String getAddButtonName() {
        return "Add category";
    }

    @Override
    protected String getRemoveButtonName() {
        return "Remove category";
    }

    @Override
    protected String getModifyButtonName() {
        return "Modify category";
    }

    @Override
    protected void validateData() {
        String name = nameInput.getText();
        String limitInputText = limitInput.getText();
        if (isEmpty(name) || isEmpty(limitInputText)) {
            throw new EntityValidationException("Category", "Category name and limit should not be empty!");
        }

        double limit;
        try {
            limit = Double.parseDouble(limitInputText);
        } catch (NumberFormatException e) {
            throw new EntityValidationException("Category", "Category limit should be a number!");
        }

        if (limit <= 0) {
            throw new EntityValidationException("Category", "Category limit should be greater than zero!");
        }
    }

    @Override
    protected T createNewEntity() {
        String name = nameInput.getText();
        double limit = Double.parseDouble(limitInput.getText());
        T newCategory = (T) new Category(0, name, limit);
        return newCategory;
    }

    @Override
    protected void clearInputFields() {
        nameInput.clear();
        limitInput.clear();
    }

    @Override
    protected AbstractBasicOperationWindow<T> getModifyActionWindow(T entity) {
        return ModalWindowsHelper.getModifyCategoryWindow(getBuilder(), entity);
    }

    @Override
    protected List<T> loadEntities() {
        return (List<T>) getProcessor().getAllCategories();
    }

    @Override
    protected void addEntity(T addedCategory) {
        getProcessor().addCategory(addedCategory);
    }

    @Override
    protected void removeEntity(Category removedCategory) {
        getProcessor().removeCategory(removedCategory.getId());
    }

    @Override
    protected void updateEntity(Category c) {
        getProcessor().updateCategory(c);
    }

}
