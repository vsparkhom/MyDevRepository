package vlpa.expman.view.modal.pattern;

import javafx.scene.Node;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.view.UIBuilder;
import vlpa.expman.view.modal.AbstractBasicOperationWindow;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractBasicPatternOperationWindow<T> extends AbstractBasicOperationWindow<T> {

    private PatternDataWindow patternDataWindow;

    AbstractBasicPatternOperationWindow(UIBuilder builder, MainProcessor processor, T dataObject) {
        super(builder, processor, dataObject);
    }

    public PatternDataWindow getPatternDataWindow() {
        if (patternDataWindow == null) {
            patternDataWindow = new PatternDataWindow(getProcessor());
        }
        return patternDataWindow;
    }

    @Override
    protected List<Node> getMainPaneComponents() {
        return Arrays.asList(getPatternDataWindow());
    }

    @Override
    public void fillFieldsWithData() {
        selectProperCategory();
        selectProperPriority();
    }

    protected void selectProperCategory() {
        //DO NOTHING
    }

    protected void selectProperPriority() {
        //DO NOTHING
    }

}
