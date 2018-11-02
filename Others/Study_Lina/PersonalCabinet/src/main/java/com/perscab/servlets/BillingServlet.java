package com.perscab.servlets;

import com.perscab.controller.BillingHelper;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class BillingServlet extends ServiceServlet {

    @Override
    public void initService(HttpServletRequest request) throws IOException {
        BillingHelper.initBillingInfo(request);
        BillingHelper.initPaymentsHistory(request);
    }

    @Override
    public String getPageToForward() {
        return "/WEB-INF/billing.jsp";
    }

}
