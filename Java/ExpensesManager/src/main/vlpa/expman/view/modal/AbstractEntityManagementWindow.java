package vlpa.expman.view.modal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.view.UIBuilder;
import vlpa.expman.view.modal.exception.EntityValidationException;

import java.util.*;

public abstract class AbstractEntityManagementWindow<T> extends AbstractBasicOperationWindow<T> {

    private final Logger LOGGER = LoggerFactory.getLogger(AbstractEntityManagementWindow.class);

    private List<T> addedEntities = new LinkedList<>();
    private List<T> removedEntities = new LinkedList<>();
    private List<T> updatedEntities = new LinkedList<>();

    private ObservableList<T> existingEntitiesList;
    private TableView<T> existingEntitiesTable;

    public AbstractEntityManagementWindow(UIBuilder builder, MainProcessor processor) {
        super(builder, processor, null);
    }

    @Override
    protected void init() {
        existingEntitiesList = loadEntitiesInternal();
        super.init();
    }

    public ObservableList<T> getExistingEntitiesList() {
        return existingEntitiesList;
    }

    @Override
    public double getWidth() {
        return 535;
    }

    @Override
    public double getHeight() {
        return 400;
    }

    protected HBox getExistingEntitiesPane() {
        HBox existingEntitiesPane = new HBox(5);
        existingEntitiesTable = new TableView<>();
        existingEntitiesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        existingEntitiesTable.setEditable(true);
        existingEntitiesTable.setPrefSize(getWidth() - 135, 250);
        existingEntitiesTable.setItems(getExistingEntitiesList());
        TableColumn[] tableColumns = getTableColumns();
        if (tableColumns !=null && tableColumns.length != 0) {
            existingEntitiesTable.getColumns().addAll(tableColumns);
            int sortingColumnIndex = getSortingColumnIndex();
            if (sortingColumnIndex >= 0 && sortingColumnIndex < tableColumns.length) {
                existingEntitiesTable.getSortOrder().add(tableColumns[sortingColumnIndex]);
            }
        }
        existingEntitiesPane.getChildren().addAll(existingEntitiesTable, getExistingEntitiesButtonPanel());
        return existingEntitiesPane;
    }

    protected abstract TableColumn[] getTableColumns();

    protected abstract int getSortingColumnIndex();

    protected Pane getExistingEntitiesButtonPanel() {
        VBox existingEntitiesButtonPanel = new VBox(5);
        existingEntitiesButtonPanel.getChildren().addAll(getRemoveButton(), getModifyButton());
        return existingEntitiesButtonPanel;
    }

    protected Button getAddButton() {
        Button addEntityButton = new Button(getAddButtonName());
        addEntityButton.setPrefWidth(110);
        addEntityButton.setOnAction(getAddAction());
        return addEntityButton;
    }

    protected abstract String getAddButtonName();

    protected EventHandler<ActionEvent> getAddAction() {
        return event -> {
            try {
                validateData();
                T newEntity = createNewEntity();
                getExistingEntitiesList().add(newEntity);
                getAddedEntities().add(newEntity);
                clearInputFields();
            } catch (EntityValidationException eve) {
                ModalWindowsHelper.getErrorDialog(eve.getEntityName() + " can't be created", eve.getMessage()).show();
            }
        };
    }

    protected abstract void validateData();

    protected abstract T createNewEntity();

    protected abstract void clearInputFields();

    protected Button getRemoveButton() {
        Button removeEntityButton = new Button(getRemoveButtonName());
        removeEntityButton.setPrefWidth(110);
        removeEntityButton.setOnAction(getRemoveAction());
        return removeEntityButton;
    }

    protected abstract String getRemoveButtonName();

    protected EventHandler<ActionEvent> getRemoveAction() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                T expenseToRemove = getExistingEntitiesTable().getSelectionModel().getSelectedItem();
                if (expenseToRemove!= null) {
                    getExistingEntitiesTable().getItems().removeAll(expenseToRemove);
                    if (!removeFromAddedList(expenseToRemove)) {
                        getRemovedEntities().add(expenseToRemove);
                    }
                }
            }

            private boolean removeFromAddedList(T entityForRemoval) {
                Iterator<T> iterator = getAddedEntities().iterator();
                for (; iterator.hasNext();) {
                    T e = iterator.next();
                    if (e.equals(entityForRemoval)) {
                        iterator.remove();
                        return true;
                    }
                }
                return false;
            }
        };
    }

    protected abstract String getModifyButtonName();

    protected Button getModifyButton() {
        Button modifyEntityButton = new Button(getModifyButtonName());
        modifyEntityButton.setPrefWidth(110);

        modifyEntityButton.setOnAction(getModifyAction());
        return modifyEntityButton;
    }

    protected EventHandler<ActionEvent> getModifyAction() {
        return event -> {
            TableView.TableViewSelectionModel<T> selectionModel = getExistingEntitiesTable().getSelectionModel();
            int selectedIndex = selectionModel.getSelectedIndex();
            T selectedEntity = selectionModel.getSelectedItem();
            if (selectedEntity != null) {
                AbstractBasicOperationWindow<T> modifyActionWindow = getModifyActionWindow(selectedEntity);
                modifyActionWindow.setApplyActionHandler((EventHandler) e -> {
                    if (modifyActionWindow.isChanged()) {
                        getExistingEntitiesTable().getItems().set(selectedIndex, selectedEntity);
                        getUpdatedEntities().add(selectedEntity);
                    }
                });
                modifyActionWindow.show();
            }
        };
    }

    protected abstract AbstractBasicOperationWindow<T> getModifyActionWindow(T entity);

    protected abstract String getWindowTitle();

    protected abstract List<T> loadEntities();

    private ObservableList<T> loadEntitiesInternal() {
        List<T> entities = loadEntities();
        return FXCollections.observableArrayList(entities);
    }

    public List<T> getAddedEntities() {
        return addedEntities;
    }

    public List<T> getRemovedEntities() {
        return removedEntities;
    }

    public List<T> getUpdatedEntities() {
        return updatedEntities;
    }

    public TableView<T> getExistingEntitiesTable() {
        return existingEntitiesTable;
    }

    @Override
    public void fillFieldsWithData() {
        //DO NOTHING
    }

    @Override
    protected EventHandler<ActionEvent> getDefaultApplyActionHandler() {
        return event -> {
            LOGGER.debug("----- ADDED ENTITIES -----");
            for (T addedEntity : getAddedEntities()) {
                LOGGER.debug("- e: {}", addedEntity);
                addEntity(addedEntity);
            }
            LOGGER.debug("----- REMOVED ENTITIES -----");
            for (T removedEntity : getRemovedEntities()) {
                LOGGER.debug("- e: {}", removedEntity);
                removeEntity(removedEntity);
            }
            LOGGER.debug("----- UPDATED ENTITIES -----");
            for (T e : getUpdatedEntities()) {
                LOGGER.debug("- e: {}", e);
                updateEntity(e);
            }
            getBuilder().updateView();
            close();
        };
    }

    protected abstract void addEntity(T addedEntity);

    protected abstract void removeEntity(T removedEntity);

    protected abstract void updateEntity(T c);

    protected boolean isEmpty(String s) {
        return s == null || "".equals(s);
    }
}
