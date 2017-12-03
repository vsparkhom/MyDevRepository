package vlpa.expman.controller;

public class ExpenseUtils {

    public static double round(double value, int numCountAfterComma) {
        double powValue = Math.pow(10, numCountAfterComma);
        return Math.round(value * powValue) / powValue;
    }

}
