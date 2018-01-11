package com.perscab.servlets;

import com.perscab.db.ConnectionUtils;
import com.perscab.db.DBUtils;
import com.perscab.model.Account;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;

//import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(name = "/main")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("--- MainServlet.GET---");

        getAccountInfo(request);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //DO NOTHING
    }

    private void getAccountInfo(HttpServletRequest request) throws IOException {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getConnection();
            Account account = DBUtils.findAccount(conn, "test2");
            System.out.println("Found account: " + account);

            request.setAttribute("account", account);

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionUtils.closeQuietly(conn);
        }
    }
}
