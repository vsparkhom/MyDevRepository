package vlpa.expman.dao.imprt.sql;

import vlpa.expman.dao.DBQueries;
import vlpa.expman.dao.connection.ConnectionManager;
import vlpa.expman.dao.imprt.ImportExpensesDAO;
import vlpa.expman.model.Category;
import vlpa.expman.model.ImportPattern;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ImportExpensesDAOImpl implements ImportExpensesDAO {

    @Override
    public List<ImportPattern> queryPatterns(String query) {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();

            List<ImportPattern> mapping = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                String text = rs.getString("pattern");
                long categoryId = rs.getLong("category_id");
                Category fakeCategory = new Category(categoryId, null, 0);
                mapping.add(new ImportPattern(id, text, fakeCategory));
            }
            return mapping;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public void addPattern(ImportPattern p) {
        System.out.println("Adding pattern: " + p);
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.ADD_PATTERN);
            pstm.setString(1, p.getText());
            pstm.setLong(2, p.getCategory().getId());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void removePattern(long id) {
        System.out.println("Removing pattern with id=" + id);
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.REMOVE_PATTERN);
            pstm.setLong(1, id);
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    public void updatePattern(ImportPattern pattern) {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.UPDATE_PATTERN);
            pstm.setString(1, pattern.getText());
            pstm.setLong(2, pattern.getCategory().getId());
            pstm.setLong(3, pattern.getId());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }
}
