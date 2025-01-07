package com.vlpa.spring.expenseimporter.repository;

import com.opencsv.CSVReader;
import com.vlpa.spring.expenseimporter.importers.BankStatementImporter;
import com.vlpa.spring.expenseimporter.model.Expense;
import org.apache.commons.lang3.StringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.vlpa.spring.expenseimporter.LoggerUtils.info;

public class CsvRepository {

    public ArrayList<Expense> readCsvFile(BankStatementImporter importer) throws IOException, ParseException {
        info("Read CSV file - START");

        String fileName = importer.getDefaultFileName();
        info("File name: " + fileName);

        String file = this.getClass().getClassLoader().getResource(fileName).getFile();
        FileReader filereader = new FileReader(file);

        CSVReader csvReader = new CSVReader(filereader);
        String[] nextRecord;

        ArrayList<Expense> expenses = new ArrayList<>();
        boolean isHeaderSkipped = false;

        while ((nextRecord = csvReader.readNext()) != null) {
            if (importer.isHeaderPresent() && !isHeaderSkipped) {
                isHeaderSkipped = true;
                continue;
            }
            int currentColumnIndex = 0;
            Expense currentExpense = new Expense();
            for (String cell : nextRecord) {
                if (currentColumnIndex == importer.getDateColumnIndex()) {
                    currentExpense.setDate(parseDate(cell, importer.getDateFormatPattern()));
                } else if (currentColumnIndex == importer.getMerchantColumnIndex()) {
                    currentExpense.setMerchant(cell);
                } else if (currentColumnIndex == importer.getAmountColumnIndex() && StringUtils.isNotEmpty(cell)) {
                    double value = Double.parseDouble(cell);
                    currentExpense.setAmount(importer.isCreditNegative() ? -value : value);
                }
                currentColumnIndex++;
            }
            if (currentExpense.getAmount() > 0) { //Do not include debit or zero transactions
                expenses.add(currentExpense);
            }
        }
        info("Read CSV file - END\n");
        return expenses;
    }

    private Date parseDate(String dateString, String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(dateString);
    }
}
