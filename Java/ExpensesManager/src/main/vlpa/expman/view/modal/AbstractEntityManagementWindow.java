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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.view.UIBuilder;
import vlpa.expman.view.modal.exception.EntityValidationException;

import java.util.*;

public abstract class AbstractEntityManagementWindow<T> extends AbstractBasicOperationWindow<T> {

    private final Logger LOGGER = LoggerFactory.getLogger(AbstractEntityManagementWindow.class);

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

    protected EventHandler<ActionEvent> getAddAction() {
        return event -> {
            try {
                validateData();
                T newEntity = createNewEntity();
                getEntities().add(newEntity);
                getEntitiesData().add(getListValueForEntity(newEntity));
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

    protected EventHandler<ActionEvent> getModifyAction() {
        return event -> {
            int index = getExistingEntitiesList().getSelectionModel().getSelectedIndex();
            if (index < 0) {
                return;
            }
            T entity = getEntities().get(index);
            AbstractBasicOperationWindow<T> modifyActionWindow = getModifyActionWindow(entity);
            modifyActionWindow.setApplyActionHandler(getModifyActionInternal(index, entity, modifyActionWindow));
            modifyActionWindow.show();
        };
    }

    protected abstract AbstractBasicOperationWindow<T> getModifyActionWindow(T entity);

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
