package com.perscab.controller;

import com.perscab.db.ConnectionUtils;
import com.perscab.db.DBUtils;
import com.perscab.model.Account;
import com.perscab.model.Payment;
import com.perscab.servlets.MyUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class BillingHelper {

    public static void initBillingInfo(HttpServletRequest request) throws IOException {
        Account account = MyUtils.getAuthorizedAccount(request.getSession());
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();

            double currentBallance = DBUtils.calcCurrentBallance(conn, account.getId());
            System.out.println("MainServlet.getBillingInfo: [currentBallance]:" + currentBallance);
            request.setAttribute("currentBallance", currentBallance);

            Date dueDate = DBUtils.getPaymentDueDate(conn, account.getId());
            System.out.println("MainServlet.getBillingInfo: [dueDate]:" + dueDate);
            SimpleDateFormat formatter = new SimpleDateFormat("MMMMM dd, yyyy");

            if (dueDate != null) {
                request.setAttribute("dueDate", formatter.format(dueDate));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeQuietly(conn);
        }
    }

    public static void initPaymentsHistory(HttpServletRequest request)  throws IOException {
        Account account = MyUtils.getAuthorizedAccount(request.getSession());
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();

            Collection<Payment> payments = DBUtils.getPayments(conn, account.getId());

            System.out.println("payments: ");
            for (Payment p : payments) {
                System.out.println("  - p: " + p);
            }

            request.setAttribute("payments", payments);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            ConnectionUtils.closeQuietly(conn);
        }
    }

}
