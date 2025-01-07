package com.vlpa.spring.expenseimporter.importers;

public class TdDebitCardImporter extends AbstractCreditCardImporter {

    @Override
    public String getPropertiesPrefix() {
        return "td.debit";
    }
}
