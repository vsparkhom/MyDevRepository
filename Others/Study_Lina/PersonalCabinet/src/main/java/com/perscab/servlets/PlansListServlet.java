package com.perscab.servlets;

import com.perscab.controller.ServiceHelper;
import com.perscab.db.services.ServiceStrategy;
import com.perscab.db.services.ServiceStrategyHolder;
import com.perscab.model.Account;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PlansListServlet extends ServiceServlet {

    public static final String TYPE_ID_REQUEST_PARAMETER = "typeid";

    @Override
    public void initService(HttpServletRequest request) throws IOException {
        Account account = MyUtils.getAuthorizedAccount(request.getSession());
        long typeId = Long.parseLong(request.getParameter(TYPE_ID_REQUEST_PARAMETER));
        ServiceStrategy serviceStrategy = ServiceStrategyHolder.getInstance().getServiceStrategyByTypeId(typeId);
        ServiceHelper.initServicePlans(request, serviceStrategy, account.getId());
        request.setAttribute("typeid", typeId);
    }

    @Override
    public String getPageToForward() {
        return "/WEB-INF/plans.jsp";
    }
}
