package com.perscab.controller;

import com.perscab.db.ConnectionUtils;
import com.perscab.db.DBUtils;
import com.perscab.db.services.ServiceStrategy;
import com.perscab.db.services.ServiceStrategyHolder;

import com.perscab.model.Account;
import com.perscab.model.Hardware;
import com.perscab.model.services.Service;
import com.perscab.model.services.ServiceInstance;
import com.perscab.model.services.ServicePlan;
import com.perscab.servlets.MyUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public class ServiceHelper {

    public static void initServices(HttpServletRequest request) throws IOException {
        Account account = MyUtils.getAuthorizedAccount(request.getSession());
        System.out.println("ServiceHelper.initServices: [account]:" + account);
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            initInternetService(request);
            initTvService(request);
            initPhoneService(request);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeQuietly(conn);
        }
    }

    public static void initHardware(HttpServletRequest request, long serviceTypeId) throws IOException {
        Account account = MyUtils.getAuthorizedAccount(request.getSession());
        System.out.println("ServiceHelper.initServices: [account]:" + account);
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            Collection<Hardware> serviceHardware = DBUtils.getHardwareByServiceType(conn, account.getId(), serviceTypeId);
            System.out.println("ServiceHelper.initServices: [serviceHardware]:" + serviceHardware);
            request.setAttribute("serviceHardware", serviceHardware);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeQuietly(conn);
        }
    }

    public static void initInternetService(HttpServletRequest request) throws IOException {
        Account account = MyUtils.getAuthorizedAccount(request.getSession());
        System.out.println("ServiceHelper.initInternetService for account:" + account);
        ServiceInstance service = getService(account.getId(), ServiceStrategyHolder.getInstance().getInternetServiceStrategy());
        request.setAttribute("internetService", service);

    }

    public static void initTvService(HttpServletRequest request) throws IOException {
        Account account = MyUtils.getAuthorizedAccount(request.getSession());
        System.out.println("ServiceHelper.initTvService for account:" + account);
        ServiceInstance service = getService(account.getId(), ServiceStrategyHolder.getInstance().getTvServiceStrategy());
        request.setAttribute("tvService", service);
    }

    public static void initPhoneService(HttpServletRequest request) throws IOException {
        Account account = MyUtils.getAuthorizedAccount(request.getSession());
        System.out.println("ServiceHelper.initPhoneService for account:" + account);
        ServiceInstance service = getService(account.getId(), ServiceStrategyHolder.getInstance().getPhoneServiceStrategy());
        request.setAttribute("phoneService", service);
    }

    public static ServiceInstance getService(long accountId, ServiceStrategy serviceStrategy) throws IOException {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            ServiceInstance service = serviceStrategy.getService(conn, accountId);
            return service;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeQuietly(conn);
        }
        return null;
    }

    public static void addService(long accountId, long serviceId) throws IOException {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            activateHardware(conn, accountId, serviceId);
            activateService(conn, accountId, serviceId);
            updateBilling(conn, accountId, serviceId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeQuietly(conn);
        }
    }

    private static void activateHardware(Connection conn, long accountId, long serviceId) throws IOException, SQLException {
        System.out.println("ServiceHelper.activateHardware: accountId: " + accountId + ", serviceId: " + serviceId);

        long hardwareId = DBUtils.findAvailableHardware(conn, serviceId);

        if (hardwareId != 0) {
            DBUtils.reserveHardware(conn, accountId, hardwareId);
        } else {
            throw new RuntimeException("Hardware can not be reserved for account " + accountId + " and service " + serviceId);
        }
    }

    private static void activateService(Connection conn, long accountId, long serviceId) throws SQLException {
        System.out.println("ServiceHelper.activateService: accountId: " + accountId + ", serviceId: " + serviceId);
        DBUtils.activateServiceForAccount(conn, accountId, serviceId);
    }

    public static void removeService(long accountId, long serviceId) throws IOException {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            deactivateHardware(conn, accountId, serviceId);
            deactivateService(conn, accountId, serviceId);
            updateBilling(conn, accountId, serviceId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeQuietly(conn);
        }
    }

    private static void deactivateHardware(Connection conn, long accountId, long serviceId) throws IOException, SQLException {
        System.out.println("ServiceHelper.deactivateHardware: accountId: " + accountId + ", serviceId: " + serviceId);
        DBUtils.deactivateHardware(conn, accountId, serviceId);
    }

    private static void deactivateService(Connection conn, long accountId, long serviceId) throws IOException, SQLException {
        System.out.println("ServiceHelper.deactivateHardware: accountId: " + accountId + ", serviceId: " + serviceId);
        DBUtils.deactivateService(conn, accountId, serviceId);
    }

    private static void updateBilling(Connection conn, long accountId, long serviceId) {
        System.out.println("ServiceHelper.updateBilling: accountId: " + accountId + ", serviceId: " + serviceId);
    }

    public static void initServicePlans(HttpServletRequest request, ServiceStrategy strategy) throws IOException {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            Collection<ServicePlan> servicePlans = strategy.getPlans(conn);

            request.setAttribute("servicePlans", servicePlans);
            request.setAttribute("columnWidth", 12/servicePlans.size());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeQuietly(conn);
        }
    }
}
