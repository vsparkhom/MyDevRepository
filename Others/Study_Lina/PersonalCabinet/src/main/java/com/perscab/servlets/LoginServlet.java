package com.perscab.servlets;

import com.perscab.db.ConnectionUtils;
import com.perscab.db.DBUtils;
import com.perscab.model.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("--- LoginServlet.GET ---");

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("--- LoginServlet.POST ---");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        boolean remember = "yes".equals(request.getParameter("remember"));

        Account account = null;
        boolean hasError = false;
        String errorString = null;

        if (login == null || password == null || login.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";//TODO: i18n
        } else {
            Connection conn = null;
            try {
                conn = ConnectionUtils.getConnection();
                account = DBUtils.findAccount(conn, login, password);

                if (account == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            } finally {
                ConnectionUtils.closeQuietly(conn);
            }
        }
        if (hasError) {
            request.setAttribute("errorString", errorString);
            request.setAttribute("account", account);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp");
            dispatcher.forward(request, response);
        } else {
            HttpSession session = request.getSession();
            MyUtils.storeAuthorizedAccount(session, account);

            if (remember) {
                MyUtils.storeUserCookie(response, account);
            } else {
                MyUtils.deleteUserCookie(response);
            }

            response.sendRedirect(request.getContextPath() + "/main");
        }

    }
}
