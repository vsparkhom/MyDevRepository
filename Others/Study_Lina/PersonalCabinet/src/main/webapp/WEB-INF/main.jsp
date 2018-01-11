<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Personal Cabinet</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="css/plugins/metisMenu/metisMenu.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome-4.1.0/css/font-awesome.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="${pageContext.request.contextPath}/main">ТКС Личный кабинет</a>
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
                            <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                        </div>
                        <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="#">
                        <div>
                            <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                        </div>
                        <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="#">
                        <div>
                            <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                        </div>
                        <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                    </a>
                </li>
                <li class="divider"></li>
                <li>
                    <a class="text-center" href="#">
                        <strong>Read All Messages</strong>
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
                <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                </li>
                <!--li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                </li-->
                <li class="divider"></li>
                <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
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
                        <!--h4>Vasia Pupkin</h4><br/-->
                        <h4>Login: ${account.login}</h4><br/>
                        <span>${account.firstName} ${account.lastName}</span><br/>
                        <span>Account Number: ${account.id}</span><br/>
                        <span>Status: ${account.status}</span><br/>
                        <span>Email: ${account.email}</span>
                    </div>
                    <!-- /input-group -->
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/main"><i class="fa fa-dashboard fa-fw"></i> Account
                        Information</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Services<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="flot.html">Internet</a>
                        </li>
                        <li>
                            <a href="morris.html">Television</a>
                        </li>
                        <li>
                            <a href="morris.html">Phone</a>
                        </li>
                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li>
                    <a href="tables.html"><i class="fa fa-table fa-fw"></i> Billing</a>
                </li>
                <li>
                    <a href="forms.html"><i class="fa fa-edit fa-fw"></i> Help</a>
                </li>
            </ul>
        </div>
        <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
</nav>

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
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <strong>My Bill</strong>
                    </div>
                    <div class="panel-body">
                        <p>Your current balance due is <strong>$0.00</strong></p>

                        <p>Your next payment is due <strong>January 29th, 2018</strong></p>

                        <p>Electronic Bill is active. Bills will be sent to <a href="mailto:test@gmail.com">test@gmail</a></p>
                    </div>
                    <!--div class="panel-footer">
                        Panel Footer
                    </div-->
                </div>
            </div>
            <!-- /.col-lg-4 -->
        </div>
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Services</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <br/>
        <div class="row">
            <div class="col-lg-4 col-md-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <!--i class="fa fa-comments fa-5x"></i-->
                                <img src="img/internet.png" height="70" width="70"/>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="big">Internet</div>
                                <div>High Speed 50</div>
                                <div class="text-success">Active</div>
                            </div>
                        </div>
                    </div>
                    <a href="#">
                        <div class="panel-footer">
                            <span class="pull-left">View Details</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>

                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-lg-4 col-md-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <!--i class="fa fa-tasks fa-5x"></i-->
                                <img src="img/tv.png" height="70" width="70"/>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="big">TV</div>
                                <div>Popular TV</div>
                                <div class="text-success">Active</div>
                            </div>
                        </div>
                    </div>
                    <a href="#">
                        <div class="panel-footer">
                            <span class="pull-left">View Details</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>

                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
            </div>
            <div class="col-lg-4 col-md-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-3">
                                <!--i class="fa fa-shopping-cart fa-5x"></i-->
                                <img src="img/phone.png" height="70" width="70"/>
                            </div>
                            <div class="col-xs-9 text-right">
                                <div class="big">Phone</div>
                                <div>Basic</div>
                                <div class="text-danger">Inactive</div>
                            </div>
                        </div>
                    </div>
                    <a href="#">
                        <div class="panel-footer">
                            <span class="pull-left">View Details</span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>

                            <div class="clearfix"></div>
                        </div>
                    </a>
                </div>
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
                <button type="button" class="btn btn-default btn-block">Phone Plans</button>
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
        <div class="row">
            <div class="col-lg-6">
                <table width="320">
                    <tr>
                        <td><span style="font-weight: bold; font-size: 16px; color: #0083b6;"> Internet </span></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td rowspan="2" width="75"> <img src="img/icon-internet.png" /></td>
                        <td><strong>Tel:</strong> (403) 123-0001</td>
                    </tr>
                    <tr>
                        <td><strong>Email:</strong> internet_support@gmail.com</td>
                    </tr>
                </table>
                <br/>
            </div>
            <!-- /.col-lg-6 -->
            <div class="col-lg-6">
                <table width="320">
                    <tr>
                        <td><span style="font-weight: bold; font-size: 16px; color: #0083b6;"> &nbsp;&nbsp;&nbsp; TV </span></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td rowspan="2" width="75"> <img src="img/icon-tv.png" /></td>
                        <td><strong>Tel:</strong> (403) 123-0002</td>
                    </tr>
                    <tr>
                        <td><strong>Email:</strong> tv_support@gmail.com</td>
                    </tr>
                </table>
                <br/>
            </div>
            <!-- /.col-lg-6 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-6">
                <table width="320">
                    <tr>
                        <td><span style="font-weight: bold; font-size: 16px; color: #0083b6;"> Phone </span></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td rowspan="2" width="75"> <img src="img/icon-phone.png" /></td>
                        <td><strong>Tel:</strong> (403) 123-0003</td>
                    </tr>
                    <tr>
                        <td><strong>Email:</strong> phone_support@gmail.com</td>
                    </tr>
                </table>
                <br/>
            </div>
            <!-- /.col-lg-6 -->
            <div class="col-lg-6">
                <table width="320">
                    <tr>
                        <td colspan="2"><span style="font-weight: bold; font-size: 16px; color: #0083b6;"> Billing </span></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td rowspan="2" width="75"> <img src="img/icon-billing.png" /></td>
                        <td><strong>Tel:</strong> (403) 123-0004</td>
                    </tr>
                    <tr>
                        <td><strong>Email:</strong> billing_support@gmail.com</td>
                    </tr>
                </table>
                <br/>
            </div>
            <!-- /.col-lg-6 -->
        </div>
        <hr>
        <div class="text-center">
            <a class="btn btn-social-icon btn-facebook"><i class="fa fa-facebook"></i></a>
            <a class="btn btn-social-icon btn-instagram"><i class="fa fa-instagram"></i></a>
            <a class="btn btn-social-icon btn-twitter"><i class="fa fa-twitter"></i></a>
            <a class="btn btn-social-icon btn-vk"><i class="fa fa-vk"></i></a>
        </div>
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

<!-- jQuery -->
<script src="js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="js/plugins/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="js/sb-admin-2.js"></script>

</body>

</html>
