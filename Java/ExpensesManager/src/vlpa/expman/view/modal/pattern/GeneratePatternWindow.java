package vlpa.expman.view.modal.pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.view.UIBuilder;

import static vlpa.expman.controller.ImportProcessor.ANY_SYMBOL_TEMPLATE;

public class GeneratePatternWindow<T extends String> extends AbstractBasePatternOperationWindow {

    public GeneratePatternWindow(UIBuilder builder, MainProcessor processor, T merchant) {
        super(builder, processor, merchant);
    }

    @Override
    public T getDataObject() {
        return (T) super.getDataObject();
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
            getProcessor().addPattern(patternText, category);
            close();
        };
    }

    @Override
    public void fillFieldsWithData() {
        getPatternTextInput().setText(ANY_SYMBOL_TEMPLATE + getDataObject().replaceAll(" ", ANY_SYMBOL_TEMPLATE)
                + ANY_SYMBOL_TEMPLATE); //TODO: generate pattern
    }
}
