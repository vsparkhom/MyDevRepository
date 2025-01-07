package com.vlpa.spring.expenseimporter.importers;

public class TdCreditCardImporter extends AbstractCreditCardImporter {

    @Override
    public String getPropertiesPrefix() {
        return "td.credit";
    }
}
