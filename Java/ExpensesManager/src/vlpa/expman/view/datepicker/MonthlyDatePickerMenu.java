package vlpa.expman.view.datepicker;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import vlpa.expman.view.UIBuilder;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.LinkedList;
import java.util.List;

public class MonthlyDatePickerMenu extends AbstractDatePickerMenu {

    public static final int VISIBLE_MONTHS_COUNT = 5;
    public static final int DECEMBER = 12;

    private HBox datePickerPane;

    public MonthlyDatePickerMenu(UIBuilder builder) {
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
        datePickerPane.setSpacing(10);//TODO: replace with styles

        Text label = new Text("Choose month:");

        List<MonthlyPeriod> periodsList = getMonthlyPeriods();

        ObservableList<String> monthsData = FXCollections.observableArrayList();
        for (MonthlyPeriod period : periodsList) {
            monthsData.add(period.monthName + "-" + period.year);
        }

        final ComboBox monthsComboBox = new ComboBox(monthsData);

        monthsComboBox.getSelectionModel().select(0);
        monthsComboBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldIndex, Number newIndex) {
                System.out.println("[INDEX] " + oldIndex + " -> " + newIndex);
                MonthlyPeriod period = periodsList.get(newIndex.intValue());
                startDate = period.start;
                System.out.println("Selected START date: " + startDate);
                endDate = period.end;
                System.out.println("Selected END date: " + endDate);
                getUIBuilder().updateView();
            }
        });

        datePickerPane.getChildren().addAll(label, monthsComboBox);
        HBox.setHgrow(datePickerPane, Priority.ALWAYS);

        return datePickerPane;
    }

    private List<MonthlyPeriod> getMonthlyPeriods() {
        List<MonthlyPeriod> periodsList = new LinkedList<>();
        LocalDate today = LocalDate.now();
        int year = today.getYear();

        for (int i = 0; i < VISIBLE_MONTHS_COUNT; i++) {
            int month = today.getMonth().minus(i).getValue();
            if (month == DECEMBER && i != 0) {
                year--;
            }
            YearMonth currentMonth = YearMonth.of(year, month);
            periodsList.add(new MonthlyPeriod(currentMonth.getMonth().name(), currentMonth.getYear(),
                    currentMonth.atDay(1), currentMonth.atEndOfMonth()));
        }
        return periodsList;
    }

    class MonthlyPeriod {

        String monthName;
        int year;
        LocalDate start;
        LocalDate end;

        MonthlyPeriod(String monthName, int year, LocalDate start, LocalDate end) {
            this.monthName = monthName;
            this.year = year;
            this.start = start;
            this.end = end;
        }
    }
}
