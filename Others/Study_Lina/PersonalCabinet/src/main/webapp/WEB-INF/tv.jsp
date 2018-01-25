<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                        <strong>TV</strong>
                    </div>
                    <div class="panel-body">
                        <p>Plan: <strong>Medium TV</strong></p>
                        <p>Channels count: <strong>25</strong></p>
                        <p>UHD support: <strong>On</strong></p>
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
                        <p>TV HW Device: <strong>TV Device #123</strong></p>
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
    </div>
    <!-- /.container-fluid -->
</div>
<!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<jsp:include page="_bottom.jsp"></jsp:include>

</body>

</html>
