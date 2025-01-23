package com.vlpa.spring.expenseimporter;

import com.vlpa.spring.expenseimporter.importers.BankStatementImporter;
import com.vlpa.spring.expenseimporter.model.Card;
import com.vlpa.spring.expenseimporter.model.Category;
import com.vlpa.spring.expenseimporter.model.Expense;
import com.vlpa.spring.expenseimporter.model.ImportRequest;
import com.vlpa.spring.expenseimporter.model.Pattern;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import static com.vlpa.spring.expenseimporter.LoggerUtils.debug;
import static com.vlpa.spring.expenseimporter.LoggerUtils.error;
import static com.vlpa.spring.expenseimporter.LoggerUtils.info;
import static com.vlpa.spring.expenseimporter.LoggerUtils.warning;

public class MainApplicationRunner {

    public static void main(String[] args) throws Exception {

        LoggerUtils.setCurrentLevel(LoggerUtils.LogLevel.ALL);

        String command;
        Scanner input = new Scanner(System.in);

        do {

            System.out.print("\nCommand: ");
            command = input.nextLine().trim();

            String[] cmdParts = command.split(" ");
            if ("import".equalsIgnoreCase(cmdParts[0])) {
                System.out.println("perform IMPORT command");

                /* examples:
                import -m 12 -b=td -t credit
                import -m 12 -b=td -t debit
                import -m 12 -b=pcf -t credit
                import -m 12 -b=cibc -t credit
                 */
                runImportCommand(command);

            } else if ("export".equalsIgnoreCase(cmdParts[0])) {
                System.out.println("perform EXPORT command");

                //execAll -m 12
                runExportCommand(command);

            } else if ("execAll".equalsIgnoreCase(cmdParts[0])) {
                System.out.println("perform EXECUTE ALL command");

                runExecuteAllCommand(command);

            } else if ("config".equalsIgnoreCase(cmdParts[0])) {
                System.out.println("perform CONFIG command");

                runConfigCommand(cmdParts[1]);

            } else if (!"exit".equalsIgnoreCase(command)) {
                System.out.println("UNKNOWN CMD");
            }

        } while (!"exit".equalsIgnoreCase(command));

        input.close();

    }

    private static Option createOption(String shortName, String longName, String argName, String description, boolean required) {
        return Option.builder(shortName)
                .longOpt(longName)
                .argName(argName)
                .desc(description)
                .hasArg()
                .required(required)
                .build();
    }

    private static void runExecuteAllCommand(String command) throws IOException {
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

            runImportCommand(String.format("import -m %s -y %s -b=td -t credit", monthNumber, yearNumber));
            runImportCommand(String.format("import -m %s -y %s -b=td -t debit", monthNumber, yearNumber));
            runImportCommand(String.format("import -m %s -y %s -b=pcf -t credit", monthNumber, yearNumber));
            runImportCommand(String.format("import -m %s -y %s -b=cibc -t credit", monthNumber, yearNumber));

            runExportCommand(String.format("export -m %s", monthNumber));
        } catch (ParseException | java.text.ParseException exp) {
            error("Parsing failed. Reason: " + exp.getMessage());
            exp.printStackTrace();
        }
    }

    private static void runImportCommand(String command) throws java.text.ParseException, IOException {
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

            if (yearNumber.isEmpty()) {
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

    private static void runExportCommand(String command) throws IOException {
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

    private static void runConfigCommand(String configAction) throws IOException {

        ExpensesImporterApplication application = new ExpensesImporterApplication();

        if ("categories".equalsIgnoreCase(configAction) || "-c".equalsIgnoreCase(configAction)) { //loads categories from Excel config file and stores them to database

            List<Category> categories = application.getCategoriesFromExcel();
            debug("------------- CATEGORIES -------------");
            for (Category category : categories) {
                info(category.toString());
            }
            application.storeCategoriesToDatabase(categories);

        } else if ("patterns".equalsIgnoreCase(configAction) || "-p".equalsIgnoreCase(configAction)) { //loads patterns from Excel config file and displays them

            debug("------------- PATTERNS -------------");
            List<Category> categories = application.getCategoriesFromExcel();
            List<Pattern> patterns = application.getPatternsMappingFromExcel(categories);
            for (Pattern pattern : patterns) {
                debug(pattern.toString());
            }

        }
    }

}
