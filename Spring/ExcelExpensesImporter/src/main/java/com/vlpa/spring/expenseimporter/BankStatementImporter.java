package com.vlpa.spring.expenseimporter;

public interface BankStatementImporter {

    String getFileName();

    boolean isHeaderPresent();

    int getDateColumnIndex();

    int getMerchantColumnIndex();

    int getAmountColumnIndex();

    String getDateFormatPattern();

    int getTemplateColumnNumber();

    boolean isCreditNegative();
}
