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

public class CsvDataImporter extends AbstractDataImporter {

    private static final String FIELDS_SEPARATOR = ",";
    private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    private CsvDataImporter() {
    }

    private static class DataProcessorInstanceHolder {
        public static CsvDataImporter instance = new CsvDataImporter();
    }

    public static CsvDataImporter getInstance() {
        return DataProcessorInstanceHolder.instance;
    }

    @Override
    protected Collection<Expense> getDataFromFile(String fileName) {
        Collection<Expense> expenses = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isHeaderSkipped = false;
            while ((line = br.readLine()) != null) {
                if (!isHeaderSkipped) {
                    isHeaderSkipped = true;
                } else {
                    String[] expensesData = line.split(FIELDS_SEPARATOR);
                    Date date = parseDate(expensesData[0]);
                    double amount = parseAmount(expensesData[2]);
                    String msg = expensesData[3];
                    expenses.add(new Expense(0, msg, date, amount, null));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    private double parseAmount(String value) {
        String sAmount = value.replace("$", "");
        sAmount = sAmount.contains("(") ? ("-" + sAmount.replace("(", "").replace(")", "")) : sAmount;
        return Double.valueOf(sAmount).doubleValue();
    }

    private Date parseDate(String value) throws ParseException {
        return formatter.parse(value);
    }
}
