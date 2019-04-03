package vlpa.expman.view.modal.pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.ImportPattern;
import vlpa.expman.model.PatternType;
import vlpa.expman.view.UIBuilder;

import java.util.List;

public class ModifyPatternWindow<T extends ImportPattern> extends AbstractBasicPatternOperationWindow<T> {

    public ModifyPatternWindow(UIBuilder builder, MainProcessor processor, T pattern) {
        super(builder, processor, pattern);
    }

    @Override
    protected String getWindowTitle() {
        return "Modify pattern";
    }

    @Override
    protected EventHandler<ActionEvent> getDefaultApplyActionHandler() {
        return event -> {
            ImportPattern updatedPattern = PatternCreator.create(getPatternDataWindow());

            if (!getDataObject().getText().equals(updatedPattern.getText())) {
                setChanged(true);
                getDataObject().setText(updatedPattern.getText());
            }
            if (!getDataObject().getCategory().equals(updatedPattern.getCategory())) {
                setChanged(true);
                getDataObject().setCategory(updatedPattern.getCategory());
            }
            if (!getDataObject().getType().equals(updatedPattern.getType())) {
                setChanged(true);
                getDataObject().setType(updatedPattern.getType());
            }
            if (!getDataObject().getPriority().equals(updatedPattern.getPriority())) {
                setChanged(true);
                getDataObject().setPriority(updatedPattern.getPriority());
            }
            if (getDataObject().getAmount() != updatedPattern.getAmount()) {
                setChanged(true);
                getDataObject().setAmount(getDataObject().getAmount());
            }

            EventHandler<ActionEvent> handler = getApplyActionHandler();
            if (handler != null) {
                handler.handle(null);
            }
            close();
        };
    }

    @Override
    public void selectProperCategory() {
        int categoryIndex = getIndex(getPatternDataWindow().getCategoriesComboBox().getItems(), getDataObject().getCategory().getName());
        if (categoryIndex >= 0) {
            getPatternDataWindow().getCategoriesComboBox().getSelectionModel().select(categoryIndex);
        }
    }

    @Override
    public void selectProperPriority() {
        int priorityIndex = getIndex(getPatternDataWindow().getPrioritiesComboBox().getItems(), getDataObject().getPriority().name());
        if (priorityIndex >= 0) {
            getPatternDataWindow().getPrioritiesComboBox().getSelectionModel().select(priorityIndex);
        }
    }

    private int getIndex(List<String> items, String objectToFind) {
        for (int i=0; i<items.size(); i++) {
            String current = items.get(i);
            if (current.equals(objectToFind)) {
                return i;
            }
        }
        return -1;
    }

    private void selectProperType() {
        getPatternDataWindow().getPatternTextInput().setText(getDataObject().getText());
        for (Toggle t : getPatternDataWindow().getPatternTypeRadioButtonGroup().getToggles()) {
            RadioButton rb = (RadioButton) t;
            if (rb.getText().equals(getDataObject().getType().getDisplayName())) {
                getPatternDataWindow().getPatternTypeRadioButtonGroup().selectToggle(t);
                if (PatternType.AMOUNT == PatternType.getPatternTypeByDisplayName(rb.getText())) {
                    getPatternDataWindow().getAmountInput().setDisable(false);
                }
            }
        }
    }

    private void selectAmount() {
        getPatternDataWindow().getAmountInput().setText(getDataObject().getAmount() + "");
    }

    @Override
    public void fillFieldsWithData() {
        super.fillFieldsWithData();
        selectProperType();
        selectAmount();
    }
}
