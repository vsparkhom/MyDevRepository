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
    public List<ImportPattern> getMapping() {
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.GET_EXPENSES_MAPPING);
            ResultSet rs = pstm.executeQuery();

            List<ImportPattern> mapping = new ArrayList<>();
            while (rs.next()) {
                String pattern = rs.getString("pattern");
                long categoryId = rs.getLong("category_id");
                Category fakeCategory = new Category(categoryId, null, 0);
                mapping.add(new ImportPattern(pattern, fakeCategory));
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
}
