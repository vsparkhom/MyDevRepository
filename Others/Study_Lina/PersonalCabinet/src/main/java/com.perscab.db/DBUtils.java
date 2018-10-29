package com.perscab.db;

import com.perscab.model.Account;
import com.perscab.model.AccountStatus;
import com.perscab.model.Hardware;
import com.perscab.model.Payment;
import com.perscab.model.services.InternetServicePlan;
import com.perscab.model.services.PhoneServicePlan;
import com.perscab.model.services.ServiceInstance;
import com.perscab.model.services.ServicePlan;
import com.perscab.model.services.ServiceType;
import com.perscab.model.services.TvServicePlan;
import com.perscab.model.support.SupportServiceInfo;
import com.perscab.model.support.SupportSocialInfo;

import static com.perscab.db.DBQueries.*;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class DBUtils {

    public static Account findAccount(Connection conn, String login) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GET_ACCOUNT_BY_USER_LOGIN);
        pstm.setString(1, login);
        return getAccount(pstm);
    }

    public static Account findAccount(Connection conn, String login, String password) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GET_ACCOUNT_BY_USER_LOGIN_AND_PASS);
        pstm.setString(1, login);
        pstm.setString(2, password);
        return getAccount(pstm);
    }

    public static ServiceInstance getInternetServiceInstance(Connection conn, long accountId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GET_INTERNET_SERVICE_INSTANCE_FOR_ACCOUNT);
        pstm.setLong(1, accountId);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            long id = rs.getLong("service_id");
            String name = rs.getString("service_name");
            long typeId = rs.getLong("type_id");
            String typeName = rs.getString("type_name");
            double price = rs.getDouble("price");
            String downloadSpeed = rs.getString("download_speed");
            String uploadSpeed = rs.getString("upload_speed");
            String dataLimit = rs.getString("data_limit");

            InternetServicePlan servicePlan = new InternetServicePlan(id, name, new ServiceType(typeId, typeName), price);
            servicePlan.setDownloadSpeed(downloadSpeed);
            servicePlan.setUploadSpeed(uploadSpeed);
            servicePlan.setDataLimit(dataLimit);

            ServiceInstance serviceInstance = new ServiceInstance(servicePlan, "Active");

            return serviceInstance;
        }

        return null;
    }

    public static ServiceInstance getTvServiceInstance(Connection conn, long accountId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GET_TV_SERVICE_INSTANCE_FOR_ACCOUNT);
        pstm.setLong(1, accountId);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            long id = rs.getLong("service_id");
            String name = rs.getString("service_name");
            long typeId = rs.getLong("type_id");
            String typeName = rs.getString("type_name");
            double price = rs.getDouble("price");
            int channelsCount = rs.getInt("channels_count");
            String uhdSupport = rs.getString("uhd_support");

            TvServicePlan tvServicePlan = new TvServicePlan(id, name, new ServiceType(typeId, typeName), price);
            tvServicePlan.setChannelsCount(channelsCount);
            tvServicePlan.setUhdSupport(uhdSupport);

            ServiceInstance serviceInstance = new ServiceInstance(tvServicePlan, "Active");

            return serviceInstance;
        }

        return null;
    }

    public static ServiceInstance getPhoneServiceInstance(Connection conn, long accountId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GET_PHONE_SERVICE_INSTANCE_FOR_ACCOUNT);
        pstm.setLong(1, accountId);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            long id = rs.getLong("service_id");
            String name = rs.getString("service_name");
            long typeId = rs.getLong("type_id");
            String typeName = rs.getString("type_name");
            double price = rs.getDouble("price");
            int talkLimit = rs.getInt("talk_limit");
            int dataLimit = rs.getInt("data_limit");
            String voiceMail = rs.getString("voice_mail");

            PhoneServicePlan phoneServicePlan = new PhoneServicePlan(id, name, new ServiceType(typeId, typeName), price);
            phoneServicePlan.setTalkLimit(talkLimit);
            phoneServicePlan.setDataLimit(dataLimit);
            phoneServicePlan.setVoiceMail("On".equals(voiceMail));

            ServiceInstance serviceInstance = new ServiceInstance(phoneServicePlan, "Active");

            return serviceInstance;
        }

        return null;
    }



    public static double calcCurrentBallance(Connection conn, long accountId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GET_CURRENT_BALLANCE);
        pstm.setLong(1, accountId);
        ResultSet rs = pstm.executeQuery();

        double currentBallance = 0;
        if (rs.next()) {
            currentBallance = rs.getDouble("summa");
        }

        return currentBallance;
    }

    public static Date getPaymentDueDate(Connection conn, long accountId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GET_PAYMENT_DUE_DATE);
        pstm.setLong(1, accountId);
        ResultSet rs = pstm.executeQuery();

        Date dueDate = null;
        if (rs.next()) {
            dueDate = rs.getDate("due_date");
        }

        return dueDate;
    }

    public static Collection<Payment> getPayments(Connection conn, long accountId) throws SQLException {

        PreparedStatement pstm = conn.prepareStatement(GET_ALL_PAYMENTS_FOR_ACCOUNT);
        pstm.setLong(1, accountId);
        ResultSet rs = pstm.executeQuery();

        ArrayList<Payment> paymentsList = new ArrayList<>();
        while (rs.next()) {
            Payment payment = new Payment(new BigInteger("" + rs.getInt("id")));
            payment.setPeriodStart(rs.getDate("period_start"));
            payment.setPeriodEnd(rs.getDate("period_end"));
            payment.setDueDate(rs.getDate("due_date"));
            payment.setSum(rs.getDouble("summa"));
            payment.setStatus(rs.getString("status"));
            paymentsList.add(payment);
        }

        return paymentsList;
    }

    public static Collection<SupportServiceInfo> getSupportServiceInfo(Connection conn) throws SQLException {

        PreparedStatement pstm = conn.prepareStatement(GET_SUPPORT_SERVICE_INFO);
        ResultSet rs = pstm.executeQuery();

        ArrayList<SupportServiceInfo> records = new ArrayList<>();
        while (rs.next()) {
            SupportServiceInfo record = new SupportServiceInfo();
            record.setCategoryId(BigInteger.valueOf(rs.getLong("category_id")));
            record.setCategoryName(rs.getString("category_name"));
            record.setPhoneNumber(rs.getString("phone_number"));
            record.setEmail(rs.getString("email"));
            records.add(record);
        }

        return records;
    }

    public static Collection<SupportSocialInfo> getSupportSocialInfo(Connection conn) throws SQLException {

        PreparedStatement pstm = conn.prepareStatement(GET_SUPPORT_SOCIAL_INFO);
        ResultSet rs = pstm.executeQuery();

        ArrayList<SupportSocialInfo> records = new ArrayList<>();
        while (rs.next()) {
            SupportSocialInfo record = new SupportSocialInfo();
            record.setCategoryId(BigInteger.valueOf(rs.getLong("category_id")));
            record.setCategoryName(rs.getString("category_name"));
            record.setAttrId(BigInteger.valueOf(rs.getLong("attr_id")));
            record.setName(rs.getString("name"));
            record.setLink(rs.getString("link"));
            records.add(record);
        }

        return records;
    }

    private static Account getAccount(PreparedStatement pstm) throws SQLException {
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            return getAccountFromResultSet(rs);
        }
        return null;
    }

    private static Account getAccountFromResultSet(ResultSet rs) throws SQLException {

        Account account = Account.newBuilder()
                .setId(rs.getInt("id"))
                .setLogin(rs.getString("login"))
                .setPassword(rs.getString("password"))
                .setFirstName(rs.getString("first_name"))
                .setLastName(rs.getString("last_name"))
                .setStatus(AccountStatus.getAccountStatusByValue(rs.getString("status")))
                .setEmail(rs.getString("email"))
                .setAddress(rs.getString("address")).build();

        return account;
    }

    public static Collection<Hardware> getHardwareByServiceType(Connection conn, long accountId, long serviceTypeId)
            throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GET_HARDWARE_FOR_ACCOUNT_BY_SERVICE_TYPE_ID);
        pstm.setLong(1, accountId);
        pstm.setLong(2, serviceTypeId);
        ResultSet rs = pstm.executeQuery();

        ArrayList<Hardware> records = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString("name");
            String serialNumber = rs.getString("serial_number");
            String status = rs.getString("status");
            Hardware hw = new Hardware(serviceTypeId, name, serialNumber, status);
            records.add(hw);
        }

        return records;
    }

    public static void deactivateHardware(Connection conn, long accountId, long serviceTypeId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(DEACTIVATE_HARDWARE_FOR_ACCOUNT_BY_SERVICE_ID);
        System.out.println("DBUtils.deactivateHardware. START. accountId: " + accountId + ", serviceTypeId: " + serviceTypeId);
        pstm.setLong(1, accountId);
        pstm.setLong(2, serviceTypeId);
        int result = pstm.executeUpdate();
        System.out.println("DBUtils.deactivateHardware. result: " + result);
    }

    public static void deactivateService(Connection conn, long accountId, long serviceTypeId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(DEACTIVATE_SERVICE_FOR_ACCOUNT_BY_SERVICE_ID);
        System.out.println("DBUtils.deactivateService. START. accountId: " + accountId + ", serviceTypeId: " + serviceTypeId);
        pstm.setLong(1, accountId);
        pstm.setLong(2, serviceTypeId);
        int result = pstm.executeUpdate();
        System.out.println("DBUtils.deactivateService. result: " + result);
    }

    public static Collection<ServicePlan> getServicePlansByTypeId(Connection conn, long serviceTypeId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GET_SERVICE_PLANS_BY_SERVICE_TYPE_ID);
        pstm.setLong(1, serviceTypeId);
        ResultSet rs = pstm.executeQuery();

        TreeSet<ServicePlan> services = new TreeSet<>(new Comparator<ServicePlan>() {
            @Override
            public int compare(ServicePlan o1, ServicePlan o2) {
                return (int) (o1.getPrice() - o2.getPrice());
            }
        });
        while (rs.next()) {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            long typeId = rs.getLong("type_id");
            String typeName = rs.getString("type_name");
            double price = rs.getDouble("price");

            ServicePlan servicePlan = new ServicePlan(id, name, new ServiceType(typeId, typeName), price);
            System.out.println("DBUtils.getServicePlansByTypeId: [servicePlan]: " + servicePlan);
            services.add(servicePlan);
        }

        return services;
    }

    public static Map<String,Object> getInternetOptionsForService(Connection conn, long serviceId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GET_INTERNET_OPTIONS_BY_SERVICE_ID);
        pstm.setLong(1, serviceId);
        ResultSet rs = pstm.executeQuery();

        Map<String,Object> options = new HashMap<>();
        if (rs.next()) {
            options.put("downloadSpeed", rs.getString("download_speed"));
            options.put("uploadSpeed", rs.getString("upload_speed"));
            options.put("dataLimit", rs.getString("data_limit"));
        }
        return options;
    }

    public static Map<String,Object> getTvOptionsForService(Connection conn, long serviceId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GET_TV_OPTIONS_BY_SERVICE_ID);
        pstm.setLong(1, serviceId);
        ResultSet rs = pstm.executeQuery();

        Map<String,Object> options = new HashMap<>();
        if (rs.next()) {
            options.put("channelsCount", rs.getInt("channels_count"));
            options.put("uhdSupport", rs.getString("uhd_support"));
        }
        return options;
    }

    public static Map<String,Object> getPhoneOptionsForService(Connection conn, long serviceId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GET_PHONE_OPTIONS_BY_SERVICE_ID);
        pstm.setLong(1, serviceId);
        ResultSet rs = pstm.executeQuery();

        Map<String,Object> options = new HashMap<>();
        if (rs.next()) {
            options.put("talkLimit", rs.getInt("talk_limit"));
            options.put("dataLimit", rs.getInt("data_limit"));
            options.put("voiceMail", "On".equals(rs.getString("voice_mail")));
        }
        return options;
    }

    public static long findAvailableHardware(Connection conn, long serviceId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(FIND_AVAILABLE_HW);
        pstm.setLong(1, serviceId);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            return rs.getLong("hardware_id");
        }
        return 0;
    }

    public static void reserveHardware(Connection conn, long accountId, long hardwareId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(RESERVE_HW_FOR_ACCOUNT);
        pstm.setLong(1, accountId);
        pstm.setLong(2, hardwareId);
        pstm.executeUpdate();
    }

    public static void activateServiceForAccount(Connection conn, long accountId, long serviceId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(ACTIVATE_SERVICE_FOR_ACCOUNT);
        pstm.setLong(1, accountId);
        pstm.setLong(2, serviceId);
        pstm.executeUpdate();
    }
}
