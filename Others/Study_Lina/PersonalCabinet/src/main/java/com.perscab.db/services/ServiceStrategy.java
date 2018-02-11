package com.perscab.db.services;

import com.perscab.model.services.ServiceInstance;
import com.perscab.model.services.ServicePlan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public interface ServiceStrategy {

    ServiceInstance getService(Connection conn, long accountId) throws SQLException;

    Collection<ServicePlan> getPlans(Connection conn) throws SQLException;
}
