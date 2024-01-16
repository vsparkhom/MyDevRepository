package com.vlpa.spring.expenseimporter;

public interface BankStatementImporter {

    boolean isHeaderPresent();

    int getDateColumnIndex();

    int getMerchantColumnIndex();

    int getAmountColumnIndex();

    String getDateFormatPattern();
}
