package com.vlpa.spring.expenseimporter.importers;

import com.vlpa.spring.expenseimporter.BankStatementImporter;

public class PcfCreditCardImporter implements BankStatementImporter {

    @Override
    public String getFileName() {
        return "PCF_CC.csv";
    }

    public boolean isHeaderPresent() {
        return true;
    }

    public int getDateColumnIndex() {
        return 3;
    }

    public int getMerchantColumnIndex() {
        return 0;
    }

    public int getAmountColumnIndex() {
        return 5;
    }

    public String getDateFormatPattern() {
        return "MM/dd/yyyy";
    }

    public int getTemplateColumnNumber() {
        return 2;
    }

    public boolean isCreditNegative() {
        return true;
    }
}
