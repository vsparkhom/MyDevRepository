package vlpa.expman.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static vlpa.expman.view.UIDimensionsConst.DATE_PICKER_WIDTH;

public class DatePickerMenu {

    private UIBuilder builder;

    public DatePickerMenu(UIBuilder builder) {
        this.builder = builder;
    }

    //data
    private String startText = "Start date:";
    private String endText = "End date:";
    private LocalDate startDate;
    private LocalDate endDate;

    //UI components
    private HBox datePickerPane;
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;

    public Pane getMenuPane() {
        if (datePickerPane == null) {
            buildPane();
        }
        return datePickerPane;
    }

    private void buildPane() {

        datePickerPane = new HBox();
        datePickerPane.setAlignment(Pos.CENTER_RIGHT);
        datePickerPane.setPadding(new Insets(25, 12, 0, 12));
        datePickerPane.setSpacing(10);//TODO: replace with styles
//        datePickerBox.getStyleClass().add("date-picker");

        //start date

        Text startText = new Text(getStartText());

        startDatePicker = new DatePicker();
        startDatePicker.setPrefWidth(DATE_PICKER_WIDTH);

        startDate = LocalDate.now().minusMonths(1);
        startDatePicker.setValue(startDate);

        startDatePicker.setOnAction(event -> {
            startDate = startDatePicker.getValue();
            System.out.println("Selected START date: " + startDate);
            builder.updateView();
        });

        datePickerPane.getChildren().addAll(startText, startDatePicker);

        //end date

        Text endText = new Text(getEndText());

        endDatePicker = new DatePicker();
        endDatePicker.setPrefWidth(DATE_PICKER_WIDTH);

        endDate = LocalDate.now();
        endDatePicker.setValue(endDate);

        endDatePicker.setOnAction(event -> {
            endDate = endDatePicker.getValue();
            System.out.println("Selected END date: " + endDate);
            builder.updateView();
        });

        datePickerPane.getChildren().addAll(endText, endDatePicker);
        HBox.setHgrow(datePickerPane, Priority.ALWAYS);
    }

    public String getStartText() {
        return startText;
    }

    public void setStartText(String startText) {
        this.startText = startText;
    }

    public String getEndText() {
        return endText;
    }

    public void setEndText(String endText) {
        this.endText = endText;
    }

    public Date getStartDate() {
        return Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Date getEndDate() {
        return Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
