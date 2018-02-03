<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
                <c:forEach items="${servicePlans}" var="servicePlan" >
                    <%--<p>${servicePlan.id}-${servicePlan.name}-${servicePlan.type.name}-${servicePlan.price}</p>--%>
                    <div class="col-lg-${columnWidth}">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                ${servicePlan.name}
                            </div>
                            <div class="panel-body">
                                <p>Price: ${servicePlan.price}</p>
                                <p>Options:</p>
                                <c:forEach var="options" items="${servicePlan.options}">
                                    <c:out value="${options.key}"/>: <c:out value="${options.value}"/>  <br/>
                                </c:forEach>
                            </div>
                            <div class="panel-footer">
                                Panel Footer
                            </div>
                        </div>
                    </div>
                </c:forEach>

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
