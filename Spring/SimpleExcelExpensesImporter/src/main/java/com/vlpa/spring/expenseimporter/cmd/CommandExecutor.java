package com.vlpa.spring.expenseimporter.cmd;

import com.vlpa.spring.expenseimporter.ExpensesImporterApplication;
import com.vlpa.spring.expenseimporter.importers.BankStatementImporter;
import com.vlpa.spring.expenseimporter.model.Card;
import com.vlpa.spring.expenseimporter.model.Expense;
import com.vlpa.spring.expenseimporter.model.ImportRequest;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.util.Calendar;
import java.util.List;

import static com.vlpa.spring.expenseimporter.LoggerUtils.debug;
import static com.vlpa.spring.expenseimporter.LoggerUtils.error;
import static com.vlpa.spring.expenseimporter.LoggerUtils.info;
import static com.vlpa.spring.expenseimporter.LoggerUtils.warning;

public class CommandExecutor {

    public void executeImport(String command) throws Exception {
        Options options = new Options();

        Option monthOption = createOption("m", "month", "MONTH", "Month number for expenses that are being imported/exported", true);
        Option yearOption = createOption("y", "year", "YEAR", "Year number for expenses that are being imported/exported", false);
        Option bankOption = createOption("b", "bank", "BANK", "Bank (TD, PCF, CIBC)", true);
        Option cardTypeOption = createOption("t", "type", "TYPE", "Card type (credit, debit)", true);
        Option fileNameOption = createOption("f", "file", "FILE", "Name of the CSV file with exported expenses", false);

        options.addOption(monthOption)
                .addOption(yearOption)
                .addOption(bankOption)
                .addOption(cardTypeOption)
                .addOption(fileNameOption);

        //input parameters
        String inputBank = "";
        String inputCardType = "";
        String monthNumber = "";
        String yearNumber = "";

        // see API - https://commons.apache.org/proper/commons-cli/usage.html
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, command.split(" "));

            if (line.hasOption("m")) {
                monthNumber = line.getOptionValue("m");
                info("Month number: " + monthNumber);
            }
            if (line.hasOption("y")) {
                yearNumber = line.getOptionValue("y");
                info("Yeah number: " + yearNumber);
            }
            if (line.hasOption("b")) {
                inputBank = line.getOptionValue("b");
                info("Bank: " + inputBank);
            }
            if (line.hasOption("t")) {
                inputCardType = line.getOptionValue("t");
                info("Card type: " + inputCardType);
            }

            // prepare request data
            ImportRequest request = new ImportRequest();
            request.setCard(Card.resolveCard(inputBank, inputCardType));

            if (!yearNumber.isEmpty()) {
                request.setBeginningOfTheMonth(Integer.valueOf(monthNumber), Integer.valueOf(yearNumber));
            } else {
                request.setBeginningOfTheMonth(Integer.valueOf(monthNumber));
            }

            ExpensesImporterApplication application = new ExpensesImporterApplication();

            debug("------------- EXPENSES -------------");

            info("Start import process with following parameters:" );
            info("    Card: " + request.getCard());
            info("    Begin date: " + request.getBeginningOfTheMonth());

            BankStatementImporter importer = application.getImporter(request);
            List<Expense> expenses = application.importExpensesFromCsv(request, importer);

//            for (Expense e : expenses) {
//                debug("    - " + e);
//            }

            application.storeExpensesToDatabase(expenses, request);

        } catch (ParseException exp) {
            error("Parsing failed.  Reason: " + exp.getMessage());
        }
    }

    public void executeExport(String command) throws Exception {
        Options options = new Options();
        Option monthOption = createOption("m", "month", "MONTH", "Month number for expenses that are being imported/exported", true);
        options.addOption(monthOption);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, command.split(" "));

            if (line.hasOption("m")) {
                String monthNumber = line.getOptionValue("m");
                info("Month number: " + monthNumber);

                ImportRequest request = new ImportRequest();
                request.setBeginningOfTheMonth(Integer.valueOf(monthNumber));

                ExpensesImporterApplication application = new ExpensesImporterApplication();

                List<Expense> expenses = application.getExpensesFromDatabase(request);

                if (expenses.isEmpty()) {
                    warning("Skip export as no expenses were found by request " + request);
                } else {
                    application.storeExpensesIntoExcel(expenses, request);
                }
            }
        } catch (ParseException exp) {
            error("Parsing failed. Reason: " + exp.getMessage());
        }
    }

    public void executeAll(String command) throws Exception {
        Options options = new Options();
        Option monthOption = createOption("m", "month", "MONTH", "Month number for expenses that are being imported/exported", true);
        Option yearOption = createOption("y", "year", "YEAR", "Year number for expenses that are being imported/exported", false);
        options.addOption(monthOption).addOption(yearOption);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, command.split(" "));

            String monthNumber = "";
            String yearNumber = Calendar.getInstance().getWeekYear() + "";

            if (line.hasOption("m")) {
                monthNumber = line.getOptionValue("m");
                info("Month number: " + monthNumber);
            }
            if (line.hasOption("y")) {
                yearNumber = line.getOptionValue("y");
                info("Yeah number: " + yearNumber);
            }

            executeImport(String.format("import -m %s -y %s -b td -t credit", monthNumber, yearNumber));
            executeImport(String.format("import -m %s -y %s -b td -t debit", monthNumber, yearNumber));
            executeImport(String.format("import -m %s -y %s -b pcf -t credit", monthNumber, yearNumber));
            executeImport(String.format("import -m %s -y %s -b cibc -t credit", monthNumber, yearNumber));

            executeExport(String.format("export -m %s", monthNumber));

        } catch (ParseException | java.text.ParseException exp) {
            error("Parsing failed. Reason: " + exp.getMessage());
        }
    }

    protected static Option createOption(String shortName, String longName, String argName, String description, boolean required) {
        return Option.builder(shortName)
                .longOpt(longName)
                .argName(argName)
                .desc(description)
                .hasArg()
                .required(required)
                .build();
    }
}
