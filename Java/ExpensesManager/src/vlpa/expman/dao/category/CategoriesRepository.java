package vlpa.expman.dao.category;

import vlpa.expman.dao.ExpenseManagerDAOFactory;
import vlpa.expman.dao.category.spec.CategorySqlSpecificationGetAll;
import vlpa.expman.dao.category.spec.CategorySqlSpecificationGetById;
import vlpa.expman.dao.imprt.ImportExpensesDAO;
import vlpa.expman.dao.imprt.spec.PatternSqlSpecificationGetAll;
import vlpa.expman.model.Category;
import vlpa.expman.model.ImportPattern;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CategoriesRepository {

    private CategoriesDAO categoriesDAO = ExpenseManagerDAOFactory.CategoriesDAOFactory.getInstance();
    private ImportExpensesDAO importExpensesDAO = ExpenseManagerDAOFactory.ImportExpensesDAOFactory.getInstance();

    public Category getCategoryById(long id) {
        return categoriesDAO.queryCategories(new CategorySqlSpecificationGetById(id).toSqlClause()).get(0);
    }

    public List<Category> getAllCategories() {
        return categoriesDAO.queryCategories(new CategorySqlSpecificationGetAll().toSqlClause());
    }

    public double getLimitForAllCategories() {
        double totalLimit = 0;
        for (Category c : getAllCategories()) {
            totalLimit += c.getLimit();
        }
        return totalLimit;
    }

    public Category getUnknownCategory() {
        for (Category c : getAllCategories()) {
            if ("Unknown".equals(c.getName())) {
                return c;
            }
        }
        return null;
    }

    public void addCategory(Category category) {
        categoriesDAO.addCategory(category.getName(), category.getLimit());
    }

    public void removeCategory(long categoryId) {
        categoriesDAO.removeCategory(categoryId);
    }

    public void updateCategory(Category category) {
        categoriesDAO.updateCategory(category);
    }

    public List<ImportPattern> getAllPatterns() {
        List<ImportPattern> patternCategoryIdMapping = importExpensesDAO.queryPatterns(
                new PatternSqlSpecificationGetAll().toSqlClause());
        initCategoriesForImportPatterns(patternCategoryIdMapping);
        return patternCategoryIdMapping;
    }

    public void sortPatternsList(List<ImportPattern> patternCategoryIdMapping) {
        Collections.sort(patternCategoryIdMapping, new Comparator<ImportPattern>() {
            @Override
            public int compare(ImportPattern p1, ImportPattern p2) {
                long id1 = p1.getPriority().getId();
                long id2 = p2.getPriority().getId();
                return id1 > id2 ? -1 : (id1 < id2) ? 1 : 0;
            }
        });
    }

    private void initCategoriesForImportPatterns(List<ImportPattern> patternCategoryIdMapping) {
        for (ImportPattern p : patternCategoryIdMapping) {
            Category patternCategory = p.getCategory();
            long categoryId = patternCategory.getId();
            Category updatedCategory = getCategoryById(categoryId);
            patternCategory.setName(updatedCategory.getName());
            patternCategory.setLimit(updatedCategory.getLimit());
        }
    }

    public void addPattern(ImportPattern pattern) {
        importExpensesDAO.addPattern(pattern);
    }

    public void removePattern(long id) {
        importExpensesDAO.removePattern(id);
    }

    public void updatePattern(ImportPattern pattern) {
        importExpensesDAO.updatePattern(pattern);
    }
}
