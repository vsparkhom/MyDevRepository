<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

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
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <table width="100%">
                            <tr>
                                <td><strong>Internet</strong></td>
                                <td align="right">
                                    <%--<button type="button" class="btn btn-danger">Disconnect</button>--%>
                                    <button type="button" class="btn btn-success">Install</button>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="panel-body">
                        <p>Service name: <strong>${service.name}</strong></p>
                        <p>Price: <strong>$${service.price}</strong></p>
                        <p>Download speed: <strong>${service.downloadSpeed}</strong></p>
                        <p>Upload speed: <strong>${service.uploadSpeed}</strong></p>
                        <p>Monthly data limit <strong>${service.dataLimit}</strong></p>
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
                        <c:forEach items="${serviceHardware}" var="pieceOfHw" >
                            <p>Internet modem: <strong>${pieceOfHw.name}</strong></p>
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
            <!-- /.col-lg-4 -->
        </div>
        <br/>
        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <strong>Usage</strong>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">

                        <!-- Styles -->
                        <style>
                            #chartdiv {
                                width		: 100%;
                                height		: 300px;
                                font-size	: 11px;
                            }
                        </style>

                        <!-- Resources -->
                        <script src="js/amcharts.js"></script>
                        <script src="https://www.amcharts.com/lib/3/pie.js"></script>
                        <script src="https://www.amcharts.com/lib/3/plugins/export/export.min.js"></script>
                        <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
                        <script src="https://www.amcharts.com/lib/3/themes/light.js"></script>

                        <!-- Chart code -->
                        <script>
                            var chart = AmCharts.makeChart( "chartdiv", {
                                "type": "pie",
                                "theme": "light",
                                "dataProvider": [ {
                                    "title": "Used",
                                    "value": 4852
                                }, {
                                    "title": "Remaining",
                                    "value": 9899
                                } ],
                                "titleField": "title",
                                "valueField": "value",
                                "labelRadius": 5,

                                "radius": "25%",
                                "innerRadius": "40%",
                                "labelText": "[[title]]",
                                "export": {
                                    "enabled": false
                                }
                            } );
                        </script>

                        <!-- HTML -->
                        <p>Used in current month: <strong>25 GB / 500 GB</strong></p>
                        <div id="chartdiv"></div>

                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <strong>Emails</strong>
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
                    <!--div class="panel-footer">
                        Panel Footer
                    </div-->
                </div>
            </div>
            <!-- /.col-lg-4 -->
        </div>
    </div>
    <!-- /.container-fluid -->
</div>
<!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<jsp:include page="_bottom.jsp"></jsp:include>

</body>

</html>
