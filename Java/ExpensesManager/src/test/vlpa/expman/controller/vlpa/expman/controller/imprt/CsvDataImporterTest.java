package vlpa.expman.controller.vlpa.expman.controller.imprt;

import org.junit.Test;
import vlpa.expman.controller.imprt.AbstractCsvDataImporter;
import vlpa.expman.model.Expense;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static vlpa.expman.controller.vlpa.expman.controller.imprt.ImportProcessorTest.TEST_EXPENSES_FILE;

public class CsvDataImporterTest {

    private final String EXPECTED_EXPENSE_LINE = "02/03/2020,Test Expense 1,11.22,,500.55";

    private TestableCsvDataImporter importer = new TestableCsvDataImporter();

    @Test
    public void testRemoveExcessSeparatorsInMerchantField_whenSeparatorDoesNotExist() {
        assertEquals(EXPECTED_EXPENSE_LINE, importer.removeExcessSeparatorsInMerchantField(EXPECTED_EXPENSE_LINE));
    }

    @Test
    public void testRemoveExcessSeparatorsInMerchantField_whenSingleSeparatorExists() {
        String testLine = "02/03/2020,Test, Expense 1,11.22,,500.55";
        assertEquals(EXPECTED_EXPENSE_LINE, importer.removeExcessSeparatorsInMerchantField(testLine));
    }

//    @Test
//    public void testRemoveExcessSeparatorsInMerchantField_whenSingleMultipleExists() {//TODO: multiple separators case is not supported yet
//        String expectedResultLine = "02/03/2020,Test Expense 1,11.22,,500.55";
//        String testLine = "02/03/2020,Test, ,Expense, 1,11.22,,500.55";
//        assertEquals(expectedResultLine, importer.removeExcessSeparatorsInMerchantField(testLine));
//    }

    @Test
    public void testRemoveUnsupportedSymbols_whenUnsupportedSymbolDoesNotExist() {
        assertEquals(EXPECTED_EXPENSE_LINE, importer.removeUnsupportedSymbols(EXPECTED_EXPENSE_LINE));
    }

    @Test
    public void testRemoveUnsupportedSymbols_whenUnsupportedSymbolExists() {
        String testLine = "\"02/03/2020\",Test \"Expense\" 1,11.22,,500.55";
        assertEquals(EXPECTED_EXPENSE_LINE, importer.removeUnsupportedSymbols(testLine));
    }

    @Test
    public void testParseAmount_whenDollarSymbolExists() {
        assertEquals(123d, importer.parseAmount("$123.00"), 0);
    }

    @Test
    public void testParseAmount_whenNegativeValue() {
        assertEquals(-10d, importer.parseAmount("(10.00)"), 0);
    }

    @Test
    public void testGetDataFromFile() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Expense[] expectedExpenseMerchants = {
                Expense.builder().setName("Test Expense 5.1").setDate(formatter.parse("02/05/2020")).setAmount(15.67).build(),
                Expense.builder().setName("Test Expense 4.1").setDate(formatter.parse("02/05/2020")).setAmount(14.56).build(),
                Expense.builder().setName("Test Expense 3.1").setDate(formatter.parse("02/04/2020")).setAmount(13.45).build(),
                Expense.builder().setName("Test Expense 2.1").setDate(formatter.parse("02/04/2020")).setAmount(12.34).build(),
                Expense.builder().setName("Test Expense 1.1").setDate(formatter.parse("02/03/2020")).setAmount(11.23).build(),
                Expense.builder().setName("Unknown Expense 1").setDate(formatter.parse("02/03/2020")).setAmount(50).build(),
                Expense.builder().setName("Amount Expense 1").setDate(formatter.parse("02/02/2020")).setAmount(1500).build(),
                Expense.builder().setName("Skip Expense 2").setDate(formatter.parse("02/02/2020")).setAmount(200).build(),
                Expense.builder().setName("Skip Expense 1").setDate(formatter.parse("02/01/2020")).setAmount(100).build()
        };

        List<Expense> expensesFromFile = new LinkedList(importer.getDataFromFile(TEST_EXPENSES_FILE));

        assertEquals(expectedExpenseMerchants.length, expensesFromFile.size());
        for (int i = 0; i < expectedExpenseMerchants.length; i++) {
            assertEquals(expectedExpenseMerchants[i].getName(), expensesFromFile.get(i).getName());
            assertEquals(expectedExpenseMerchants[i].getAmount(), expensesFromFile.get(i).getAmount(), 0);
            assertEquals(expectedExpenseMerchants[i].getDate(), expensesFromFile.get(i).getDate());
        }
    }

    private class TestableCsvDataImporter extends AbstractCsvDataImporter {

        @Override
        public boolean isHeaderPresent() {
            return false;
        }

        @Override
        public int getTotalFieldsNumber() {
            return 5;
        }

        @Override
        public int getTransactionDateFieldIndex() {
            return 0;
        }

        @Override
        public int getMerchantFieldIndex() {
            return 1;
        }

        @Override
        public int getAmountFieldIndex() {
            return 2;
        }

        @Override
        public String getBankName() {
            return "Test Bank";
        }

        protected Collection<Expense> getDataFromFile(String fileName) {
            return super.getDataFromFile(fileName);
        }

        public String removeUnsupportedSymbols(String rawExpensesDataLine) {
            return super.removeUnsupportedSymbols(rawExpensesDataLine);
        }

        public String removeExcessSeparatorsInMerchantField(String line) {
            return super.removeExcessSeparatorsInMerchantField(line);
        }

        public double parseAmount(String value) {
            return super.parseAmount(value);
        }
    }

}
