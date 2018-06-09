package vlpa.expman.dao.category.sqlite;

import vlpa.expman.dao.category.CategoriesDAO;
import vlpa.expman.dao.connection.ConnectionManager;
import vlpa.expman.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
}
