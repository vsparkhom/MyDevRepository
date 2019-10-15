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

            <c:set var="isInternetServiceActive" value="${internetService.status eq 'active'}"/>
            <c:choose>
                <c:when test="${isInternetServiceActive}">

                    <div class="row">
                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <table width="100%">
                                        <tr>
                                            <td><strong><fmt:message key="label.internet.1" /></strong></td>
                                            <td align="right">
                                                <a href="${pageContext.request.contextPath}/manage?action=remove&serviceid=${internetService.plan.id}">
                                                    <button type="button" class="btn btn-danger"><fmt:message key="label.internet.2" /></button>
                                                </a>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="panel-body">
                                    <p><fmt:message key="label.internet.3" />: <strong><fmt:message key="${internetService.plan.name}" /></strong></p>
                                    <p><fmt:message key="label.internet.4" />: <strong>${internetService.plan.price}</strong></p>
                                    <p><fmt:message key="label.internet.5" />: <strong>${internetService.plan.downloadSpeed}</strong></p>
                                    <p><fmt:message key="label.internet.6" />: <strong>${internetService.plan.uploadSpeed}</strong></p>
                                    <p><fmt:message key="label.internet.7" /> <strong>${internetService.plan.dataLimit}</strong></p>
                                </div>
                                <!--div class="panel-footer">
                                    Panel Footer
                                </div-->
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <strong><fmt:message key="label.internet.8" /></strong>
                                </div>
                                <div class="panel-body">
                                    <c:forEach items="${serviceHardware}" var="pieceOfHw">
                                        <p><fmt:message key="label.internet.9" />: <strong>${pieceOfHw.name}</strong></p>
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
                        <!-- /.col-lg-4 -->
                    </div>
                    <br/>

                    <div class="row">
                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <strong><fmt:message key="label.internet.10" /></strong>
                                </div>
                                <!-- /.panel-heading -->
                                <div class="panel-body">

                                    <!-- Styles -->
                                    <style>
                                        #chartdiv {
                                            width: 100%;
                                            height: 300px;
                                            font-size: 11px;
                                        }
                                    </style>

                                    <!-- Resources -->
                                    <script src="js/amcharts.js"></script>
                                    <script src="https://www.amcharts.com/lib/3/pie.js"></script>
                                    <script src="https://www.amcharts.com/lib/3/plugins/export/export.min.js"></script>
                                    <link rel="stylesheet"
                                          href="https://www.amcharts.com/lib/3/plugins/export/export.css"
                                          type="text/css" media="all"/>
                                    <script src="https://www.amcharts.com/lib/3/themes/light.js"></script>

                                    <!-- Chart code -->
                                    <script>
                                        var chart = AmCharts.makeChart("chartdiv", {
                                            "type": "pie",
                                            "theme": "light",
                                            "dataProvider": [
                                                {
                                                    "title": "Used",
                                                    "value": 4852
                                                },
                                                {
                                                    "title": "Remaining",
                                                    "value": 9899
                                                }
                                            ],
                                            "titleField": "title",
                                            "valueField": "value",
                                            "labelRadius": 5,

                                            "radius": "25%",
                                            "innerRadius": "40%",
                                            "labelText": "[[title]]",
                                            "export": {
                                                "enabled": false
                                            }
                                        });
                                    </script>

                                    <!-- HTML -->
                                    <p><fmt:message key="label.internet.11" />: <strong>25 <fmt:message key="label.internet.12" /> / 500 <fmt:message key="label.internet.12" /></strong></p>

                                    <div id="chartdiv"></div>

                                </div>
                                <!-- /.panel-body -->
                            </div>
                            <!-- /.panel -->
                        </div>
                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <strong><fmt:message key="label.internet.13" /></strong>
                                </div>
                                <div class="panel-body">
                                    <p>acc1@gmail.com</p>
                                </div>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <br/>
                                <!--div class="panel-footer">
                                    Panel Footer
                                </div-->
                            </div>
                        </div>
                        <!-- /.col-lg-4 -->
                    </div>


                </c:when>
                <c:otherwise>

                    <div class="row">
                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <table width="100%">
                                        <tr>
                                            <td><strong><fmt:message key="label.internet.1" /></strong></td>
                                            <td align="right">
                                                <a href="${pageContext.request.contextPath}/plans?typeid=${internetService.plan.type.id}">
                                                    <button type="button" class="btn btn-success"><fmt:message key="label.internet.14" /></button>
                                                </a>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="panel-body">
                                    <p><fmt:message key="label.internet.15" /></p>
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

        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<jsp:include page="_bottom.jsp"></jsp:include>

</body>

</html>
