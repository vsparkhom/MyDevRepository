package com.vlpa.spring.expenseimporter.model;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class ImportRequestData {

    private final static int CURRENT_YEAR = 2024;

    private Bank bank;
    private CardType cardType;
    private Date beginningOfTheMonth;

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Date getBeginningOfTheMonth() {
        return beginningOfTheMonth;
    }

    public void setBeginningOfTheMonth(Date beginningOfTheMonth) {
        this.beginningOfTheMonth = beginningOfTheMonth;
    }

    public void setBeginningOfTheMonth(int monthNumber) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(CURRENT_YEAR, monthNumber-1, 1);
        this.beginningOfTheMonth = calendar.getTime();
    }


}
