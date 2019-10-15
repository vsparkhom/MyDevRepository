<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="true" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="${sessionScope.lang}">

<jsp:include page="_header.jsp"></jsp:include>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><fmt:message key="label.login.1" /></h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="${pageContext.request.contextPath}/login" method="POST">
                            <fieldset>
                                <div class="form-group" >
                                    <!-- if type="email" then JS validation will be enabled -->
                                    <input class="form-control" placeholder="Login" name="login" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Password" name="password" type="password" value="">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="yes"><fmt:message key="label.login.2" />
                                    </label>
                                </div>
                                <button type="submit" class="btn btn-lg btn-success btn-block"><fmt:message key="label.login.3" /></button>
                                <div style="margin-top: 10px; color: red;">
                                    ${errorString}
                                </div>
                                <div id="login_flags">
                                    <a href="?sessionLocale=en">
                                        <img src="img/en.png" />
                                    </a>
                                    <a href="?sessionLocale=ru">
                                        <img src="img/ru.png" />
                                    </a>
                                    <a href="?sessionLocale=ua">
                                        <img src="img/ua.png" />
                                    </a>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="_bottom.jsp"></jsp:include>

</body>

</html>
