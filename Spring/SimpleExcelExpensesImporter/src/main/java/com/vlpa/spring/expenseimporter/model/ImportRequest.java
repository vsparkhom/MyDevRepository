package com.vlpa.spring.expenseimporter.model;

import java.util.Calendar;
import java.util.Date;

import static com.vlpa.spring.expenseimporter.LoggerUtils.warning;

public class ImportRequest {

    private Card card;
    private Date beginningOfTheMonth;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Date getBeginningOfTheMonth() {
        return beginningOfTheMonth;
    }

    public void setBeginningOfTheMonth(Date beginningOfTheMonth) {
        this.beginningOfTheMonth = beginningOfTheMonth;
    }

    public void setBeginningOfTheMonth(int monthNumber) {
        warning("Year has not been provided! Using current one.");
        setBeginningOfTheMonth(monthNumber, Calendar.getInstance().getWeekYear());
    }

    public void setBeginningOfTheMonth(int monthNumber, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthNumber-1, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        this.beginningOfTheMonth = calendar.getTime();
    }

    @Override
    public String toString() {
        return "ImportRequest{" +
                "card=" + card +
                ", beginningOfTheMonth=" + beginningOfTheMonth +
                '}';
    }
}
