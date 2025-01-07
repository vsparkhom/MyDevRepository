package com.vlpa.spring.expenseimporter.dao.category;

import com.vlpa.spring.expenseimporter.dao.SqlQueries;
import com.vlpa.spring.expenseimporter.dao.connection.ConnectionManager;
import com.vlpa.spring.expenseimporter.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.*;

import static com.vlpa.spring.expenseimporter.LoggerUtils.*;

public class CategoriesDAOImpl implements CategoriesDAO {

    @Override
    public void saveCategories(List<Category> categories) {
        info("Number of categories to add: " + categories.size());
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(SqlQueries.ADD_CATEGORY);

            for (Category category : categories) {
                debug("Import of " + category);
                pstm.setLong(1, category.getId());
                pstm.setString(2, category.getName());

                if (category.getParentCategory() != null) {
                    pstm.setDouble(3, category.getParentCategory().getId());
                }

                if (category.getTopCategory() != null) {
                    pstm.setString(4, category.getTopCategory().name());
                }
                pstm.executeUpdate();
            }
            info("Categories were successfully imported to database");
        } catch (Exception e) {
            error("Category can't be added due to error: " + e);
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public List<Category> readCategories() {
        return null;
    }

    @Override
    public void removeAllCategories() {
        info("Start removal of all categories");
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(SqlQueries.REMOVE_ALL_CATEGORIES);
            pstm.executeUpdate();
            info("All categories have been removed successfully");
        } catch (Exception e) {
            error("Category can't be removed due to error:" + e);
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    //    public List<Category> queryCategories(String query) {
//        LOGGER.debug("Running query: {}", query);
//        Connection conn = null;
//        try {
//            conn = ConnectionManager.getConnection();
//            PreparedStatement pstm = conn.prepareStatement(query);
//            ResultSet rs = pstm.executeQuery();
//
//            List<Category> categories = new ArrayList<>();
//            while (rs.next()) {
//                long id = rs.getLong("id");
//                String name = rs.getString("name");
//                double limit = rs.getDouble("limit");
//
//                Category c = new Category(id, name, limit);
//                categories.add(c);
//            }
//            return categories;
//        } catch (Exception e) {
//            error("Query can't be executed due to error", e);
//        } finally {
//            ConnectionManager.closeConnection(conn);
//        }
//        return Collections.emptyList();
//    }
//
//    @Override
//    public void addCategory(Category category) {
//        LOGGER.debug("Adding category: {}", category);
//        Connection conn = null;
//        try {
//            conn = ConnectionManager.getConnection();
//            PreparedStatement pstm = conn.prepareStatement(SqlQueries.SQLiteDBQueries.ADD_CATEGORY);
//            pstm.setString(1, category.getName());
//            pstm.setDouble(2, category.getLimit());
//            pstm.executeUpdate();
//        } catch (Exception e) {
//            LOGGER.error("Category can't be added due to error", e);
//        } finally {
//            ConnectionManager.closeConnection(conn);
//        }
//    }
//
//    @Override
//    public void removeCategory(long categoryId) {
//        LOGGER.info("Removing category with id {}", categoryId);
//        Connection conn = null;
//        try {
//            conn = ConnectionManager.getConnection();
//            PreparedStatement pstm = conn.prepareStatement(SqlQueries.SQLiteDBQueries.REMOVE_ALL_CATEGORIES);
//            pstm.setLong(1, categoryId);
//            pstm.executeUpdate();
//        } catch (Exception e) {
//            LOGGER.error("Category can't be removed due to error", e);
//        } finally {
//            ConnectionManager.closeConnection(conn);
//        }
//    }
//
//    @Override
//    public void updateCategory(Category category) {
//        LOGGER.info("Updating category: {}", category);
//        Connection conn = null;
//        try {
//            conn = ConnectionManager.getConnection();
//            PreparedStatement pstm = conn.prepareStatement(SqlQueries.SQLiteDBQueries.UPDATE_CATEGORY);
//            pstm.setString(1, category.getName());
//            pstm.setDouble(2, category.getLimit());
//            pstm.setLong(3, category.getId());
//            pstm.executeUpdate();
//        } catch (Exception e) {
//            LOGGER.error("Category can't be updated due to error", e);
//        } finally {
//            ConnectionManager.closeConnection(conn);
//        }
//    }

}
