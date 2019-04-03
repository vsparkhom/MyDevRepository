package vlpa.expman.dao.category;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import vlpa.expman.model.Category;
import vlpa.expman.model.ImportPattern;
import vlpa.expman.model.PatternPriority;
import vlpa.expman.model.PatternType;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;

public class CategoriesRepositoryTest {

    private CategoriesRepository categoriesRepositorySpy;

    @Before
    public void setUp() {
        categoriesRepositorySpy = spy(new CategoriesRepository());
    }

    @Test
    public void testSortPatternsList() {
        List<ImportPattern> patternsList = Arrays.asList(
                new ImportPattern(1, "Pattern 1", new Category(10, "Fist Category", 100.0), PatternType.REGULAR),
                new ImportPattern(2, "Pattern 2", new Category(20, "Second Category", 100.0), PatternType.REGULAR, PatternPriority.LOW, 0),
                new ImportPattern(3, "Pattern 3", new Category(30, "Third Category", 100.0), PatternType.REGULAR, PatternPriority.HIGH, 0),
                new ImportPattern(4, "Pattern 4", new Category(40, "Fourth Category", 100.0), PatternType.REGULAR, PatternPriority.CRITICAL, 0),
                new ImportPattern(5, "Pattern 5", new Category(50, "Fifth Category", 100.0), PatternType.REGULAR, PatternPriority.LOW, 0)
        );

        categoriesRepositorySpy.sortPatternsList(patternsList);

        assertEquals(PatternPriority.CRITICAL, patternsList.get(0).getPriority());
        assertEquals(PatternPriority.HIGH, patternsList.get(1).getPriority());
        assertEquals(PatternPriority.MEDIUM, patternsList.get(2).getPriority());
        assertEquals(PatternPriority.LOW, patternsList.get(3).getPriority());
        assertEquals(PatternPriority.LOW, patternsList.get(4).getPriority());
    }
}
