package com.perscab.db.services;

import com.perscab.db.DBUtils;
import com.perscab.model.Account;
import com.perscab.model.services.InternetServiceInstance;
import com.perscab.model.services.PhoneServiceInstance;
import com.perscab.model.services.ServiceInstance;
import com.perscab.model.services.TvServiceInstance;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceStrategyHolder {

    private static ServiceStrategyHolder instance;

    private ServiceStrategyHolder() {}

    public static ServiceStrategyHolder getInstance() {
        if (instance == null) {
            instance = new ServiceStrategyHolder();
        }
        return instance;
    }

    public ServiceStrategy getInternetServiceStrategy() {
        return new InternetServiceStrategy();
    }

    public ServiceStrategy getTvServiceStrategy() {
        return new TvServiceStrategy();
    }

    public ServiceStrategy getPhoneServiceStrategy() {
        return new PhoneServiceStrategy();
    }

    private class InternetServiceStrategy implements ServiceStrategy {

        @Override
        public ServiceInstance getService(Connection conn, Account account) throws SQLException {
            InternetServiceInstance internetServiceInstance = DBUtils.getInternetServiceInstance(conn, account.getId());
            System.out.println("ServiceHelper.getInternetServiceInstance: [internetServiceInstance]:" + internetServiceInstance);
            return internetServiceInstance;
        }
    }

    private class TvServiceStrategy implements ServiceStrategy {

        @Override
        public ServiceInstance getService(Connection conn, Account account) throws SQLException {
            TvServiceInstance tvServiceInstance = DBUtils.getTvServiceInstance(conn, account.getId());
            System.out.println("ServiceHelper.getTvServiceInstance: [tvServiceInstance]:" + tvServiceInstance);
            return tvServiceInstance;
        }
    }

    private class PhoneServiceStrategy implements ServiceStrategy {

        @Override
        public ServiceInstance getService(Connection conn, Account account) throws SQLException {
            PhoneServiceInstance phoneServiceInstance = DBUtils.getPhoneServiceInstance(conn, account.getId());
            System.out.println("ServiceHelper.getPhoneServiceInstance: [phoneServiceInstance]:" + phoneServiceInstance);
            return phoneServiceInstance;
        }
    }
}
