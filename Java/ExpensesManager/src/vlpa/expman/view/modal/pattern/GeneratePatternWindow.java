package vlpa.expman.view.modal.pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.view.UIBuilder;

import static vlpa.expman.controller.ImportProcessor.ANY_SYMBOL_TEMPLATE;

public class GeneratePatternWindow<T extends String> extends AbstractBasicPatternOperationWindow<T> {

    public GeneratePatternWindow(UIBuilder builder, MainProcessor processor, T merchant) {
        super(builder, processor, merchant);
    }

    @Override
    protected String getWindowTitle() {
        return "Generate pattern";
    }

    @Override
    protected EventHandler<ActionEvent> getDefaultApplyActionHandler() {
        return event -> {
            String patternText = getPatternTextInput().getText();
            int selectedIndex = getCategoriesComboBox().getSelectionModel().getSelectedIndex();
            Category category = getCategories().get(selectedIndex);
            String selectedTypeValue = ((RadioButton)getPatternTypeRadioButtonGroup().getSelectedToggle()).getText();
            PatternType type = PatternType.getPatternTypeByDisplayName(selectedTypeValue);
            getProcessor().addPattern(patternText, category, type);
            close();
        };
    }

    @Override
    protected void selectProperCategory() {
        getCategoriesComboBox().getSelectionModel().selectFirst();
    }

    @Override
    public void fillFieldsWithData() {
        super.fillFieldsWithData();
        System.out.println("initial row: '" + getDataObject() + "'");
        getPatternTextInput().setText(ANY_SYMBOL_TEMPLATE + getDataObject().replaceAll(" ", ANY_SYMBOL_TEMPLATE)
                + ANY_SYMBOL_TEMPLATE);
    }
}
