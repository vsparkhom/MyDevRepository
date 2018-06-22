package vlpa.expman.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ExpenseUtils {

    private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static Date fromStringToDate(String s) {
        try {
            return formatter.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date fromLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate fromDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static String fromDateToString(Date date) {
        return formatter.format(date);
    }

    public static double round(double value, int numCountAfterComma) {
        double powValue = Math.pow(10, numCountAfterComma);
        return Math.round(value * powValue) / powValue;
    }

}
