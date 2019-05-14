package vlpa.expman.dao.connection;

public class SQLiteConnectionType implements ConnectionType {

    private final String TEST_DB_PATH = "jdbc:sqlite:res/test/expman.db";
    private final String PRODUCTION_DB_PATH = "jdbc:sqlite:res/production/expman.db";

    @Override
    public String getDriverClassName() {
        return "org.sqlite.JDBC";
    }

    @Override
    public String getUrl() {
        return PRODUCTION_DB_PATH;
    }
}
