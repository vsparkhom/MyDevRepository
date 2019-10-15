<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="true" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages"/>

<c:set var="typeid" value="${typeid}" scope="session" />

<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<jsp:include page="_header.jsp"></jsp:include>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <jsp:include page="_navigation.jsp"></jsp:include>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <br/>

            <div class="row">
                <c:forEach items="${servicePlans}" var="servicePlan">
                    <%--<p>${servicePlan.id}-${servicePlan.name}-${servicePlan.type.name}-${servicePlan.price}</p>--%>
                    <div class="col-lg-${columnWidth}">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="plan-head"><fmt:message key="${servicePlan.name}" /></div>
                            </div>
                            <div class="panel-body">
                                <div class="plan-section"><fmt:message key="label.plans.1" /></div>
                                <hr>
                                <table width="70%" align="center">
                                    <c:forEach var="option" items="${servicePlan.options}">
                                        <tr>
                                            <td align="left">
                                                <fmt:message key="${option.key.id}" />:
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${option.key.i18n}">
                                                        <fmt:message key="${option.value}" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="${option.value}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <br/>

                                <div class="plan-section"><fmt:message key="label.plans.2" /></div>
                                <hr>
                                <div class="plan-price">${servicePlan.price} <fmt:message key="label.plans.6" /></div>
                            </div>
                            <div class="panel-footer">
                                <div style="text-align: center;">

                                    <c:set var="isServiceActive" value="${currentServiceInstance.status eq 'active'}"/>
                                    <c:choose>
                                        <c:when test="${isServiceActive}">
                                            <c:set var="installedServicePlanId" value="${currentServiceInstance.plan.id}"/>
                                            <c:choose>
                                                <c:when test="${installedServicePlanId eq servicePlan.id}">
                                                    <button type="button" class="btn btn-default disabled"><fmt:message key="label.plans.3" /></button>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="${pageContext.request.contextPath}/manage?action=update&serviceid=${servicePlan.id}&oldserviceid=${installedServicePlanId}">
                                                        <button type="button" class="btn btn-primary"><fmt:message key="label.plans.4" /></button>
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>

                                        </c:when>
                                        <c:otherwise>
                                            <a href="${pageContext.request.contextPath}/manage?action=add&serviceid=${servicePlan.id}">
                                                <button type="button" class="btn btn-success"><fmt:message key="label.plans.5" /></button>
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>

            <br/>
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<jsp:include page="_bottom.jsp"></jsp:include>

</body>

</html>
