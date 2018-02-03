<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <c:set var="isTvServiceActive" value="${tvService.status eq 'Active'}"/>
            <c:choose>
                <c:when test="${isTvServiceActive}">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <table width="100%">
                                        <tr>
                                            <td><strong>TV</strong></td>
                                            <td align="right">
                                                <a href="${pageContext.request.contextPath}/manage?action=remove&type=${tvService.type.id}">
                                                    <button type="button" class="btn btn-danger">Disconnect</button>
                                                        <%--<button type="button" class="btn btn-success">Install</button>--%>
                                                </a>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="panel-body">
                                    <p>Service name: <strong>${tvService.plan.name}</strong></p>
                                    <p>Price: <strong>$${tvService.plan.price}</strong></p>
                                    <p>Channels count: <strong>${tvService.plan.channelsCount}</strong></p>
                                    <p>UHD support: <strong>${tvService.plan.uhdSupport}</strong></p>
                                </div>
                                <!--div class="panel-footer">
                                    Panel Footer
                                </div-->
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <strong>Assigned hardware</strong>
                                </div>
                                <div class="panel-body">
                                    <c:forEach items="${serviceHardware}" var="pieceOfHw">
                                        <p>TV modem: <strong>${pieceOfHw.name}</strong></p>
                                        <p>&nbsp;&nbsp;&nbsp;&nbsp; ${pieceOfHw.serialNumber} - ${pieceOfHw.status}</p>
                                    </c:forEach>
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
                                            <td><strong>TV</strong></td>
                                            <td align="right">
                                                <a href="${pageContext.request.contextPath}/manage?action=add&typeid=${tvService.plan.type.id}">
                                                    <button type="button" class="btn btn-success">Install</button>
                                                </a>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="panel-body">
                                    <p>TV service is not installed</p>
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
