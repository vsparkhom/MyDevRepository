<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- request.setCharacterEncoding("UTF-8"); --%>

<fmt:setLocale value="${param.lang}" />
<fmt:setBundle basename="messages"/>

<html lang="${param.lang}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
</head>
<body>
    <h1><fmt:message key="label.index.1" /></h1>
    <h2><fmt:message key="label.index.2" />: ${pageContext.request.contextPath}</h2>
    <h4><fmt:message key="label.index.3" /> <a href="login"><fmt:message key="label.index.4" /></a></h4>
</body>
</html>