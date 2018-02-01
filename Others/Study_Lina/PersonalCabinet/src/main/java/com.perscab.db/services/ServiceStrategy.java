package com.perscab.db.services;

import com.perscab.model.Account;
import com.perscab.model.services.ServiceInstance;

import java.sql.Connection;
import java.sql.SQLException;

public interface ServiceStrategy {

    ServiceInstance getService(Connection conn, Account account) throws SQLException;
}
