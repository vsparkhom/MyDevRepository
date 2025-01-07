package com.vlpa.spring.expenseimporter.importers;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static com.vlpa.spring.expenseimporter.LoggerUtils.error;

public abstract class AbstractCreditCardImporter implements BankStatementImporter {

    private Properties properties;

    public AbstractCreditCardImporter() {
        initProperties();
    }

    private void initProperties() {
        String file = this.getClass().getClassLoader().getResource("properties/importers.properties").getFile();
        try(FileReader reader = new FileReader(file)) {
            properties = new Properties();
            properties.load(reader);
        } catch (IOException e) {
            error("Can't load properties due to error: " + e.getMessage());
        }
    }

    public Properties getProperties() {
        return properties;
    }

    @Override
    public String getDefaultFileName() {
        return getProperties().getProperty(getPropertiesPrefix() + ".filename");
    }

    @Override
    public boolean isHeaderPresent() {
        String value = getProperties().getProperty(getPropertiesPrefix() + ".header");
        return Boolean.getBoolean(value);
    }

    @Override
    public int getDateColumnIndex() {
        String value = getProperties().getProperty(getPropertiesPrefix() + ".column_index.date");
        return Integer.valueOf(value);
    }

    @Override
    public int getMerchantColumnIndex() {
        String value = getProperties().getProperty(getPropertiesPrefix() + ".column_index.merchant");
        return Integer.valueOf(value);
    }

    @Override
    public int getAmountColumnIndex() {
        String value = getProperties().getProperty(getPropertiesPrefix() + ".column_index.amount");
        return Integer.valueOf(value);
    }

    @Override
    public String getDateFormatPattern() {
        return getProperties().getProperty(getPropertiesPrefix() + ".date_format");
    }

    @Override
    public boolean isCreditNegative() {
        String value = getProperties().getProperty(getPropertiesPrefix() + ".negative_credit");
        return Boolean.getBoolean(value);
    }

    public abstract String getPropertiesPrefix();
}
