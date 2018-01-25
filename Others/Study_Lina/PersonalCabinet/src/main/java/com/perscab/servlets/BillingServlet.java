package com.perscab.servlets;

import com.perscab.controller.BillingHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BillingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("--- BillingServlet.GET---");

        BillingHelper.initBillingInfo(request);

        BillingHelper.initPaymentsHistory(request);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/billing.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //DO NOTHING
    }

}
