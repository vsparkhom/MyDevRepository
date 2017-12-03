package vlpa.expman.model.dao;

import vlpa.expman.model.Category;

import java.util.Collection;

public interface CategoriesDAO {

    Collection<Category> getAllCategories();

    Category getCategoryById(int categoryId);

}
