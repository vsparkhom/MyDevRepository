package vlpa.spring.expman.dao;

import vlpa.spring.expman.entity.Category;

import java.util.List;

public interface CategoryDAO {

    List<Category> getAllCategories();

    Category getCategoryById(int categoryId);

    void saveCategory(Category c);

    void removeCategory(Category c);
}
