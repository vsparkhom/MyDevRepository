<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="true" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages"/>

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

            <c:set var="isPhoneServiceActive" value="${phoneService.status eq 'active'}"/>
            <c:choose>
                <c:when test="${isPhoneServiceActive}">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <table width="100%">
                                        <tr>
                                            <td><strong><fmt:message key="label.phone.1" /></strong></td>
                                            <td align="right">
                                                <a href="${pageContext.request.contextPath}/manage?action=remove&serviceid=${phoneService.plan.id}">
                                                    <button type="button" class="btn btn-danger"><fmt:message key="label.phone.2" /></button>
                                                </a>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="panel-body">
                                    <p><fmt:message key="label.phone.3" />: <strong><fmt:message key="${phoneService.plan.name}" /></strong></p>
                                    <p><fmt:message key="label.phone.4" />: <strong>${phoneService.plan.price}</strong></p>
                                    <p><fmt:message key="label.phone.5" />: <strong>${phoneService.plan.talkLimit}</strong></p>
                                    <p><fmt:message key="label.phone.6" />: <strong>${phoneService.plan.dataLimit}</strong></p>
                                    <p><fmt:message key="label.phone.7" />:
                                        <strong>
                                            <c:choose>
                                                <c:when test="${phoneService.plan.voiceMail}">
                                                    <fmt:message key="db.service_opts.yes" />
                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:message key="db.service_opts.no" />
                                                </c:otherwise>
                                            </c:choose>
                                        </strong>
                                    </p>
                                </div>
                                <!--div class="panel-footer">
                                    Panel Footer
                                </div-->
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <strong><fmt:message key="label.phone.8" /></strong>
                                </div>
                                <div class="panel-body">
                                    <c:forEach items="${serviceHardware}" var="pieceOfHw">
                                        <p><fmt:message key="label.phone.9" />: <strong>${pieceOfHw.name}</strong></p>
                                        <p>&nbsp;&nbsp;&nbsp;&nbsp; ${pieceOfHw.serialNumber} - <fmt:message key="${pieceOfHw.status.value}" /></p>
                                    </c:forEach>
                                    <p>&nbsp;</p>
                                </div>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <!--div class="panel-footer">
                                    Panel Footer
                                </div-->
                            </div>
                        </div>
                        <!-- /.col-lg-6 -->
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <table width="100%">
                                        <tr>
                                            <td><strong><fmt:message key="label.phone.1" /></strong></td>
                                            <td align="right">
                                                <a href="${pageContext.request.contextPath}/plans?typeid=${phoneService.plan.type.id}">
                                                    <button type="button" class="btn btn-success"><fmt:message key="label.phone.10" /></button>
                                                </a>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="panel-body">
                                    <p><fmt:message key="label.phone.11" /></p>
                                </div>
                                <!--div class="panel-footer">
                                    Panel Footer
                                </div-->
                            </div>
                        </div>
                        <!-- /.col-lg-6 -->
                    </div>
                </c:otherwise>
            </c:choose>


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
