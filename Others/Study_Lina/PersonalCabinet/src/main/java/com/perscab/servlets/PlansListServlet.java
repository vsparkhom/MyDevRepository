package com.perscab.servlets;

import com.perscab.controller.ServiceHelper;
import com.perscab.db.services.ServiceStrategyHolder;

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

        long typeId = new Long(request.getParameter("typeid")).longValue();
        ServiceHelper.initServicePlans(request, ServiceStrategyHolder.getInstance().getServiceStrategyByTypeId(typeId));

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/plans.jsp");
        dispatcher.forward(request, response);
    }
}
