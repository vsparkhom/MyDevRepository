package com.vlpa.spring.expenseimporter.importers;

public class PcfCreditCardImporter extends AbstractCreditCardImporter {

    @Override
    public String getPropertiesPrefix() {
        return "pcf.credit";
    }
}
