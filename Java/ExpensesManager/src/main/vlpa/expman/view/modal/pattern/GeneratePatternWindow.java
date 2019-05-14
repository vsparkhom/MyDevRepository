package vlpa.expman.view.modal.pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.view.UIBuilder;

import static vlpa.expman.controller.imprt.ImportProcessor.ANY_SYMBOL_TEMPLATE;

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
            getProcessor().addPattern(PatternCreator.create(getPatternDataWindow()));
            close();
        };
    }

    @Override
    protected void selectProperCategory() {
        getPatternDataWindow().getCategoriesComboBox().getSelectionModel().selectFirst();
    }

    @Override
    public void selectProperPriority() {
        getPatternDataWindow().getPrioritiesComboBox().getSelectionModel().select(1); // MEDIUM priority by default;
    }

    @Override
    public void fillFieldsWithData() {
        super.fillFieldsWithData();
        getPatternDataWindow().getPatternTextInput().setText(ANY_SYMBOL_TEMPLATE + getDataObject().replaceAll(" ", ANY_SYMBOL_TEMPLATE)
                + ANY_SYMBOL_TEMPLATE);
    }
}
