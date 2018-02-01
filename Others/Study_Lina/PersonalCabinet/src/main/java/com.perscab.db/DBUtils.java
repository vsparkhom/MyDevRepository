package com.perscab.db;

import com.perscab.model.Account;
import com.perscab.model.AccountStatus;
import com.perscab.model.Hardware;
import com.perscab.model.services.InternetServiceInstance;
import com.perscab.model.Payment;
import com.perscab.model.services.PhoneServiceInstance;
import com.perscab.model.services.ServiceInstance;
import com.perscab.model.services.TvServiceInstance;
import com.perscab.model.support.SupportServiceInfo;
import com.perscab.model.support.SupportSocialInfo;

import static com.perscab.db.DBQueries.*;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static ServiceInstance getServiceInstanceByType(Connection conn, String login, BigInteger typeId)
            throws SQLException  {
        PreparedStatement pstm = conn.prepareStatement(GET_SERVICE_INSTANCE_BY_TYPE);
        pstm.setString(1, login);
        pstm.setLong(2, typeId.longValue());

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String serviceName = rs.getString("service_name");
            String serviceType = rs.getString("type_name");
            ServiceInstance serviceInstance = new ServiceInstance(serviceName, serviceType, "Active", 0);
            return serviceInstance;
        }

        return null;
    }

    public static InternetServiceInstance getInternetServiceInstance(Connection conn, int accountId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GET_INTERNET_SERVICE_INSTANCE_FOR_ACCOUNT);
        pstm.setInt(1, accountId);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String name = rs.getString("service_name");
            String type = rs.getString("type_name");
            double price = rs.getDouble("price");
            String downloadSpeed = rs.getString("download_speed");
            String uploadSpeed = rs.getString("upload_speed");
            String dataLimit = rs.getString("data_limit");
            InternetServiceInstance serviceInstance = new InternetServiceInstance(name, type, "Active", price);
            serviceInstance.setDownloadSpeed(downloadSpeed);
            serviceInstance.setUploadSpeed(uploadSpeed);
            serviceInstance.setDataLimit(dataLimit);
            return serviceInstance;
        }

        return null;
    }

    public static TvServiceInstance getTvServiceInstance(Connection conn, int accountId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GET_TV_SERVICE_INSTANCE_FOR_ACCOUNT);
        pstm.setInt(1, accountId);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String name = rs.getString("service_name");
            String type = rs.getString("type_name");
            double price = rs.getDouble("price");
            int channelsCount = rs.getInt("channels_count");
            String uhdSupport = rs.getString("uhd_support");
            TvServiceInstance serviceInstance = new TvServiceInstance(name, type, "Active", price);
            serviceInstance.setChannelsCount(channelsCount);
            serviceInstance.setUhdSupport(uhdSupport);
            return serviceInstance;
        }

        return null;
    }

    public static PhoneServiceInstance getPhoneServiceInstance(Connection conn, int accountId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GET_PHONE_SERVICE_INSTANCE_FOR_ACCOUNT);
        pstm.setInt(1, accountId);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String name = rs.getString("service_name");
            String type = rs.getString("type_name");
            double price = rs.getDouble("price");
            int talkLimit = rs.getInt("talk_limit");
            int dataLimit = rs.getInt("data_limit");
            String voiceMail = rs.getString("voice_mail");
            PhoneServiceInstance serviceInstance = new PhoneServiceInstance(name, type, "Active", price);
            serviceInstance.setTalkLimit(talkLimit);
            serviceInstance.setDataLimit(dataLimit);
            serviceInstance.setVoiceMail("On".equals(voiceMail));
            return serviceInstance;
        }

        return null;
    }



    public static double calcCurrentBallance(Connection conn, int accountId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GET_CURRENT_BALLANCE);
        pstm.setInt(1, accountId);
        ResultSet rs = pstm.executeQuery();

        double currentBallance = 0;
        if (rs.next()) {
            currentBallance = rs.getDouble("summa");
        }

        return currentBallance;
    }

    public static Date getPaymentDueDate(Connection conn, int accountId) throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GET_PAYMENT_DUE_DATE);
        pstm.setInt(1, accountId);
        ResultSet rs = pstm.executeQuery();

        Date dueDate = null;
        if (rs.next()) {
            dueDate = rs.getDate("due_date");
        }

        return dueDate;
    }

    public static List<Payment> getPayments(Connection conn, int accountId) throws SQLException {

        PreparedStatement pstm = conn.prepareStatement(GET_ALL_PAYMENTS_FOR_ACCOUNT);
        pstm.setInt(1, accountId);
        ResultSet rs = pstm.executeQuery();

        ArrayList<Payment> paymentsList = new ArrayList<>();
        while (rs.next()) {
            Payment payment = new Payment(new BigInteger("" + rs.getInt("id")));
            payment.setPeriodStart(rs.getDate("period_start"));
            payment.setPeriodEnd(rs.getDate("period_end"));
            payment.setPayBefore(rs.getDate("pay_before"));
            payment.setSum(rs.getDouble("summa"));
            payment.setStatus(rs.getString("status"));
            paymentsList.add(payment);
        }

        return paymentsList;
    }

    public static List<SupportServiceInfo> getSupportServiceInfo(Connection conn) throws SQLException {

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

    public static List<SupportSocialInfo> getSupportSocialInfo(Connection conn) throws SQLException {

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

    public static List<Hardware> getHardwareByServiceType(Connection conn, int accountId, BigInteger serviceTypeId)
            throws SQLException {
        PreparedStatement pstm = conn.prepareStatement(GET_HARDWARE_FOR_ACCOUNT_BY_SERVICE_TYPE_ID);
        pstm.setInt(1, accountId);
        pstm.setLong(2, serviceTypeId.longValue());
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
}
