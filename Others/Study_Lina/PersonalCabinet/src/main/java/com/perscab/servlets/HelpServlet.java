package com.perscab.servlets;

import com.perscab.controller.SupportInfoHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HelpServlet extends ServiceServlet {

    @Override
    public void initService(HttpServletRequest request) throws IOException {
        SupportInfoHelper.initSupportInfo(request);
    }

    @Override
    public String getPageToForward() {
        return "/WEB-INF/help.jsp";
    }

}
