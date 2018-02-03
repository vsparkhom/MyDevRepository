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
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Billing</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <br/>
        <!-- /.row -->
        <jsp:include page="_mybill.jsp"></jsp:include>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Services</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <br/>
        <div class="row">
            <div class="col-lg-4 col-md-6">
                <c:set var = "isInternetServiceActive" value = "${internetService.status eq 'Active'}" />
                <c:choose>
                    <c:when test="${isInternetServiceActive}">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <!--i class="fa fa-comments fa-5x"></i-->
                                        <img src="img/internet.png" height="70" width="70"/>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="big"><strong>${internetService.plan.type.name}</strong></div>
                                        <div>${internetService.plan.name}</div>
                                        <div class="text-success">${internetService.status}</div>
                                    </div>
                                </div>
                            </div>
                            <a href="${pageContext.request.contextPath}/internet">
                                <div class="panel-footer">
                                    <span class="pull-left">View Details</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>

                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <!--i class="fa fa-comments fa-5x"></i-->
                                        <img src="img/internet.png" height="70" width="70"/>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="big"><strong>${internetService.plan.type.name}</strong></div>
                                        <div>${internetService.plan.name}</div>
                                        <div class="text-danger">${internetService.status}</div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
            <div class="col-lg-4 col-md-6">
                <c:set var = "isTvServiceActive" value = "${tvService.status eq 'Active'}" />
                <c:choose>
                    <c:when test="${isTvServiceActive}">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <!--i class="fa fa-tasks fa-5x"></i-->
                                        <img src="img/tv.png" height="70" width="70"/>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="big"><strong>${tvService.plan.type.name}</strong></div>
                                        <div>${tvService.plan.name}</div>
                                        <div class="text-success">${tvService.status}</div>
                                    </div>
                                </div>
                            </div>
                            <a href="${pageContext.request.contextPath}/tv">
                                <div class="panel-footer">
                                    <span class="pull-left">View Details</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <!--i class="fa fa-tasks fa-5x"></i-->
                                        <img src="img/tv.png" height="70" width="70"/>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="big"><strong>${tvService.plan.type.name}</strong></div>
                                        <div>${tvService.plan.name}</div>
                                        <div class="text-danger">${tvService.status}</div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
            <div class="col-lg-4 col-md-6">
                <c:set var = "isPhoneServiceActive" value = "${phoneService.status eq 'Active'}" />
                <c:choose>
                    <c:when test="${isPhoneServiceActive}">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <!--i class="fa fa-shopping-cart fa-5x"></i-->
                                        <img src="img/phone.png" height="70" width="70"/>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="big"><strong>${phoneService.plan.type.name}</strong></div>
                                        <div>${phoneService.plan.name}</div>
                                        <div class="text-success">${phoneService.status}</div>
                                    </div>
                                </div>
                            </div>
                            <a href="${pageContext.request.contextPath}/phone">
                                <div class="panel-footer">
                                    <span class="pull-left">View Details</span>
                                    <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <!--i class="fa fa-shopping-cart fa-5x"></i-->
                                        <img src="img/phone.png" height="70" width="70"/>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="big"><strong>${phoneService.plan.type.name}</strong></div>
                                        <div>${phoneService.plan.name}</div>
                                        <div class="text-danger">${phoneService.status}</div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>

                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 col-md-6">
                <button type="button" class="btn btn-default btn-block">Internet Plans</button>
            </div>
            <div class="col-lg-4 col-md-6">
                <button type="button" class="btn btn-default btn-block">TV Plans</button>
            </div>
            <div class="col-lg-4 col-md-6">
                <a href="${pageContext.request.contextPath}/services?typeid=102">
                    <button type="button" class="btn btn-default btn-block">Phone Plans</button>
                </a>
            </div>
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Support</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <br/>
        <!-- /.row -->
        <jsp:include page="_support.jsp"></jsp:include>
        <!-- /.row -->
        <%--<div class="row">--%>
            <%--<div class="col-lg-12">--%>
                <%--<h1 class="page-header">Contact us in social networks</h1>--%>
            <%--</div>--%>
            <%--<!-- /.col-lg-12 -->--%>
        <%--</div>--%>
        <!-- /.row -->
        <%--<div class="row">--%>
            <%--<div class="col-lg-12">--%>
                <%--<div class="text-center">--%>
                    <%--<a class="btn btn-social-icon btn-facebook"><i class="fa fa-facebook"></i></a>--%>
                    <%--<a class="btn btn-social-icon btn-instagram"><i class="fa fa-instagram"></i></a>--%>
                    <%--<a class="btn btn-social-icon btn-twitter"><i class="fa fa-twitter"></i></a>--%>
                    <%--<a class="btn btn-social-icon btn-vk"><i class="fa fa-vk"></i></a>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <!-- /.row -->
    </div>
    <!-- /.container-fluid -->
</div>
<!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<jsp:include page="_bottom.jsp"></jsp:include>

</body>

</html>
