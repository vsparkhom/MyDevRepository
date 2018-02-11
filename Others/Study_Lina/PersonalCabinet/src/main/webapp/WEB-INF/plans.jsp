<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="cf" uri="/WEB-INF/tld/custom_functions.tld" %>

<!DOCTYPE html>
<html lang="en">

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
                                <div class="plan-head">${servicePlan.name}</div>
                            </div>
                            <div class="panel-body">
                                <div class="plan-section">Options</div>
                                <hr>
                                <table width="70%" align="center">
                                    <c:forEach var="options" items="${servicePlan.options}">
                                        <tr>
                                            <td align="left"><c:out
                                                    value="${cf:getOptionDisplayName(servicePlan.type.id, options.key)}"/>:
                                            </td>
                                            <td><c:out value="${options.value}"/></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                                <br/>

                                <div class="plan-section">Price</div>
                                <hr>
                                <div class="plan-price">$${servicePlan.price}</div>
                            </div>
                            <div class="panel-footer">
                                <div style="text-align: center;">

                                    <c:set var="isServiceActive" value="${currentServiceInstance.status eq 'Active'}"/>
                                    <c:choose>
                                        <c:when test="${isServiceActive}">
                                            <c:set var="installedServicePlanId" value="${currentServiceInstance.plan.id}"/>
                                            <c:choose>
                                                <c:when test="${installedServicePlanId eq servicePlan.id}">
                                                    <button type="button" class="btn btn-default disabled">Already installed</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="${pageContext.request.contextPath}/manage?action=update&serviceid=${servicePlan.id}&oldserviceid=${installedServicePlanId}">
                                                        <button type="button" class="btn btn-primary">Update</button>
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>

                                        </c:when>
                                        <c:otherwise>
                                            <a href="${pageContext.request.contextPath}/manage?action=add&serviceid=${servicePlan.id}">
                                                <button type="button" class="btn btn-success">Install</button>
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
