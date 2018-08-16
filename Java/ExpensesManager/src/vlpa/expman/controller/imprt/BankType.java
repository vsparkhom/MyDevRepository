package vlpa.expman.controller.imprt;

public enum BankType {

    TD_BANK_ACCOUNT("TD Bank", TDBankCsvDataImporter.getInstance()),
    PCF_BANK_ACCOUNT("PCF Bank", PCFBankCsvDataImporter.getInstance());

    private String name;
    private DataImporter dataImporter;

    BankType(String name, DataImporter dataImporter) {
        this.name = name;
        this.dataImporter = dataImporter;
    }

    public String getName() {
        return name;
    }

    public DataImporter getDataImporter() {
        return dataImporter;
    }
}
