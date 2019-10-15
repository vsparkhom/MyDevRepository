<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="true" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages"/>

<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/main">
            <table>
                <tr>
                    <td><img src="img/_logo.png" height="28px" /></td>
                    <td><span style="color:#428bca;">&nbsp;<fmt:message key="label.navigation.1" /></span></td>
                </tr>
            </table>
        </a>
    </div>
    <!-- /.navbar-header -->
    
    <ul class="nav navbar-top-links navbar-right">
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-messages">
                <li>
                    <a href="#">
                        <div>
                            <strong><fmt:message key="label.msg.1.from" /></strong>
                                    <span class="pull-right text-muted">
                                        <em><fmt:message key="label.msg.1.when" /></em>
                                    </span>
                        </div>
                        <div><fmt:message key="label.msg.1.text" /></div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="#">
                        <div>
                            <strong><fmt:message key="label.msg.2.from" /></strong>
                                    <span class="pull-right text-muted">
                                        <em><fmt:message key="label.msg.2.when" /></em>
                                    </span>
                        </div>
                        <div><fmt:message key="label.msg.2.text" /></div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="#">
                        <div>
                            <strong><fmt:message key="label.msg.3.from" /></strong>
                                    <span class="pull-right text-muted">
                                        <em><fmt:message key="label.msg.3.when" /></em>
                                    </span>
                        </div>
                        <div><fmt:message key="label.msg.3.text" /></div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a class="text-center" href="#">
                        <strong><fmt:message key="label.msg.read_all" /></strong>
                        <i class="fa fa-angle-right"></i>
                    </a>
                </li>
            </ul>
            <!-- /.dropdown-messages -->
        </li>
        <!-- /.dropdown -->
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="#"><i class="fa fa-user fa-fw"></i> <fmt:message key="label.navigation.2" /></a>
                </li>
                <!--li><a href="#"><i class="fa fa-gear fa-fw"></i> <fmt:message key="label.navigation.3" /></a>
                </li-->
                <li class="divider"></li>
                <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> <fmt:message key="label.navigation.4" /></a>
                </li>
            </ul>
            <!-- /.dropdown-user -->
        </li>
        <!-- /.dropdown -->
    </ul>
    <!-- /.navbar-top-links -->

    <div class="navbar-default sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
                <li class="sidebar-search">
                    <!--div class="input-group custom-search-form">
                        <input type="text" class="form-control" placeholder="Search...">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                <i class="fa fa-search"></i>
                            </button>
                        </span>
                    </div-->
                    <div>
                        <span style="font-weight:bold; font-size:18px;">${authorizedAccount.firstName} ${authorizedAccount.lastName}</span><br/>
                        <fmt:message key="label.navigation.5" />: <span style="color:#428bca;">${authorizedAccount.login}</span><br/>
                        <fmt:message key="label.navigation.6" />: <span style="color:#428bca;">${authorizedAccount.id}</span><br/>
                        <fmt:message key="label.navigation.7" />: <span style="color:#428bca;"><fmt:message key="${authorizedAccount.status.value}" /></span><br/>
                        <fmt:message key="label.navigation.8" />: <span><a href="mailto:${authorizedAccount.email}">${authorizedAccount.email}</a></span>
                    </div>
                    <!-- /input-group -->
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/main"><i class="fa fa-dashboard fa-fw"></i> <fmt:message key="label.navigation.9" /></a>
                </li>
                <li class="active">
                    <a href=""><i class="fa fa-bar-chart-o fa-fw"></i> <fmt:message key="label.navigation.10" /><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="${pageContext.request.contextPath}/internet"><fmt:message key="label.navigation.11" /></a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/tv"><fmt:message key="label.navigation.12" /></a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/phone"><fmt:message key="label.navigation.13" /></a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/billing"><i class="fa fa-table fa-fw"></i> <fmt:message key="label.navigation.14" /></a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/help"><i class="fa fa-edit fa-fw"></i> <fmt:message key="label.navigation.15" /></a>
                </li>
            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
    
    <div id="nav_flags" class="nav navbar-top-links navbar-right">
        <a href="?sessionLocale=en">
            <img src="img/en.png" />
        </a>
        <a href="?sessionLocale=ru">
            <img src="img/ru.png" />
        </a>
        <a href="?sessionLocale=ua">
            <img src="img/ua.png" />
        </a>
    </div>
    
</nav>