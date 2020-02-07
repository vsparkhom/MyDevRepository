package vlpa.expman.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static ConnectionTypeEnum currentConnectionType = ConnectionTypeEnum.SQLITE;

    public static Connection getConnection() throws Exception {
        ConnectionType typeInstance = currentConnectionType.getInstance();
        Class.forName(typeInstance.getDriverClassName());
        return DriverManager.getConnection(typeInstance.getUrl());
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public static void setConnectionType(ConnectionTypeEnum t) {
        currentConnectionType = t;
    }

    public static ConnectionTypeEnum getConnectionType() {
        return currentConnectionType;
    }
}
