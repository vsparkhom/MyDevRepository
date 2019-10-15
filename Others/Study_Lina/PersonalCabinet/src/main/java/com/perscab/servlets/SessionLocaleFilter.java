package com.perscab.servlets;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

import static com.perscab.servlets.PlansListServlet.TYPE_ID_REQUEST_PARAMETER;

@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("--- SessionLocaleFilter.doFilter START ---");

        AppendableHttpRequestWrapper requestWrapper = new AppendableHttpRequestWrapper((HttpServletRequest) request);

        fetchParametersFromSession(requestWrapper);

        if (requestWrapper.getParameter("sessionLocale") != null) {
            requestWrapper.getSession().setAttribute("lang", requestWrapper.getParameter("sessionLocale"));
        }

        chain.doFilter(requestWrapper, response);
    }

    private void fetchParametersFromSession(AppendableHttpRequestWrapper requestWrapper) {
        HttpSession session = requestWrapper.getSession();
        Object attribute = session.getAttribute(TYPE_ID_REQUEST_PARAMETER);
        if (attribute != null) {
            requestWrapper.setParameter(TYPE_ID_REQUEST_PARAMETER, attribute.toString());
            session.removeAttribute(TYPE_ID_REQUEST_PARAMETER);
        }
    }

    public void destroy() {
    }

    public void init(FilterConfig arg) throws ServletException {
    }

    private class AppendableHttpRequestWrapper extends HttpServletRequestWrapper {

        private HashMap<String, String> params = new HashMap<>();

        public AppendableHttpRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        public String getParameter(String name) {
            String p = params.get(name);
            return p == null ? super.getRequest().getParameter(name) : p;
        }

        public void setParameter(String name, String value) {
            params.put(name, value);
        }
    }

}