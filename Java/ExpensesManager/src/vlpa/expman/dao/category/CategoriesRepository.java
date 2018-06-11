package vlpa.expman.dao.category;

import vlpa.expman.dao.DBQueries;
import vlpa.expman.dao.category.spec.CategorySqlSpecificationGetAll;
import vlpa.expman.dao.category.spec.CategorySqlSpecificationGetById;
import vlpa.expman.dao.category.sqlite.CategoriesDAOImpl;
import vlpa.expman.model.Category;

import java.util.List;

public class CategoriesRepository {

    private CategoriesDAO categoriesDAO = new CategoriesDAOImpl();

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

    public void addCategory(String name, double limit) {
        categoriesDAO.addCategory(name, limit);
    }

    public void removeCategory(long categoryId) {
        categoriesDAO.removeCategory(categoryId);
    }

    public void updateCategory(Category category) {
        categoriesDAO.updateCategory(category);
    }
}
