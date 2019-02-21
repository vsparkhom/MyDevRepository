package vlpa.expman.controller.imprt;

public class TDBankCsvDataImporter extends AbstractCsvDataImporter {

    private TDBankCsvDataImporter() {
    }

    private static class TDBankCsvDataImporterHolder {
        public static DataImporter instance = new TDBankCsvDataImporter();
    }

    public static DataImporter getInstance() {
        return TDBankCsvDataImporterHolder.instance;
    }

    @Override
    public boolean isHeaderPresent() {
        return false;
    }

    @Override
    public int getTransactionDateFieldIndex() {
        return 0;
    }

    @Override
    public int getMerchantFieldIndex() {
        return 1;
    }

    @Override
    public int getAmountFieldIndex() {
        return 2;
    }
}
