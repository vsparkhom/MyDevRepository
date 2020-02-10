package vlpa.expman.controller.vlpa.expman.controller.imprt;

import org.junit.Before;
import org.junit.Test;
import vlpa.expman.controller.imprt.ImportProcessor;
import vlpa.expman.controller.imprt.BankType;
import vlpa.expman.dao.category.CategoriesRepository;
import vlpa.expman.dao.expense.ExpensesRepository;
import vlpa.expman.model.*;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ImportProcessorTest {

    public static final String TEST_EXPENSES_FILE = "res/test/import/test_expenses.csv";

    private TestableImportProcessor importProcessorInstance;
    private ExpensesRepository expensesRepositoryMock;
    private CategoriesRepository categoriesRepositorySpy;
    private Category unknownCategory;

    @Before
    public void setUp() {
        unknownCategory = new Category(10, "Unknown Category", 1000.0);

        expensesRepositoryMock = mock(ExpensesRepository.class);

        categoriesRepositorySpy = spy(new CategoriesRepository());
        when(categoriesRepositorySpy.getUnknownCategory()).thenReturn(unknownCategory);

        importProcessorInstance = spy(new TestableImportProcessor(expensesRepositoryMock, categoriesRepositorySpy));
        doReturn(Collections.emptyList()).when(importProcessorInstance).importExpensesFromFile(any(String.class), any(BankType.class));
    }

    private Expense buildExpense(long id, String name, Date date, double amount, Category category) {
        return Expense.builder()
                .setId(id)
                .setName(name)
                .setDate(date)
                .setAmount(amount)
                .setCategory(category).build();
    }

    private ImportPattern buildPattern(long id, String text, Category category) {
        return buildPattern(id, text, category, PatternType.REGULAR, PatternPriority.MEDIUM, 0);
    }

    private ImportPattern buildPattern(long id, String text, Category category, PatternType type,
            PatternPriority priority) {
        return buildPattern(id, text, category, type, priority, 0);
    }

    private ImportPattern buildPattern(long id, String text, Category category, PatternType type,
            PatternPriority priority, double amount) {
        return ImportPattern.builder()
                .setId(id)
                .setText(text)
                .setCategory(category)
                .setType(type)
                .setPriority(priority)
                .setAmount(amount).build();
    }

    @Test
    public void testImportExpenses_checkMethodsCalled() {
        doCallRealMethod().when(importProcessorInstance).importExpensesFromFile(eq(TEST_EXPENSES_FILE), any(BankType.class));

        importProcessorInstance.importExpenses(TEST_EXPENSES_FILE, BankType.TD_BANK_ACCOUNT);

        verify(importProcessorInstance).importExpensesFromFile(eq(TEST_EXPENSES_FILE), eq(BankType.TD_BANK_ACCOUNT));
        verify(importProcessorInstance).sortExpensesByCategories(any());
        verify(importProcessorInstance).storeExpenses(any());
    }

    @Test
    public void testSortExpensesByCategories_checkPatternsSorted() {

        Category category1 = new Category(20, "Fist Category", 1000.0);
        Category category2 = new Category(30, "Second Category", 1000.0);
        Category category3 = new Category(40, "Third Category", 1000.0);
        Category category4 = new Category(50, "Fourth Category", 1000.0);

        List<ImportPattern> patternsList = Arrays.asList(
                buildPattern(2, "%PATTERN%2%", category2, PatternType.REGULAR, PatternPriority.LOW),
                buildPattern(1, "%PATTERN%1%", category1),
                buildPattern(4, "%PATTERN%4%", category4, PatternType.REGULAR, PatternPriority.CRITICAL),
                buildPattern(3, "%PATTERN%3%", category3, PatternType.REGULAR, PatternPriority.HIGH)
        );

        doReturn(patternsList).when(categoriesRepositorySpy).getAllPatterns();

        importProcessorInstance.sortExpensesByCategories(Collections.emptyList());

        assertEquals(4, patternsList.get(0).getId());
        assertEquals(3, patternsList.get(1).getId());
        assertEquals(1, patternsList.get(2).getId());
        assertEquals(2, patternsList.get(3).getId());

        verify(categoriesRepositorySpy).getAllPatterns();
        verify(categoriesRepositorySpy).sortPatternsList(anyList());
    }

    @Test
    public void testSortExpensesByCategories_checkRegularExpensesCategorized() {

        Category category1 = new Category(20, "Fist Category", 1000.0);
        Category category2 = new Category(30, "Second Category", 1000.0);
        Category category3 = new Category(40, "Third Category", 1000.0);
        Category category4 = new Category(50, "Fourth Category", 1000.0);

        List<ImportPattern> patternsList = Arrays.asList(
                buildPattern(1, "%PATTERN%1%", category1),
                buildPattern(2, "%PATTERN%2%", category2, PatternType.REGULAR, PatternPriority.LOW),
                buildPattern(3, "%PATTERN%3%", category3, PatternType.REGULAR, PatternPriority.HIGH),
                buildPattern(4, "%PATTERN%4%", category4, PatternType.REGULAR, PatternPriority.CRITICAL)
        );
        doReturn(patternsList).when(categoriesRepositorySpy).getAllPatterns();

        List<Expense> expensesList = Arrays.asList(
                buildExpense(13, "Pattern 3 Expense", new Date(), 103, null),
                buildExpense(11, "Pattern 1 Expense", new Date(), 101, null),
                buildExpense(15, "Uncategorized Expense", new Date(), 105, null),
                buildExpense(14, "Pattern 4 Expense", new Date(), 104, null),
                buildExpense(12, "Pattern 2 Expense", new Date(), 102, null)
        );

        importProcessorInstance.sortExpensesByCategories(expensesList);

        assertEquals(category3, expensesList.get(0).getCategory());
        assertEquals(category1, expensesList.get(1).getCategory());
        assertEquals(unknownCategory, expensesList.get(2).getCategory());
        assertEquals(category4, expensesList.get(3).getCategory());
        assertEquals(category2, expensesList.get(4).getCategory());
    }

    @Test
    public void testSortExpensesByCategories_checkAmountBasedExpensesCategorized() {
        Category amountBasedCategory1 = new Category(20, "Amount-Based Category 1", 1000.0);
        Category amountBasedCategory2 = new Category(30, "Amount-Based Category 2", 1000.0);
        Category category1 = new Category(40, "Fist Category", 1000.0);

        double amount1 = 444;
        double amount2 = 555;
        List<ImportPattern> patternsList = Arrays.asList(
                buildPattern(1, "%TEST%PATTERN%", category1),
                buildPattern(2, "%TEST%PATTERN%", amountBasedCategory1, PatternType.AMOUNT, PatternPriority.CRITICAL, amount1),
                buildPattern(3, "%TEST%PATTERN%", amountBasedCategory2, PatternType.AMOUNT, PatternPriority.CRITICAL, amount2)
        );
        doReturn(patternsList).when(categoriesRepositorySpy).getAllPatterns();

        List<Expense> expensesList = Arrays.asList(
                buildExpense(11, "Test Regular Pattern Expense", new Date(), 123, null),
                buildExpense(12, "Test Amount-Based Pattern Expense", new Date(), amount1, null),
                buildExpense(13, "Test Amount-Based Pattern Expense", new Date(), amount2, null)
        );

        importProcessorInstance.sortExpensesByCategories(expensesList);

        assertEquals(category1, expensesList.get(0).getCategory());
        assertEquals(amountBasedCategory1, expensesList.get(1).getCategory());
        assertEquals(amountBasedCategory2, expensesList.get(2).getCategory());
    }

    private class TestableImportProcessor extends ImportProcessor {

        public TestableImportProcessor() {
            super();
        }

        public TestableImportProcessor(ExpensesRepository expensesRepository, CategoriesRepository categoriesRepository) {
            super(expensesRepository, categoriesRepository);
        }

        @Override
        public void importExpenses(String fileName, BankType bankType) {
            super.importExpenses(fileName, bankType);
        }

        @Override
        protected Collection<Expense> importExpensesFromFile(String fileName, BankType bankType) {
            return super.importExpensesFromFile(fileName, bankType);
        }

        @Override
        protected void sortExpensesByCategories(Collection<Expense> expenses) {
            super.sortExpensesByCategories(expenses);
        }

        @Override
        protected void storeExpenses(Collection<Expense> expenses) {
            super.storeExpenses(expenses);
        }
    }

}
