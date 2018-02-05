package com.perscab.servlets;

import com.perscab.controller.ServiceHelper;
import com.perscab.model.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManageServiceServlet extends HttpServlet {

    private static final String ADD_ACTION = "add";
    private static final String REMOVE_ACTION = "remove";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("--- ManageServiceServlet.GET---");

        String action = request.getParameter("action");
        long serviceId = new Long(request.getParameter("serviceid")).longValue();

        System.out.println("action: " + action);
        System.out.println("serviceId: " + serviceId);

        Account account = MyUtils.getAuthorizedAccount(request.getSession());

        if (ADD_ACTION.equals(action)) {
            ServiceHelper.addService(account.getId(), serviceId);
        } else if (REMOVE_ACTION.equals(action)) {
            ServiceHelper.removeService(account.getId(), serviceId);
        } else {
            System.out.println("There is no such action for service: " + action);
        }

        response.sendRedirect(request.getContextPath() + "/main");
    }

}
