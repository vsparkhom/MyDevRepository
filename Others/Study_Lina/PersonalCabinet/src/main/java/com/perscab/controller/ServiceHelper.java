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

    public static void addService(long accountId, long serviceTypeId) throws IOException {
        activateHardware(accountId, serviceTypeId);
        activateService(accountId, serviceTypeId);
        updateBilling(accountId, serviceTypeId);
    }

    private static void activateHardware(long accountId, long serviceTypeId) throws IOException {
        //TODO: implement logic
        System.out.println("activateHardware: DO NOTHING");
    }

    private static void activateService(long accountId, long serviceTypeId) {
        //TODO: implement logic
        System.out.println("activateService: DO NOTHING");
    }

    public static void removeService(long accountId, long serviceTypeId) throws IOException {
        deactivateHardware(accountId, serviceTypeId);
        deactivateService(accountId, serviceTypeId);
        updateBilling(accountId, serviceTypeId);
    }

    private static void deactivateHardware(long accountId, long serviceTypeId) throws IOException {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            DBUtils.deactivateHardware(conn, accountId, serviceTypeId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeQuietly(conn);
        }
    }

    private static void deactivateService(long accountId, long serviceTypeId) throws IOException {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            DBUtils.deactivateService(conn, accountId, serviceTypeId);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeQuietly(conn);
        }
    }

    private static void updateBilling(long accountId, long serviceTypeId) {
        //TODO: implement logic
        System.out.println("updateBilling: DO NOTHING");
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
