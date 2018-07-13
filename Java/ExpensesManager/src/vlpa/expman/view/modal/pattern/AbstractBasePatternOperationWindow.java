package vlpa.expman.view.modal.pattern;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.view.UIBuilder;
import vlpa.expman.view.modal.BasicOperationWindow;
import vlpa.expman.view.modal.FillableWindow;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractBasePatternOperationWindow<T> extends BasicOperationWindow implements FillableWindow {

    private TextField patternTextInput;
    private T dataObject;

    AbstractBasePatternOperationWindow(UIBuilder builder, MainProcessor processor, T dataObject) {
        super(builder, processor);
        this.dataObject = dataObject;
        fillFieldsWithData();
        selectProperCategory();
    }

    @Override
    protected List<Node> getMainPaneComponents() {
        Pane patternTextPane = getPatternTextPane();
        Pane patternCategoryPane = getPatternCategoryPane();
        return Arrays.asList(patternTextPane, patternCategoryPane);
    }

    protected Pane getPatternTextPane() {
        HBox patternTextPane = new HBox(5);
        Label patternTextLabel = new Label("Text: ");
        patternTextLabel.setPrefWidth(55);
        patternTextInput = new TextField();
        patternTextInput.setPrefWidth(340);
        patternTextPane.getChildren().addAll(patternTextLabel, patternTextInput);
        return patternTextPane;
    }

    protected Pane getPatternCategoryPane() {
        HBox patternCategoryPane = new HBox(5);
        Label patternCategoryText = new Label("Category:");
        patternCategoryText.setPrefWidth(55);
        getCategoriesComboBox().setPrefWidth(340);
        patternCategoryPane.getChildren().addAll(patternCategoryText, getCategoriesComboBox());
        return patternCategoryPane;
    }

    public TextField getPatternTextInput() {
        return patternTextInput;
    }

    public abstract void fillFieldsWithData();

    @Override
    public void selectProperCategory() {
        getCategoriesComboBox().getSelectionModel().selectFirst();
    }

    public T getDataObject() {
        return dataObject;
    }

}
