package vlpa.spring.expman.controller.period;

public class PeriodHolder {

    private static PeriodHolder instance;
    private Period currentPeriod;

    private PeriodHolder() {
        setCurrentPeriod(0);
    }

    public static PeriodHolder getInstance() {
        if (instance == null) {
            instance = new PeriodHolder();
        }
        return instance;
    }

    public Period getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(int unitsAgo) {
        this.currentPeriod = new MonthPeriod(unitsAgo);
    }
}
