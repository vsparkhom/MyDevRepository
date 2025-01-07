package com.vlpa.spring.expenseimporter.model;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class ImportRequest {

    private final static int CURRENT_YEAR = 2024;//TODO: make configurable

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
        Calendar calendar = Calendar.getInstance();
        calendar.set(CURRENT_YEAR, monthNumber-1, 1, 0, 0, 0);
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
