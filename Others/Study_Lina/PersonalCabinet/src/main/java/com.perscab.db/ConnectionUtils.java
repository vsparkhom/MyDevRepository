package com.perscab.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {

    public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
        return OracleConnectionUtils.getOracleConnection();
    }

    public static void closeQuietly(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
        }
    }

    public static void rollbackQuietly(Connection conn) {
        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (Exception e) {
        }
    }
}