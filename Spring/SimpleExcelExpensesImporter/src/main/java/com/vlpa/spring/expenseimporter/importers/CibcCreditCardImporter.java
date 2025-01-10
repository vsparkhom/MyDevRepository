package com.vlpa.spring.expenseimporter.importers;

public class CibcCreditCardImporter extends AbstractCreditCardImporter {

    @Override
    public String getPropertiesPrefix() {
        return "cibc.credit";
    }
}
