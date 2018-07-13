package vlpa.expman.view.modal;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vlpa.expman.controller.MainProcessor;
import vlpa.expman.view.UIBuilder;

import java.util.Collections;
import java.util.List;

public abstract class AbstractBasicOperationWindow<T> implements FillableWindow {

    private UIBuilder builder;
    private MainProcessor processor;
    private Stage stage;
    private EventHandler<ActionEvent> applyActionHandler;

    private T dataObject;

    public AbstractBasicOperationWindow(UIBuilder builder, T dataObject) {
        this(builder, null, dataObject);
    }

    public AbstractBasicOperationWindow(UIBuilder builder, MainProcessor processor, T dataObject) {
        this.builder = builder;
        this.processor = processor;
        this.dataObject = dataObject;
        init();
        fillFieldsWithData();
    }

    protected void init() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(builder.getPrimaryStage());
        stage.setTitle(getWindowTitle());

        Pane mainVerticalPane = getMainPane();

        mainVerticalPane.getChildren().add(getApplyCancelPane());
        Scene dialogScene = new Scene(mainVerticalPane, 420, 110);
        stage.setScene(dialogScene);
    }

    protected void setMainWindowSize(double w, double h) {
        stage.setWidth(w);
        stage.setHeight(h);
    }

    protected Pane getMainPane() {
        VBox mainVerticalPane = new VBox(5);
        mainVerticalPane.setPadding(new Insets(10));
        mainVerticalPane.getChildren().addAll(getMainPaneComponents());
        return mainVerticalPane;
    }

    protected List<Node> getMainPaneComponents() {
        return Collections.emptyList();
    }

    protected Pane getApplyCancelPane() {
        HBox applyCancelPane = new HBox(20);
        applyCancelPane.setAlignment(Pos.CENTER);
        Button applyButton = new Button("Apply");
        applyButton.setPrefWidth(80);
        applyButton.setOnAction(getApplyActionHandler());
        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(80);
        cancelButton.setOnAction(event -> close());
        applyCancelPane.getChildren().addAll(applyButton, cancelButton);
        return applyCancelPane;
    }

    public T getDataObject() {
        return dataObject;
    }

    protected UIBuilder getBuilder() {
        return builder;
    }

    protected void setBuilder(UIBuilder builder) {
        this.builder = builder;
    }

    protected MainProcessor getProcessor() {
        return processor;
    }

    protected void setProcessor(MainProcessor processor) {
        this.processor = processor;
    }

    public EventHandler<ActionEvent> getApplyActionHandler() {
        if (applyActionHandler != null) {
            return applyActionHandler;
        }
        return getDefaultApplyActionHandler();
    }

    public void setApplyActionHandler(EventHandler<ActionEvent> applyActionHandler) {
        this.applyActionHandler = applyActionHandler;
    }

    protected EventHandler<ActionEvent> getDefaultApplyActionHandler() {
        return null;
    }

    public void show() {
        stage.show();
    }

    public void close() {
        stage.close();
    }

    public abstract void fillFieldsWithData();

    protected abstract String getWindowTitle();

}
