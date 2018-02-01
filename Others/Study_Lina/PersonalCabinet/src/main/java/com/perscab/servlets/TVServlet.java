package com.perscab.servlets;

import com.perscab.controller.ServiceHelper;
import com.perscab.db.AttributeConsts;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TVServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("--- TVServlet.GET---");

        ServiceHelper.initTvService(request);

        ServiceHelper.initHardware(request, AttributeConsts.TV_SERVICE_TYPE_ID);//TODO: check

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/tv.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //DO NOTHING
    }

}
