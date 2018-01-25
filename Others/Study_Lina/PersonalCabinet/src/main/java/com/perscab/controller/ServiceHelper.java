package com.perscab.controller;

import com.perscab.db.AttributeConsts;
import com.perscab.db.ConnectionUtils;
import com.perscab.db.DBUtils;
import com.perscab.model.Account;
import com.perscab.model.ServiceInstance;
import com.perscab.servlets.MyUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ServiceHelper {

    public static void initServices(HttpServletRequest request) throws IOException {
        Account account = MyUtils.getAuthorizedAccount(request.getSession());
        System.out.println("ServiceHelper.initServices: [account]:" + account);
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();

            //Internet
            ServiceInstance internetService = getInternetService(conn, account);
            System.out.println("ServiceHelper.initServices: [internetService]:" + internetService);
            request.setAttribute("internetService", internetService);

            //TV
            ServiceInstance tvService = getTVService(conn, account);
            System.out.println("ServiceHelper.initServices: [tvService]:" + tvService);
            request.setAttribute("tvService", tvService);

            //Phone
            ServiceInstance phoneService = getPhoneService(conn, account);
            System.out.println("ServiceHelper.initServices: [phoneService]:" + phoneService);
            request.setAttribute("phoneService", phoneService);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeQuietly(conn);
        }
    }

    private static ServiceInstance getInternetService(Connection conn, Account account) throws SQLException {
        ServiceInstance internetServiceInstance = DBUtils.getServiceInstanceByType(conn, account.getLogin(),
                AttributeConsts.INTERNET_SERVICE_TYPE_ID);
        System.out.println("ServiceHelper.getInternetService: [internetServiceInstance]:" + internetServiceInstance);
        return internetServiceInstance == null ? new ServiceInstance("Internet") : internetServiceInstance;
    }

    private static ServiceInstance getTVService(Connection conn, Account account) throws SQLException {
        ServiceInstance tvServiceInstance = DBUtils.getServiceInstanceByType(conn, account.getLogin(),
                AttributeConsts.TV_SERVICE_TYPE_ID);
        System.out.println("ServiceHelper.getTVService: [tvServiceInstance]:" + tvServiceInstance);
        return tvServiceInstance == null ? new ServiceInstance("TV") : tvServiceInstance;
    }

    private static ServiceInstance getPhoneService(Connection conn, Account account) throws SQLException {
        ServiceInstance phoneServiceInstance = DBUtils.getServiceInstanceByType(conn, account.getLogin(),
                AttributeConsts.PHONE_SERVICE_TYPE_ID);
        System.out.println("ServiceHelper.getPhoneService: [phoneServiceInstance]:" + phoneServiceInstance);
        return phoneServiceInstance == null ? new ServiceInstance("Phone") : phoneServiceInstance;
    }
}
