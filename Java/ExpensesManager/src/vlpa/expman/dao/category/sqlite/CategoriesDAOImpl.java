package vlpa.expman.dao.category.sqlite;

import vlpa.expman.dao.DBQueries;
import vlpa.expman.dao.category.CategoriesDAO;
import vlpa.expman.dao.connection.ConnectionManager;
import vlpa.expman.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class CategoriesDAOImpl implements CategoriesDAO {

    public List<Category> queryCategories(String sqlQuery) {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sqlQuery);
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
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return Collections.emptyList();
    }

    @Override
    public void addCategory(String name, double limit) {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.ADD_CATEGORY);
            pstm.setString(1, name);
            pstm.setDouble(2, limit);
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void removeCategory(long categoryId) {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.REMOVE_CATEGORY);
            pstm.setLong(1, categoryId);
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void updateCategory(Category category) {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.UPDATE_CATEGORY);
            pstm.setString(1, category.getName());
            pstm.setDouble(2, category.getLimit());
            pstm.setLong(3, category.getId());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

}
