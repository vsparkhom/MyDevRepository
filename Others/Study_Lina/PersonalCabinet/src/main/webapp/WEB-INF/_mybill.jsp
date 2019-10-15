<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages"/>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <strong><fmt:message key="label.mybill.1" /></strong>
            </div>
            <div class="panel-body">
                <p><fmt:message key="label.mybill.2" />: <strong>${currentBalance}</strong></p>

                <c:set var="isPaymentRequired" value="${dueDate ne null}"/>
                <c:choose>
                    <c:when test="${isPaymentRequired}">
                        <p><fmt:message key="label.mybill.3" />: <strong>${dueDate}</strong></p>
                    </c:when>
                    <c:otherwise>
                        <p><fmt:message key="label.mybill.4" /></p>
                    </c:otherwise>
                </c:choose>

                <p><fmt:message key="label.mybill.5" />
                    <a href="mailto:${authorizedAccount.email}">${authorizedAccount.email}</a>
                </p>
            </div>
            <!--div class="panel-footer">
                Panel Footer
            </div-->
        </div>
    </div>
    <!-- /.col-lg-4 -->
</div>