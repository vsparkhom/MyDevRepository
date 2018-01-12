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
        <a class="navbar-brand" href="${pageContext.request.contextPath}/main">TKS Личный кабинет</a>
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
                    <div>
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
                <li class="active">
                    <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Services<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a href="${pageContext.request.contextPath}/internet">Internet</a>
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
        <br/>
        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <strong>Internet</strong>
                    </div>
                    <div class="panel-body">
                        <p>Download speed: <strong>75 Mb/s</strong></p>
                        <p>Upload speed: <strong>15 Mb/s</strong></p>
                        <p>Monthly data limit <strong>500 GB</strong></p>
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
                        <p>Internet modem: <strong>Cisco HW 123</strong></p>
                    </div>
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
                        <script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
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
                                    "title": "New",
                                    "value": 4852
                                }, {
                                    "title": "Returning",
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
