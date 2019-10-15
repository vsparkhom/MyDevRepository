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
                        <strong><fmt:message key="label.billing.1" /></strong>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                            <thead>
                            <tr>
                                <th><fmt:message key="label.billing.table.1" /></th>
                                <th><fmt:message key="label.billing.table.2" /></th>
                                <th><fmt:message key="label.billing.table.3" /></th>
                                <th><fmt:message key="label.billing.table.4" /></th>
                                <th><fmt:message key="label.billing.table.5" /></th>
                                <th><fmt:message key="label.billing.table.6" /></th>
                            </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${payments}" var="payment" >
                                    <tr class="gradeA">
                                        <td class="center">${payment.id}</td>
                                        <td>${payment.periodStart}</td>
                                        <td>${payment.periodEnd}</td>
                                        <td>${payment.dueDate}</td>
                                        <td>${payment.sum}</td>
                                        <td class="center"><fmt:message key="${payment.status.value}" /></td>
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
