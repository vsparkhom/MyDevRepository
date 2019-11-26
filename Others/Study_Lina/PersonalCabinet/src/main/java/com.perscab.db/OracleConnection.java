package com.perscab.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OracleConnection implements DBConnection {

    private static final String HOST = "db.oracle.host";
    private static final String SID = "db.oracle.sid";
    private static final String PORT = "db.oracle.port";
    private static final String USER = "db.oracle.user";
    private static final String PASSWORD = "db.oracle.password";
    private static final String DB_PROPERTIES_FILE_PATH = "db.properties";

    @Override
    public Connection getDBConnection() throws ClassNotFoundException, SQLException, IOException {

        Properties prop = new Properties();
        try(InputStream input = OracleConnection.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE_PATH)) {
            prop.load(input);
        }

        String hostName = prop.getProperty(HOST);
        String sid = prop.getProperty(SID);
        String port = prop.getProperty(PORT);
        String userName = prop.getProperty(USER);
        String password = prop.getProperty(PASSWORD);

        return getDBConnection(hostName, port, sid, userName, password);
    }

    public static Connection getDBConnection(String hostName, String port, String sid,
                                             String userName, String password) throws ClassNotFoundException, SQLException {

        Class.forName("oracle.jdbc.driver.OracleDriver");
        // jdbc:oracle:thin:@localhost:1521:db11g
        // jdbc:oracle:thin:@//HOSTNAME:PORT/SERVICENAME
        String connectionURL = "jdbc:oracle:thin:@" + hostName + ":" + port + ":" + sid;
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }
}