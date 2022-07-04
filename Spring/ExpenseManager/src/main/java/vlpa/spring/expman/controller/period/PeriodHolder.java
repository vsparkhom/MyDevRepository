package vlpa.spring.expman.controller.period;

import java.util.ArrayList;
import java.util.List;

public class PeriodHolder {

    private static final int NUMBER_OF_PERIODS = 6; //last 6 months

    private static PeriodHolder instance;
    private int currentPeriodIndex;
    private List<Period> periods = new ArrayList<>(NUMBER_OF_PERIODS);;

    private PeriodHolder() {
        fillPeriodsList();
    }

    private void fillPeriodsList() {
        for (int i = 0; i < NUMBER_OF_PERIODS; i++) {
            periods.add(new MonthPeriod(i));
        }
        setCurrentPeriod(0);
    }

    public static PeriodHolder getInstance() {
        if (instance == null) {
            instance = new PeriodHolder();
        }
        return instance;
    }

    public Period getCurrentPeriod() {
        return periods.get(currentPeriodIndex);
    }

    public void setCurrentPeriod(int currentPeriodIndex) {
        this.currentPeriodIndex = currentPeriodIndex;
    }

    public List<Period> getPeriodsList() {
        return periods;
    }
}
