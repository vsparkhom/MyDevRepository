package com.perscab.servlets;

import com.perscab.controller.ServiceHelper;
import com.perscab.db.AttributeConsts;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InternetServlet extends ServiceServlet {

    @Override
    public void initService(HttpServletRequest request) throws IOException {
        ServiceHelper.initInternetService(request);
        ServiceHelper.initHardware(request, AttributeConsts.INTERNET_SERVICE_TYPE_ID);
    }

    @Override
    public String getPageToForward() {
        return "/WEB-INF/internet.jsp";
    }

}
