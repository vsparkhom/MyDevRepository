package vlpa.expman.view.datepicker;

import javafx.scene.layout.Pane;
import vlpa.expman.view.UIBuilder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public abstract class AbstractDatePickerMenu implements CustomDatePicker {

    private UIBuilder builder;

    protected LocalDate startDate;
    protected LocalDate endDate;

    public AbstractDatePickerMenu(UIBuilder builder) {
        this.builder = builder;
        this.startDate = LocalDate.now().withDayOfMonth(1);
        this.endDate = LocalDate.now();
    }

    public Pane getMenuPane() {
        return buildPane();
    }

    protected UIBuilder getUIBuilder() {
        return builder;
    }

    @Override
    public Date getStartDate() {
        return Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public Date getEndDate() {
        return Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public abstract Pane buildPane();

}
