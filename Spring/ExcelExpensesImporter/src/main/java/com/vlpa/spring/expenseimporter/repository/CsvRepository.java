package com.vlpa.spring.expenseimporter.repository;

import com.opencsv.CSVReader;
import com.vlpa.spring.expenseimporter.BankStatementImporter;
import com.vlpa.spring.expenseimporter.model.Expense;
import org.apache.commons.lang3.StringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CsvRepository {

    public ArrayList<Expense> readCsvFile(String path, BankStatementImporter importer) throws IOException, ParseException {
        FileReader filereader = new FileReader(this.getClass().getClassLoader().getResource(path).getFile());

        CSVReader csvReader = new CSVReader(filereader);
        String[] nextRecord;

        ArrayList<Expense> expenses = new ArrayList<>();
        while ((nextRecord = csvReader.readNext()) != null) {
            if (importer.isHeaderPresent()) {
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
                    currentExpense.setAmount(Double.parseDouble(cell));
                }
                currentColumnIndex++;
            }
            expenses.add(currentExpense);
        }
        return expenses;
    }

    private Date parseDate(String dateString, String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(dateString);
    }
}
