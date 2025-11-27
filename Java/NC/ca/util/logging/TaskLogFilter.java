package ca.util.logging;

import java.io.IOException;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.ejb.ObjectNotFoundException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ca.shaw.exception.base.DefaultShawRuntimeException;
import ca.shaw.util.logging.UserAction;
import ca.shaw.util.transaction.TransactionManager;

import com.netcracker.ejb.core.users.UserFacade;
import com.netcracker.security.UserSession;
import com.netcracker.solutions.shaw.utilities.StringUtils;

public class TaskLogFilter implements Filter {

    private static final String USESSION = "usession";

    private static final Logger LOG = Logger.getLogger(TaskLogFilter.class);

    private static final String DEFAULT_ACTION_PARAMETER = "action";
    private static final List<String> TARGET_URLS;
    private static final List<String> BUTTON_NAMES;
    private static final List<String> DEFAULT_TASKS_ID_PARAMETER;

    static {
        final List<String> buttonNames = new ArrayList<String>();
        buttonNames.add("mark_finished");
        buttonNames.add("mark_terminated");
        buttonNames.add("force_terminate");
        buttonNames.add("cancel_task");
        BUTTON_NAMES = Collections.unmodifiableList(buttonNames);

        final List<String> defaultTargetUrls = new ArrayList<String>();
        defaultTargetUrls.add("/applications/om/handle.jsp");
        defaultTargetUrls.add("/platform/orchestrator/manual_operations.jsp");
        TARGET_URLS = Collections.unmodifiableList(defaultTargetUrls);

        final List<String> tasksIdParam = new ArrayList<String>();
        tasksIdParam.add("items");
        tasksIdParam.add("taskOrProcessID");
        DEFAULT_TASKS_ID_PARAMETER = Collections.unmodifiableList(tasksIdParam);
    }

    @SuppressWarnings("PMD.AvoidDeeplyNestedIfStmts")
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            final HttpServletRequest request = (HttpServletRequest) servletRequest;
            final String uri = request.getRequestURI();

            dumpParameters(request);

            final String action = request.getParameter(DEFAULT_ACTION_PARAMETER);
            if (TARGET_URLS.contains(uri.toLowerCase(Locale.getDefault()))
                    && BUTTON_NAMES.contains(action)) {
                UserSession session = (UserSession) request.getSession(true).getAttribute(
                        USESSION);
                if (session != null) {
                    final BigInteger[] tasksId = getTaskId(request);
                    final String userName = session.getUserName();
                    if (LOG.isInfoEnabled()) {
                        List<BigInteger> listOfTasksIds = Arrays.stream(tasksId).collect(Collectors.toList());
                        LOG.info(StringUtils.concat("User '", userName, "' trigger tasks [",
                                StringUtils.join(listOfTasksIds, ",", BigInteger::toString), "] for action: ", action));
                    }
                    storeHistoryEvent(userName, tasksId);
                }
            }
            LOG.trace("Procced next filter..");
        }
        filterChain.doFilter(servletRequest, servletResponse);
        LOG.trace("Return to own context..");
    }

    protected BigInteger[] getTaskId(HttpServletRequest request) {
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String name = params.nextElement();
            if (DEFAULT_TASKS_ID_PARAMETER.contains(name)) {
                String[] parameterValues = request.getParameterValues(name);
                if (isParametersValid(parameterValues)) {
                    return Arrays.stream(parameterValues)
                            .map(BigInteger::new)
                            .toArray(BigInteger[]::new);
                }
            }
        }
        return new BigInteger[] {};
    }

    private boolean isParametersValid(String[] parameterValues) {
        if (parameterValues == null || parameterValues.length == 0) {
            return false;
        }

        for (String parameterValue : parameterValues) {
            if (org.apache.commons.lang3.StringUtils.isEmpty(parameterValue) || !parameterValue.matches("\\d+")) {
                return false;
            }
        }
        return true;
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Task log filter initialization..");
    }

    protected void dumpParameters(final HttpServletRequest request) {
        if (LOG.isTraceEnabled()) {
            Enumeration<String> params = request.getParameterNames();
            while (params.hasMoreElements()) {
                final String name = params.nextElement();
                final String value = request.getParameter(name);
                final String[] values = request.getParameterValues(name);
                LOG.trace(StringUtils.concat("Parameter ", name, " = ", value, "(",
                        StringUtils.join(values, ","), ")"));
            }

            params = request.getAttributeNames();
            while (params.hasMoreElements()) {
                final String name = params.nextElement();
                final Object value = request.getAttribute(name);
                final String[] values = request.getParameterValues(name);
                LOG.trace(StringUtils.concat("Attribute ", name, " = ", value, "(",
                        StringUtils.join(values, ","), ")"));
            }
        }
    }

    protected void storeHistoryEvent(final String userName, final BigInteger... taskIds) {
        try {
            new TransactionManager().executeInSeparateTransaction(() -> {
                try {
                    storeHistoryEventImpl(userName, taskIds);
                } catch (ObjectNotFoundException | RemoteException e) {
                    throw new DefaultShawRuntimeException(e);
                }
                return null;
            });
        } catch (Exception e) {
            LOG.error("Error storing history event", e);
        }
    }

    public static void storeHistoryEventImpl(final String userName, final BigInteger... taskIds)
            throws ObjectNotFoundException, RemoteException {
        final BigInteger userId = UserFacade.getInstance().getUserByName(userName).getID();
        for (int i = 0; i < taskIds.length; i++) {
            UserAction uAction = new UserAction(userId, taskIds[i]);
            uAction.save();
        }
    }

    public void destroy() {
        LOG.debug("Task log filter destroyed..");
    }
}
