package vlpa.expman.controller.imprt;

public class PCFBankCsvDataImporter extends AbstractCsvDataImporter {

    private PCFBankCsvDataImporter() {
    }

    private static class PCFBankCsvDataImporterHolder {
        public static DataImporter instance = new PCFBankCsvDataImporter();
    }

    public static DataImporter getInstance() {
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
