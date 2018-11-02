package com.perscab.servlets;

import com.perscab.controller.ServiceHelper;
import com.perscab.db.AttributeConsts;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TVServlet extends ServiceServlet {

    @Override
    public void initService(HttpServletRequest request) throws IOException {
        ServiceHelper.initTvService(request);
        ServiceHelper.initHardware(request, AttributeConsts.TV_SERVICE_TYPE_ID);//TODO: check
    }

    @Override
    public String getPageToForward() {
        return "/WEB-INF/tv.jsp";
    }
}
