package com.vlpa.spring.expenseimporter.importers;

public interface BankStatementImporter {

    String getDefaultFileName();

    boolean isHeaderPresent();

    int getDateColumnIndex();

    int getMerchantColumnIndex();

    int getAmountColumnIndex();

    String getDateFormatPattern();

    boolean isCreditNegative();

    String getPropertiesPrefix();
}
