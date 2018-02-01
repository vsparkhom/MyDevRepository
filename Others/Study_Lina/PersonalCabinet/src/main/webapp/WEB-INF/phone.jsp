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
                        <strong>Phone</strong>
                    </div>
                    <div class="panel-body">
                        <p>Service name: <strong>${service.name}</strong></p>
                        <p>Price: <strong>$${service.price}</strong></p>
                        <p>Talk monthly limit (mins): <strong>${service.talkLimit}</strong></p>
                        <p>Data monthly limit (GB): <strong>${service.dataLimit}</strong></p>
                        <p>Voice Mail: <strong>${service.voiceMail}</strong></p>
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
                        <%--<p>Phone HW Device: <strong>Phone Device #456</strong></p>--%>
                        <c:forEach items="${serviceHardware}" var="pieceOfHw" >
                            <p>Phone device: <strong>${pieceOfHw.name}</strong></p>
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
    </div>
    <!-- /.container-fluid -->
</div>
<!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<jsp:include page="_bottom.jsp"></jsp:include>

</body>

</html>
