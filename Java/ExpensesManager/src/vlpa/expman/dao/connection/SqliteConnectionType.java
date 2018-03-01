package vlpa.expman.dao.connection;

public class SqliteConnectionType implements ConnectionType {

    @Override
    public String getDriverClassName() {
        return "org.sqlite.JDBC";
    }

    @Override
    public String getUrl() {
        return "jdbc:sqlite:res/expman.db";
    }
}
