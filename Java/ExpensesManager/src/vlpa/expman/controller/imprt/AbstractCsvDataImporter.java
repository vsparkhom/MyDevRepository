package vlpa.expman.controller.imprt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vlpa.expman.model.Expense;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public abstract class AbstractCsvDataImporter implements DataImporter {

    private static final String FIELDS_SEPARATOR = ",";
    private final Logger LOGGER = LoggerFactory.getLogger(AbstractCsvDataImporter.class);

    private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    private boolean depositAllowed = false;

    @Override
    public Collection<Expense> importExpensesFromFile(String fileName) {
        return getDataFromFile(fileName);
    }

    protected Collection<Expense> getDataFromFile(String fileName) {
        LOGGER.info("Start data import from file: {}", fileName);
        Collection<Expense> expenses = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isHeaderSkipped = false;
            while ((line = br.readLine()) != null) {
                if (isHeaderPresent() && !isHeaderSkipped) {
                    isHeaderSkipped = true;
                } else {
                    String[] expensesData = line.split(FIELDS_SEPARATOR);
                    Date date = parseDate(expensesData[getTransactionDateFieldIndex()]);
                    String msg = expensesData[getMerchantFieldIndex()].trim();
                    double amount = parseAmount(expensesData[getAmountFieldIndex()]);
                    if (amount > 0 || isDepositAllowed()) {
                        Expense e = new Expense(0, msg, date, amount, null);
                        expenses.add(e);
                    } else {
                        LOGGER.warn("Deposit is not allowed. Skip line: {}", line);
                    }
                }
            }
            LOGGER.info("Import has been finished successfully.");
        } catch (ParseException | IOException e) {
            LOGGER.error("Import can't be finished due to error", e);
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
