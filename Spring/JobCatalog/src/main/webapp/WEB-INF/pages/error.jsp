<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Default Error Page</title>
</head>
<body>

<h2>Exception</h2>
<p>${exception.message}</p>

<p><b>Cause:</b></p>
<p>${exception.cause}</p>

<p><b>Stack Trace:</b></p>
<%--<p>${exception.stackTrace}</p>--%>
<c:forEach items="${exception.stackTrace}" var="stackTraceItem">
${stackTraceItem}<br/>
</c:forEach>

</body>
</html>