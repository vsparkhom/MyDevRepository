package vlpa.expman.view.modal.pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.model.ImportPattern;
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
            System.out.println("Update category...");
            String updatedPatternText = getPatternTextInput().getText();
            int selectedIndex = getCategoriesComboBox().getSelectionModel().getSelectedIndex();
            Category updatedCategory = getCategories().get(selectedIndex);
            if (!getDataObject().getText().equals(updatedPatternText)) {
                setChanged(true);
                getDataObject().setText(updatedPatternText);

            }
            if (!getDataObject().getCategory().equals(updatedCategory)) {
                setChanged(true);
                getDataObject().setCategory(updatedCategory);
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
        List<String> patternComboBoxItems = getCategoriesComboBox().getItems();
        for (int i=0; i<patternComboBoxItems.size(); i++) {
            String currentPatternText = patternComboBoxItems.get(i);
            if (currentPatternText.equals(getDataObject().getCategory().getName())) {
                getCategoriesComboBox().getSelectionModel().select(i);
                break;
            }
        }
    }

    @Override
    public void fillFieldsWithData() {
        super.fillFieldsWithData();
        getPatternTextInput().setText(getDataObject().getText());
    }
}
