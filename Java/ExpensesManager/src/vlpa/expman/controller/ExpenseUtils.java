package vlpa.expman.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExpenseUtils {

    private static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static Date toDate(String s) {
        try {
            return formatter.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String fromDate(Date date) {
        return formatter.format(date);
    }

    public static double round(double value, int numCountAfterComma) {
        double powValue = Math.pow(10, numCountAfterComma);
        return Math.round(value * powValue) / powValue;
    }

}
