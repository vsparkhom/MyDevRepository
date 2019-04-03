package vlpa.expman.dao.category.sqlite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vlpa.expman.dao.DBQueries;
import vlpa.expman.dao.category.CategoriesDAO;
import vlpa.expman.dao.connection.ConnectionManager;
import vlpa.expman.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class CategoriesDAOImpl implements CategoriesDAO {

    private final Logger LOGGER = LoggerFactory.getLogger(CategoriesDAOImpl.class);

    public List<Category> queryCategories(String query) {
        LOGGER.debug("Running query: {}", query);
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            List<Category> categories = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                double limit = rs.getDouble("limit");

                Category c = new Category(id, name, limit);
                categories.add(c);
            }
            return categories;
        } catch (Exception e) {
            LOGGER.error("Query can't be executed due to error", e);
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return Collections.emptyList();
    }

    @Override
    public void addCategory(Category category) {
        LOGGER.debug("Adding category: {}", category);
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.ADD_CATEGORY);
            pstm.setString(1, category.getName());
            pstm.setDouble(2, category.getLimit());
            pstm.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Category can't be added due to error", e);
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void removeCategory(long categoryId) {
        LOGGER.debug("Removing category with id {}", categoryId);
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.REMOVE_CATEGORY);
            pstm.setLong(1, categoryId);
            pstm.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Category can't be removed due to error", e);
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void updateCategory(Category category) {
        LOGGER.debug("Updating category: {}", category);
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.UPDATE_CATEGORY);
            pstm.setString(1, category.getName());
            pstm.setDouble(2, category.getLimit());
            pstm.setLong(3, category.getId());
            pstm.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Category can't be updated due to error", e);
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

}
