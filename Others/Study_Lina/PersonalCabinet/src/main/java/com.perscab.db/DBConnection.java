package com.perscab.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnection {

    Connection getDBConnection() throws ClassNotFoundException, SQLException, IOException;

}
