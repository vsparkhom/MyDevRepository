package vlpa.expman.view.datepicker;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import vlpa.expman.view.UIBuilder;

import java.time.LocalDate;

import static vlpa.expman.view.UIDimensionsConst.DATE_PICKER_WIDTH;

public class PeriodDatePickerMenu extends AbstractDatePickerMenu {

    private HBox datePickerPane;
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;

    public PeriodDatePickerMenu(UIBuilder builder) {
        super(builder);
    }

    @Override
    public Pane buildPane() {
        if (datePickerPane == null) {
            datePickerPane = buildPaneInternal();
        }
        return datePickerPane;
    }

    private HBox buildPaneInternal() {

        datePickerPane = new HBox();
        datePickerPane.setAlignment(Pos.CENTER_RIGHT);
        datePickerPane.setPadding(new Insets(25, 12, 0, 12));
        datePickerPane.setSpacing(10);

        //start date

        Text startText = new Text("Start date:");

        startDatePicker = new DatePicker();
        startDatePicker.setPrefWidth(DATE_PICKER_WIDTH);

        startDate = LocalDate.now().minusMonths(1);
        startDatePicker.setValue(startDate);

        startDatePicker.setOnAction(event -> {
            startDate = startDatePicker.getValue();
            getUIBuilder().updateView();
        });

        datePickerPane.getChildren().addAll(startText, startDatePicker);

        //end date

        Text endText = new Text("End date:");

        endDatePicker = new DatePicker();
        endDatePicker.setPrefWidth(DATE_PICKER_WIDTH);

        endDate = LocalDate.now();
        endDatePicker.setValue(endDate);

        endDatePicker.setOnAction(event -> {
            endDate = endDatePicker.getValue();
            getUIBuilder().updateView();
        });

        datePickerPane.getChildren().addAll(endText, endDatePicker);
        HBox.setHgrow(datePickerPane, Priority.ALWAYS);

        return datePickerPane;
    }

}
