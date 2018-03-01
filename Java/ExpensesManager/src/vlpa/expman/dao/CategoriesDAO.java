package vlpa.expman.dao;

import vlpa.expman.model.Category;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public interface CategoriesDAO {

    Collection<Category> getAllCategories(Connection conn) throws SQLException;

    Category getCategoryById(Connection conn, long categoryId) throws SQLException;

}
