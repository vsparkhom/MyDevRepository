package vlpa.expman.dao.imprt.sql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vlpa.expman.dao.DBQueries;
import vlpa.expman.dao.connection.ConnectionManager;
import vlpa.expman.dao.imprt.ImportPatternsDAO;
import vlpa.expman.model.Category;
import vlpa.expman.model.ImportPattern;
import vlpa.expman.model.PatternPriority;
import vlpa.expman.model.PatternType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ImportPatternsDAOImpl implements ImportPatternsDAO {

    private final Logger LOGGER = LoggerFactory.getLogger(ImportPatternsDAOImpl.class);

    @Override
    public List<ImportPattern> queryPatterns(String query) {
        LOGGER.debug("Running query: {}", query);
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
                long typeId = rs.getLong("type_id");
                PatternPriority priority = PatternPriority.getPatternPriorityById(rs.getLong("priority"));
                double amount = rs.getDouble("amount");
                Category fakeCategory = new Category(categoryId, null, 0);
                mapping.add(ImportPattern.builder()
                        .setId(id)
                        .setText(text)
                        .setCategory(fakeCategory)
                        .setType(PatternType.getPatternTypeById(typeId))
                        .setPriority(priority)
                        .setAmount(amount).build()
                );
            }
            return mapping;
        } catch (Exception e) {
            LOGGER.error("Query can't be executed due to error", e);
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public void addPattern(ImportPattern p) {
        LOGGER.info("Adding pattern: {}", p);
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.ADD_PATTERN);
            pstm.setString(1, p.getText());
            pstm.setLong(2, p.getCategory().getId());
            pstm.setLong(3, p.getType().getId());
            pstm.setLong(4, p.getPriority().getId());
            pstm.setDouble(5, p.getAmount());
            pstm.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Pattern can't be added due to error", e);
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void removePattern(long id) {
        LOGGER.info("Removing pattern with id: {}", id);
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.REMOVE_PATTERN);
            pstm.setLong(1, id);
            pstm.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Pattern can't be removed due to error", e);
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    public void updatePattern(ImportPattern pattern) {
        LOGGER.info("Updating pattern by id: {}", pattern);
        Connection conn = null;
        try {
            conn = ConnectionManager.getConnection();
            PreparedStatement pstm = conn.prepareStatement(DBQueries.SQLiteDBQueries.UPDATE_PATTERN);
            pstm.setString(1, pattern.getText());
            pstm.setLong(2, pattern.getCategory().getId());
            pstm.setLong(3, pattern.getType().getId());
            pstm.setLong(4, pattern.getPriority().getId());
            pstm.setDouble(5, pattern.getAmount());
            pstm.setLong(6, pattern.getId());
            pstm.executeUpdate();
        } catch (Exception e) {
            LOGGER.error("Pattern can't be updated due to error", e);
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }
}
