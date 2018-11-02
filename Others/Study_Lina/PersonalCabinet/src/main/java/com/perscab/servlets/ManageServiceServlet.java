package com.perscab.servlets;

import com.perscab.controller.ServiceHelper;
import com.perscab.model.Account;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ManageServiceServlet extends HttpServlet {

    private static final String ADD_ACTION = "add";
    private static final String REMOVE_ACTION = "remove";
    private static final String UPDATE_ACTION = "update";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        System.out.println("--- ManageServiceServlet.GET---");

        String action = request.getParameter("action");
        long serviceId = Long.parseLong(request.getParameter("serviceid"));

        System.out.println("action: " + action);
        System.out.println("serviceId: " + serviceId);

        Account account = MyUtils.getAuthorizedAccount(request.getSession());

        switch (action) {
            case ADD_ACTION:
                ServiceHelper.addService(account.getId(), serviceId);
                break;
            case REMOVE_ACTION:
                ServiceHelper.removeService(account.getId(), serviceId);
                break;
            case UPDATE_ACTION:
                long oldServiceId = Long.parseLong(request.getParameter("oldserviceid"));
                ServiceHelper.updateService(account.getId(), oldServiceId, serviceId);
                break;
            default:
                System.out.println("There is no such action for service: " + action);
        }

        response.sendRedirect(request.getContextPath() + "/main");
    }

}
