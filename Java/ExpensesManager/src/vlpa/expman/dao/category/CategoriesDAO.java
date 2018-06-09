package vlpa.expman.dao.category;

import vlpa.expman.model.Category;

import java.util.List;

public interface CategoriesDAO {

    //TODO: add CRUD methods

    List<Category> queryCategories(String sqlQuery);
}
