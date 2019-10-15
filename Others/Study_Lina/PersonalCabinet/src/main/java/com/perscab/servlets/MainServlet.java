package com.perscab.servlets;

import com.perscab.controller.BillingHelper;
import com.perscab.controller.ServiceHelper;
import com.perscab.controller.SupportInfoHelper;
import com.perscab.model.Account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("--- MainServlet.GET ---");

        Account loggedUser = MyUtils.getAuthorizedAccount(request.getSession());
        if (loggedUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        BillingHelper.initBillingInfo(request);
        ServiceHelper.initServices(request);
        SupportInfoHelper.initSupportInfo(request);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        //DO NOTHING
    }

}
