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
        <%--<div class="row">--%>
            <%--<div class="col-lg-12">--%>
                <%--<h1 class="page-header">Billing</h1>--%>
            <%--</div>--%>
            <%--<!-- /.col-lg-12 -->--%>
        <%--</div>--%>
        <br/>
        <!-- /.row -->
        <jsp:include page="_mybill.jsp"></jsp:include>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <strong>Payments History</strong>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Period Start</th>
                                <th>Period End</th>
                                <th>Pay Before</th>
                                <th>Sum</th>
                                <th>Status</th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${payments}" var="payment" >
                                    <tr class="gradeA">
                                        <td class="center">${payment.id}</td>
                                        <td>${payment.periodStart}</td>
                                        <td>${payment.periodEnd}</td>
                                        <td>${payment.payBefore}</td>
                                        <td>${payment.sum}</td>
                                        <td class="center">${payment.status}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            </table>
                        </div>
                    <!-- /.table-responsive -->
                    </div>
                <!-- /.panel-body -->
                </div>
            <!-- /.panel -->
            </div>
        <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->

    </div>
    <!-- /.container-fluid -->
</div>
<!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<jsp:include page="_bottom.jsp"></jsp:include>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
    $(document).ready(function() {
        $('#dataTables-example').dataTable();
    });
</script>

</body>

</html>
