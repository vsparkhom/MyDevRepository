package com.perscab.db.services;

import com.perscab.db.AttributeConsts;
import com.perscab.db.DBUtils;

import com.perscab.model.services.ServiceInstance;
import com.perscab.model.services.ServicePlan;
import com.perscab.model.services.ServiceType;
import com.perscab.model.services.TvServicePlan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public class ServiceStrategyHolder {

    private static ServiceStrategyHolder instance;

    private ServiceStrategyHolder() {
    }

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

    public ServiceStrategy getServiceStrategyByTypeId(long typeId) {
        if (typeId == AttributeConsts.INTERNET_SERVICE_TYPE_ID) {
            return getInternetServiceStrategy();
        } else if (typeId == AttributeConsts.TV_SERVICE_TYPE_ID) {
            return getTvServiceStrategy();
        } else if (typeId == AttributeConsts.PHONE_SERVICE_TYPE_ID) {
            return getPhoneServiceStrategy();
        }
        throw new RuntimeException("Service Strategy was not found for given typeId: " + typeId);
    }

    private class InternetServiceStrategy implements ServiceStrategy {

        @Override
        public ServiceInstance getService(Connection conn, long accountId) throws SQLException {
            ServiceInstance internetServiceInstance = DBUtils.getInternetServiceInstance(conn, accountId);
            System.out.println("InternetServiceStrategy.getService: [internetServiceInstance]:" + internetServiceInstance);
            ServiceType serviceType = new ServiceType(AttributeConsts.INTERNET_SERVICE_TYPE_ID, "Internet");
            return internetServiceInstance == null ? new ServiceInstance(serviceType) : internetServiceInstance;
        }

        @Override
        public Collection<ServicePlan> getPlans(Connection conn) throws SQLException {
            Collection<ServicePlan> servicePlans = DBUtils.getServicePlansByTypeId(conn, AttributeConsts.TV_SERVICE_TYPE_ID);
            System.out.println("InternetServiceStrategy.getPlans: [servicePlans]:" + servicePlans.size());
            for (ServicePlan sp : servicePlans) {
                System.out.println("InternetServiceStrategy.getPlans:    - [sp]:" + sp);
                sp.setOptions(DBUtils.getInternetOptionsForService(conn, sp.getId()));
            }
            return servicePlans;
        }
    }

    private class TvServiceStrategy implements ServiceStrategy {

        @Override
        public ServiceInstance getService(Connection conn, long accountId) throws SQLException {
            ServiceInstance tvServiceInstance = DBUtils.getTvServiceInstance(conn, accountId);
            System.out.println("TvServiceStrategy.getService: [tvServiceInstance]:" + tvServiceInstance);
            ServiceType serviceType = new ServiceType(AttributeConsts.TV_SERVICE_TYPE_ID, "TV");
            return tvServiceInstance == null ? new ServiceInstance(serviceType) : tvServiceInstance;
        }

        @Override
        public Collection<ServicePlan> getPlans(Connection conn) throws SQLException {
            Collection<ServicePlan> servicePlans = DBUtils.getServicePlansByTypeId(conn, AttributeConsts.TV_SERVICE_TYPE_ID);
            System.out.println("TvServiceStrategy.getPlans: [servicePlans]:" + servicePlans.size());
            for (ServicePlan sp : servicePlans) {
                System.out.println("TvServiceStrategy.getPlans:    - [sp]:" + sp);
                sp.setOptions(DBUtils.getTvOptionsForService(conn, sp.getId()));
            }
            return servicePlans;
        }
    }

    private class PhoneServiceStrategy implements ServiceStrategy {

        @Override
        public ServiceInstance getService(Connection conn, long accountId) throws SQLException {
            ServiceInstance phoneServiceInstance = DBUtils.getPhoneServiceInstance(conn, accountId);
            System.out.println("PhoneServiceStrategy.getService: [phoneServiceInstance]:" + phoneServiceInstance);
            ServiceType serviceType = new ServiceType(AttributeConsts.PHONE_SERVICE_TYPE_ID, "Phone");
            return phoneServiceInstance == null ? new ServiceInstance(serviceType) : phoneServiceInstance;
        }

        @Override
        public Collection<ServicePlan> getPlans(Connection conn) throws SQLException {
            Collection<ServicePlan> servicePlans = DBUtils.getServicePlansByTypeId(conn, AttributeConsts.PHONE_SERVICE_TYPE_ID);
            System.out.println("PhoneServiceStrategy.getPlans: [servicePlans]:" + servicePlans.size());
            for (ServicePlan sp : servicePlans) {
                System.out.println("PhoneServiceStrategy.getPlans:    - [sp]:" + sp);
                sp.setOptions(DBUtils.getPhoneOptionsForService(conn, sp.getId()));
            }
            return servicePlans;
        }
    }
}
