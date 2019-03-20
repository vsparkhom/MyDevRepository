package vlpa.expman.controller.imprt;

import java.text.SimpleDateFormat;

public class CapitalOneBankCsvDataImporter extends AbstractCsvDataImporter {

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    private CapitalOneBankCsvDataImporter() {
    }

    private static class CapitalOneBankCsvDataImporterHolder {
        public static DataImporter instance = new CapitalOneBankCsvDataImporter();
    }

    public static DataImporter getInstance() {
        return CapitalOneBankCsvDataImporterHolder.instance;
    }

    @Override
    public boolean isHeaderPresent() {
        return true;
    }

    @Override
    public int getTotalFieldsNumber() {
        return 7;
    }

    @Override
    public int getTransactionDateFieldIndex() {
        return 0;
    }

    @Override
    public int getMerchantFieldIndex() {
        return 3;
    }

    @Override
    public int getAmountFieldIndex() {
        return 5;
    }

    @Override
    public SimpleDateFormat getDateFormatter() {
        return dateFormatter;
    }
}
