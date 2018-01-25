package com.perscab.db;

import com.perscab.model.Account;
import com.perscab.model.AccountStatus;
import com.perscab.model.Payment;
import com.perscab.model.ServiceInstance;

import static com.perscab.db.DBQueries.*;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

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
        PreparedStatement pstm = conn.prepareStatement(GET_SERVICE_NAME_BY_TYPE);
        pstm.setString(1, login);
        pstm.setLong(2, typeId.longValue());

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String serviceName = rs.getString("service_name");
            String serviceType = rs.getString("type_name");
            ServiceInstance serviceInstance = new ServiceInstance(serviceName, serviceType, "Active");
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

    public static ArrayList<Payment> getPayments(Connection conn, int accountId) throws SQLException {

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



}
