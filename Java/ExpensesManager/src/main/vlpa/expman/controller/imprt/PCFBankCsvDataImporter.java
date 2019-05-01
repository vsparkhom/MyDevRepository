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
    public int getTotalFieldsNumber() {
        return 5;
    }

    @Override
    public int getTransactionDateFieldIndex() {
        return 2;
    }

    @Override
    public int getMerchantFieldIndex() {
        return 0;
    }

    @Override
    public int getAmountFieldIndex() {
        return 4;
    }

    @Override
    public String getBankName() {
        return BankType.PCF_BANK_ACCOUNT.getName();
    }
}
