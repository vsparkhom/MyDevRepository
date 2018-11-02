package com.perscab.servlets;

import com.perscab.controller.ServiceHelper;
import com.perscab.db.AttributeConsts;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PhoneServlet extends ServiceServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //DO NOTHING
    }

    @Override
    public void initService(HttpServletRequest request) throws IOException {
        ServiceHelper.initPhoneService(request);
        ServiceHelper.initHardware(request, AttributeConsts.PHONE_SERVICE_TYPE_ID);
    }

    @Override
    public String getPageToForward() {
        return "/WEB-INF/phone.jsp";
    }

}
