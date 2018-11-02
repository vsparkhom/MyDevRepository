package com.perscab.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class ServiceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("--- ServiceServlet.GET---");

        initService(request);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(getPageToForward());
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //DO NOTHING
    }

    public abstract void initService(HttpServletRequest request) throws IOException;
    public abstract String getPageToForward();
}
