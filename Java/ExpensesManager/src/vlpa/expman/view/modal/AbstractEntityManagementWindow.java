package vlpa.expman.view.modal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.view.UIBuilder;

import java.util.*;

public abstract class AbstractEntityManagementWindow<T> extends AbstractBasicOperationWindow<T> {

    private List<T> entities;
    private ObservableList<String> entitiesData;

    private List<T> addedEntities = new LinkedList<>();
    private List<T> removedEntities = new LinkedList<>();
    private List<T> updatedEntities = new LinkedList<>();

    private ListView<String> existingEntitiesList;

    public AbstractEntityManagementWindow(UIBuilder builder, MainProcessor processor) {
        super(builder, processor, null);
    }

    @Override
    protected void init() {
        entities = loadEntities();
        entitiesData = loadEntitiesData();
        super.init();
        setMainWindowSize(550, 420);
    }

    protected HBox getExistingEntitiesPane() {
        HBox existingEntitiesPane = new HBox(5);
        existingEntitiesList = new ListView<>(getEntitiesData());
        existingEntitiesList.setPrefSize(400, 250);
        existingEntitiesPane.getChildren().addAll(existingEntitiesList, getExistingEntitiesButtonPanel());
        return existingEntitiesPane;
    }

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

    protected abstract EventHandler<ActionEvent> getAddAction();

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
                int selectedIndex = getExistingEntitiesList().getSelectionModel().getSelectedIndex();
                if (selectedIndex < 0) {
                    return;
                }
                String removedEntityName = getEntitiesData().remove(selectedIndex);
                boolean temporarilyAdded = removeFromAddedList(removedEntityName);
                if (!temporarilyAdded) {
                    deleteFromRemovedList(removedEntityName);
                }
            }

            private boolean removeFromAddedList(String entityName) {
                boolean temporarilyAdded = false;
                for (int i = 0; i < getAddedEntities().size(); i++) {
                    T addedEntity = getAddedEntities().get(i);
                    if (getListValueForEntity(addedEntity).equals(entityName)) {
                        temporarilyAdded = true;
                        getAddedEntities().remove(i);
                        break;
                    }
                }
                return temporarilyAdded;
            }

            private void deleteFromRemovedList(String removedEntityName) {
                for (Iterator<T> iterator = getEntities().iterator(); iterator.hasNext(); ) {
                    T e = iterator.next();
                    if (getListValueForEntity(e).equals(removedEntityName)) {
                        getRemovedEntities().add(e);
                        iterator.remove();
                        break;
                    }
                }
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

    protected abstract EventHandler<ActionEvent> getModifyAction();

    protected EventHandler getModifyActionInternal(int index, T entity, AbstractBasicOperationWindow<T> modifyEntityWindow) {
        return new EventHandler() {
            @Override
            public void handle(Event event) {
                if (modifyEntityWindow.isChanged()) {
                    T updatedEntity = modifyEntityWindow.getDataObject();
                    updateEntityParameters(updatedEntity, entity);
                    getEntitiesData().set(index, getListValueForEntity(updatedEntity));
                    addEntityToUpdatedList(entity);
                }
            }

            private void addEntityToUpdatedList(T entityToAdd) {
                boolean isExist = false;
                for (T e : getUpdatedEntities()) {
                    if (areEntitiesEqual(e, entityToAdd)) {
                        updateEntityParameters(entityToAdd, e);
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    getUpdatedEntities().add(entity);
                }
            }
        };
    }

    protected abstract String getWindowTitle();

    protected abstract List<T> loadEntities();

    protected abstract boolean areEntitiesEqual(T e1, T e2);

    protected abstract void updateEntityParameters(T fromEntity, T toEntity);

    protected ObservableList<String> loadEntitiesData() {
        ObservableList<String> entitiesData = FXCollections.observableArrayList();
        for (T entity : getEntities()) {
            entitiesData.add(getListValueForEntity(entity));
        }
        return entitiesData;
    }

    protected abstract String getListValueForEntity(T entity);

    public List<T> getEntities() {
        return entities;
    }

    public ObservableList<String> getEntitiesData() {
        return entitiesData;
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

    public ListView<String> getExistingEntitiesList() {
        return existingEntitiesList;
    }

    @Override
    public void fillFieldsWithData() {
        //DO NOTHING
    }

    @Override
    protected EventHandler<ActionEvent> getDefaultApplyActionHandler() {
        return event -> {
            System.out.println("----- ADDED ENTITIES -----");
            for (T addedEntity : getAddedEntities()) {
                System.out.println("- e: " + addedEntity);
                addEntity(addedEntity);
            }
            System.out.println("----- REMOVED ENTITIES -----");
            for (T removedEntity : getRemovedEntities()) {
                System.out.println("- e: " + removedEntity);
                removeEntity(removedEntity);
            }
            System.out.println("----- UPDATED ENTITIES -----");
            for (T c : getUpdatedEntities()) {
                System.out.println("- e: " + c);
                updateEntity(c);
            }
            getBuilder().updateView();
            close();
        };
    }

    protected abstract void addEntity(T addedEntity);

    protected abstract void removeEntity(T removedEntity);

    protected abstract void updateEntity(T c);
}
