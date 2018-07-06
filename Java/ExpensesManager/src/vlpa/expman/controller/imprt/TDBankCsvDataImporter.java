package vlpa.expman.controller.imprt;

public class TDBankCsvDataImporter extends CsvDataImporter {

    private TDBankCsvDataImporter() {
    }

    private static class TDBankCsvDataImporterHolder {
        public static CsvDataImporter instance = new TDBankCsvDataImporter();
    }

    public static CsvDataImporter getInstance() {
        return TDBankCsvDataImporterHolder.instance;
    }

    public int getTransactionDateFieldIndex() {
        return 0;
    }

    public int getMerchantFieldIndex() {
        return 1;
    }

    public int getAmountFieldIndex() {
        return 2;
    }
}
