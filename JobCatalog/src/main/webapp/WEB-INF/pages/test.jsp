<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title><spring:message code="test.job.catalog"/></title>
</head>
<body>
<h3><spring:message code="test.job.catalog"/></h3>
<br/>
<a href="<c:url value="/company/list"/>" target="_blank"><spring:message code="test.company"/></a>
<br/>
<br/>
Current locale: <spring:message code="test.current.lang"/>
<br/>
<br/>
Test e-mail: ${testEmail}
<br/>
<br/>
Change localization:
<br/><a href="test?lang=en_US">To EN</a>
<br/><a href="test?lang=ru_RU">To RU</a>
</body>
</html>