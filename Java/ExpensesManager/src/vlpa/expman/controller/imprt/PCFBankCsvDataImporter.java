package vlpa.expman.controller.imprt;

public class PCFBankCsvDataImporter extends CsvDataImporter {

    private PCFBankCsvDataImporter() {
    }

    private static class PCFBankCsvDataImporterHolder {
        public static CsvDataImporter instance = new PCFBankCsvDataImporter();
    }

    public static CsvDataImporter getInstance() {
        return PCFBankCsvDataImporter.PCFBankCsvDataImporterHolder.instance;
    }

    @Override
    public boolean isHeaderPresent() {
        return true;
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
        return 2;
    }
}
