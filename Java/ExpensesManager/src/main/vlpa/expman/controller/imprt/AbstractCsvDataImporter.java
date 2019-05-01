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
import org.apache.commons.lang3.StringUtils;
import vlpa.expman.view.modal.exception.ImportExpensesException;

public abstract class AbstractCsvDataImporter implements DataImporter {

    private final Logger LOGGER = LoggerFactory.getLogger(AbstractCsvDataImporter.class);

    private SimpleDateFormat defaultDateFormatter = new SimpleDateFormat("MM/dd/yyyy");
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
                    line = removeUnsupportedSymbols(line);
                    String[] expensesData = line.split(getFieldsSeparator());
                    Date date = parseDate(expensesData[getTransactionDateFieldIndex()]);
                    String msg = expensesData[getMerchantFieldIndex()].trim();
                    double amount = parseAmount(expensesData[getAmountFieldIndex()]);
                    if (amount > 0 || isDepositAllowed()) {
                        Expense e = new Expense(0, msg, date, amount, null);
                        e.setBank(getBankName());
                        expenses.add(e);
                    } else {
                        LOGGER.warn("Deposit is not allowed. Skip line: {}", line);
                    }
                }
            }
            LOGGER.info("Import has been finished successfully.");
        } catch (ParseException | IOException e) {
            LOGGER.error("Import can't be finished due to error", e);
            throw new ImportExpensesException("Import can't be finished due to error", e);
        }
        return expenses;
    }

    private String removeUnsupportedSymbols(String rawExpensesDataLine) {
        String resultExpensesDataLine = removeExcessSeparatorsInMerchantField(rawExpensesDataLine);
        String[] unsupportedSymbols = {"\""};
        for (String charToReplace : unsupportedSymbols) {
            resultExpensesDataLine = resultExpensesDataLine.replaceAll(charToReplace, "");
        }
        return resultExpensesDataLine;
    }

    private String removeExcessSeparatorsInMerchantField(String line) {
        if (StringUtils.countMatches(line, getFieldsSeparator()) > getTotalFieldsNumber() - 1) {
            int charIndexToDelete = StringUtils.ordinalIndexOf(line, getFieldsSeparator(),
                    getMerchantFieldIndex() + 1);
            StringBuilder tempBuffer = new StringBuilder(line);
            tempBuffer.deleteCharAt(charIndexToDelete);
            return tempBuffer.toString();
        }
        return line;
    }

    protected String getFieldsSeparator() {
        return ",";
    }

    protected SimpleDateFormat getDateFormatter() {
        return defaultDateFormatter;
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
        return getDateFormatter().parse(value);
    }

    public void setDepositAllowed(boolean depositAllowed) {
        this.depositAllowed = depositAllowed;
    }

    protected boolean isDepositAllowed() {
        return depositAllowed;
    }

    public abstract boolean isHeaderPresent();

    public abstract int getTotalFieldsNumber();

    public abstract int getTransactionDateFieldIndex();

    public abstract int getMerchantFieldIndex();

    public abstract int getAmountFieldIndex();
}
