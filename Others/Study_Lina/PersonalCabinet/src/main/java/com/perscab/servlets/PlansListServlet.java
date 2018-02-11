package com.perscab.servlets;

import com.perscab.controller.ServiceHelper;
import com.perscab.db.services.ServiceStrategy;
import com.perscab.db.services.ServiceStrategyHolder;
import com.perscab.model.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PlansListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("--- PlansListServlet.GET---");
        Account account = MyUtils.getAuthorizedAccount(request.getSession());

        long typeId = Long.parseLong(request.getParameter("typeid"));
        ServiceStrategy serviceStrategy = ServiceStrategyHolder.getInstance().getServiceStrategyByTypeId(typeId);
        ServiceHelper.initServicePlans(request, serviceStrategy, account.getId());

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/plans.jsp");
        dispatcher.forward(request, response);
    }
}
