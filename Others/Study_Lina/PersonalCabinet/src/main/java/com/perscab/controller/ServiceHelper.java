package com.perscab.controller;

import com.perscab.db.AttributeConsts;
import com.perscab.db.ConnectionUtils;
import com.perscab.db.DBUtils;
import com.perscab.db.services.ServiceStrategy;
import com.perscab.db.services.ServiceStrategyHolder;

import com.perscab.model.Account;
import com.perscab.model.Hardware;
import com.perscab.model.services.InternetServiceInstance;
import com.perscab.model.services.PhoneServiceInstance;
import com.perscab.model.services.ServiceInstance;
import com.perscab.model.services.TvServiceInstance;
import com.perscab.servlets.MyUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ServiceHelper {

    //TODO: remove unused consts
//    public static final String INTERNET_SERVICE_TYPE_NAME = "internetService";
//    public static final String TV_SERVICE_TYPE_NAME = "tvService";
//    public static final String PHONE_SERVICE_TYPE_NAME = "phoneService";

    public static void initServices(HttpServletRequest request) throws IOException {
        Account account = MyUtils.getAuthorizedAccount(request.getSession());
        System.out.println("ServiceHelper.initServices: [account]:" + account);
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();

            //Internet
            ServiceInstance internetService = getServiceInstance(conn, account, AttributeConsts.INTERNET_SERVICE_TYPE_ID, "Internet");
            System.out.println("ServiceHelper.initServices: [internetService]:" + internetService);
            request.setAttribute("internetService", internetService);

            //TV
            ServiceInstance tvService = getServiceInstance(conn, account, AttributeConsts.TV_SERVICE_TYPE_ID, "TV");
            System.out.println("ServiceHelper.initServices: [tvService]:" + tvService);
            request.setAttribute("tvService", tvService);

            //Phone
            ServiceInstance phoneService = getServiceInstance(conn, account, AttributeConsts.PHONE_SERVICE_TYPE_ID, "Phone");
            System.out.println("ServiceHelper.initServices: [phoneService]:" + phoneService);
            request.setAttribute("phoneService", phoneService);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeQuietly(conn);
        }
    }

    public static void initHardware(HttpServletRequest request, BigInteger serviceTypeId) throws IOException {
        Account account = MyUtils.getAuthorizedAccount(request.getSession());
        System.out.println("ServiceHelper.initServices: [account]:" + account);
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();

            List<Hardware> serviceHardware = DBUtils.getHardwareByServiceType(conn, account.getId(), serviceTypeId);
            System.out.println("ServiceHelper.initServices: [serviceHardware]:" + serviceHardware);
            request.setAttribute("serviceHardware", serviceHardware);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeQuietly(conn);
        }
    }

    public static void initInternetService(HttpServletRequest request) throws IOException {
        initService(request, ServiceStrategyHolder.getInstance().getInternetServiceStrategy());
    }

    public static void initTvService(HttpServletRequest request) throws IOException {
        initService(request, ServiceStrategyHolder.getInstance().getTvServiceStrategy());
    }

    public static void initPhoneService(HttpServletRequest request) throws IOException {
        initService(request, ServiceStrategyHolder.getInstance().getPhoneServiceStrategy());
    }

    public static void initService(HttpServletRequest request, ServiceStrategy serviceStrategy) throws IOException {
        Account account = MyUtils.getAuthorizedAccount(request.getSession());
        System.out.println("ServiceHelper.initService for account:" + account);
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            ServiceInstance service = serviceStrategy.getService(conn, account);
            request.setAttribute("service", service);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeQuietly(conn);
        }
    }

    //TODO: replace with ServiceStrategy
    private static ServiceInstance getServiceInstance(Connection conn, Account account, BigInteger serviceTypeId,
                                                      String defaultTitle) throws SQLException {
        ServiceInstance serviceInstance = DBUtils.getServiceInstanceByType(conn, account.getLogin(), serviceTypeId);
        System.out.println("ServiceHelper.getServiceInstance: [serviceInstance]:" + serviceInstance);
        return serviceInstance == null ? new ServiceInstance(defaultTitle) : serviceInstance;
    }

}
