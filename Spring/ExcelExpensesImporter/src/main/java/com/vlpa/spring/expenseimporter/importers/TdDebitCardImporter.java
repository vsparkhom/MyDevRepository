package com.vlpa.spring.expenseimporter.importers;

import com.vlpa.spring.expenseimporter.BankStatementImporter;

public class TdDebitCardImporter implements BankStatementImporter {

    @Override
    public String getFileName() {
        return "TD_DEBIT.csv";
    }

    public boolean isHeaderPresent() {
        return false;
    }

    public int getDateColumnIndex() {
        return 0;
    }

    public int getMerchantColumnIndex() {
        return 1;
    }

    public int getAmountColumnIndex() {
        return 2;
    }

    public String getDateFormatPattern() {
        return "MM/dd/yyyy";
    }

    public int getTemplateColumnNumber() {
        return 0;
    }

    public boolean isCreditNegative() {
        return false;
    }
}
