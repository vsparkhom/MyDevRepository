package vlpa.expman.view.modal.categories;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.view.UIBuilder;
import vlpa.expman.view.modal.AbstractEntityManagementWindow;
import vlpa.expman.view.modal.ModalWindowsHelper;

import java.util.Arrays;
import java.util.List;

public class CategoriesManagementWindow<T extends Category> extends AbstractEntityManagementWindow<T> {

    private TextField nameInput;
    private TextField limitInput;

    public CategoriesManagementWindow(UIBuilder builder, MainProcessor processor) {
        super(builder, processor);
    }

    protected List<T> loadEntities() {
        return (List<T>) getProcessor().getAllCategories();
    }

    protected String getListValueForEntity(T entity) {
        return entity.getName();
    }

    protected List<Node> getMainPaneComponents() {
        return Arrays.asList(
                new Text("New category"),
                getControlPane(),
                new Text(), new Separator(),
                new Text("Existing categories:"),
                getExistingEntitiesPane()
        );
    }

    private HBox getControlPane() {
        HBox addControlPane = new HBox(5);

        Label nameText = new Label("Name:");
        nameInput = new TextField();
        nameInput.setPrefWidth(240);

        Label limitText = new Label("Limit:");
        limitInput = new TextField();
        limitInput.setPrefWidth(70);

        Button addEntityButton = getAddButton();

        addControlPane.getChildren().addAll(nameText, nameInput, limitText, limitInput, addEntityButton);
        return addControlPane;
    }

    protected String getAddButtonName() {
        return "Add category";
    }

    protected EventHandler<ActionEvent> getAddAction() {
        return event -> {
            //TODO: validate input data
            String name = nameInput.getText();
            double limit = Double.parseDouble(limitInput.getText());
            T newCategory = (T) new Category(0, name, limit);
            getEntities().add(newCategory);
            getEntitiesData().add(name);
            getAddedEntities().add(newCategory);
            nameInput.clear();
            limitInput.clear();
        };
    }

    protected String getRemoveButtonName() {
        return "Remove category";
    }

    protected String getModifyButtonName() {
        return "Modify category";
    }

    protected EventHandler<ActionEvent> getModifyAction() {
        return event -> {
            int index = getExistingEntitiesList().getSelectionModel().getSelectedIndex();
            T category = getEntities().get(index);
            ModifyCategoryWindow<T> modifyCategoryWindow = ModalWindowsHelper.getModifyCategoryWindow(getBuilder(), category);
            modifyCategoryWindow.setApplyActionHandler(getModifyActionInternal(index, category, modifyCategoryWindow));
            modifyCategoryWindow.show();
        };
    }

    @Override
    protected String getWindowTitle() {
        return "Manage categories";
    }

    @Override
    protected boolean areEntitiesEqual(T category1, T category2) {
        return category1.getId() == category2.getId();
    }

    @Override
    protected void updateEntityParameters(T fromCategory, T toCategory) {
        toCategory.setName(fromCategory.getName());
        toCategory.setLimit(fromCategory.getLimit());
    }

    protected void addEntity(T addedCategory) {
        getProcessor().addCategory(addedCategory.getName(), addedCategory.getLimit());
    }

    protected void removeEntity(Category removedCategory) {
        getProcessor().removeCategory(removedCategory.getId());
    }

    protected void updateEntity(Category c) {
        getProcessor().updateCategory(c);
    }


}
