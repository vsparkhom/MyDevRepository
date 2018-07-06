package vlpa.expman.view.modal.pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.model.Category;
import vlpa.expman.view.UIBuilder;

import static vlpa.expman.controller.ImportProcessor.ANY_SYMBOL_TEMPLATE;

public class GeneratePatternWindow extends BasePatternOperationWindow {

    private String expenseMerchant;

    public GeneratePatternWindow(UIBuilder builder, MainProcessor processor, String expenseMerchant) {
        setBuilder(builder);
        setProcessor(processor);
        this.expenseMerchant = expenseMerchant;
        init();
    }

    @Override
    protected void init() {
        super.init();
        getPatternInput().setText(ANY_SYMBOL_TEMPLATE + expenseMerchant.replaceAll(" ", ANY_SYMBOL_TEMPLATE)
                + ANY_SYMBOL_TEMPLATE); //TODO: generate pattern
    }

    @Override
    protected String getWindowTitle() {
        return "Generate pattern";
    }

    @Override
    protected EventHandler<ActionEvent> getApplyButtonAction() {
        return event -> {
            String patternText = getPatternInput().getText();
            int selectedIndex = getCategoriesComboBox().getSelectionModel().getSelectedIndex();
            Category category = getCategories().get(selectedIndex);
            getProcessor().addPattern(patternText, category);
            close();
        };
    }
}
