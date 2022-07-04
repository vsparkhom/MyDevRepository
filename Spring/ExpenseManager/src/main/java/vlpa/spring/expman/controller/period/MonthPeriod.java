package vlpa.spring.expman.controller.period;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MonthPeriod implements Period {

    private int monthsAgo;
    private Date startDate;
    private Date endDate;

    public MonthPeriod(int monthsAgo) {
        this.monthsAgo = monthsAgo;
        initDates(monthsAgo);
    }

    private void initDates(int monthAgo) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -monthAgo);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        startDate = cal.getTime();
        cal.add(Calendar.MONTH, 1);
        endDate = cal.getTime();
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

//    @Override
//    public int getNumberOfUnits() {
//        return monthsAgo;
//    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.monthsAgo = numberOfUnits;
    }

    @Override
    public String getName() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.CANADA);
    }

    @Override
    public int getIndex() {
        return monthsAgo;
    }
}
