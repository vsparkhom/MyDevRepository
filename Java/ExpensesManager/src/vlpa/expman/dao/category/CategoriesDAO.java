package vlpa.expman.dao.category;

import vlpa.expman.model.Category;

import java.util.List;

public interface CategoriesDAO {

    void addCategory(Category category);

    void removeCategory(long categoryId);

    void updateCategory(Category category);

    List<Category> queryCategories(String sqlQuery);

}
