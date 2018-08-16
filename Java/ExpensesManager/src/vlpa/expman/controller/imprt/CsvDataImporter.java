package vlpa.expman.controller.imprt;

import vlpa.expman.model.Expense;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public abstract class CsvDataImporter implements DataImporter {

    private static final String FIELDS_SEPARATOR = ",";
    private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    private boolean depositAllowed = false;

    @Override
    public Collection<Expense> importExpensesFromFile(String fileName) {
        return getDataFromFile(fileName);
    }

    protected Collection<Expense> getDataFromFile(String fileName) {
        System.out.println("[DEBUG]<CsvDataImporter.getDataFromFile> Start importing data from file: " + fileName);
        Collection<Expense> expenses = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isHeaderSkipped = false;
            while ((line = br.readLine()) != null) {
                if (isHeaderPresent() && !isHeaderSkipped) {
                    System.out.println("[DEBUG]<CsvDataImporter.getDataFromFile> HeaderSkipped=true");
                    isHeaderSkipped = true;
                } else {
                    String[] expensesData = line.split(FIELDS_SEPARATOR);
                    Date date = parseDate(expensesData[getTransactionDateFieldIndex()]);
                    String msg = expensesData[getMerchantFieldIndex()];
                    double amount = parseAmount(expensesData[getAmountFieldIndex()]);
                    if (amount > 0 || isDepositAllowed()) {
                        Expense e = new Expense(0, msg, date, amount, null);
                        expenses.add(e);
                        System.out.println("[DEBUG]<CsvDataImporter.getDataFromFile> expense to import: " + e);
                    } else {
                        System.out.println("Deposit is not allowed. Skip line: " + line);
                    }
                }
            }
            System.out.println("[DEBUG]<CsvDataImporter.getDataFromFile> Import finished");
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    private double parseAmount(String value) {
        if (value == null || "".equals(value)) {
            return 0;
        }
        String sAmount = value.replace("$", "");
        sAmount = sAmount.contains("(") ? ("-" + sAmount.replace("(", "").replace(")", "")) : sAmount;
        return Double.valueOf(sAmount).doubleValue();
    }

    private Date parseDate(String value) throws ParseException {
        return formatter.parse(value);
    }

    public void setDepositAllowed(boolean depositAllowed) {
        this.depositAllowed = depositAllowed;
    }

    protected boolean isDepositAllowed() {
        return depositAllowed;
    }

    public abstract boolean isHeaderPresent();

    public abstract int getTransactionDateFieldIndex();

    public abstract int getMerchantFieldIndex();

    public abstract int getAmountFieldIndex();
}
