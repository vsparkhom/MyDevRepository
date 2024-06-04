package com.vlpa.spring.expenseimporter.repository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpenseParsingHelper {

    public static final String EXPENSE_AMOUNT_REGEXP_STATIC_PART = "<amount=";
    public static final String EXPENSE_AMOUNT_REGEXP = EXPENSE_AMOUNT_REGEXP_STATIC_PART + "\\d+>";

    public static boolean isAmountBasedPattern(String text) {
        Pattern pattern = Pattern.compile(EXPENSE_AMOUNT_REGEXP);
        Matcher matcher = pattern.matcher(text);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches > 0;
    }

    public static String parseExpressionFromAmountBasedPattern(String text) {
        return text.substring(0, text.indexOf(EXPENSE_AMOUNT_REGEXP_STATIC_PART));
    }

    public static double parseAmountValue(String text) {
        Pattern pattern = Pattern.compile(EXPENSE_AMOUNT_REGEXP);
        Matcher matcher = pattern.matcher(text);

        String amountString = null;
        while (matcher.find()) {
            amountString = matcher.group();
            break;
        }

        double amountValue = 0;
        if (amountString != null && !"".equals(amountString)) {
            amountValue = Double.parseDouble(amountString.substring(amountString.indexOf('=') + 1, amountString.length() - 1));
        }

        return amountValue;
    }
}
