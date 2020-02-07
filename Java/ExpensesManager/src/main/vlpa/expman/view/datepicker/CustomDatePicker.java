package vlpa.expman.view.datepicker;

import javafx.scene.layout.Pane;

import java.util.Date;

public interface CustomDatePicker {

    Pane getMenuPane();

    Date getStartDate();

    Date getEndDate();

}
