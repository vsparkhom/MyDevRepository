<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Login page</title>
    <%--<link href="login.css" rel="stylesheet" type="text/css" media="screen" />--%>
    <style media="screen" type="text/css">
        body {
            margin: 0;
            padding: 0;
            background: #EEEBDA repeat-x left top;
        }
        table{
            border-collapse:collapse;
        }
        #login_main {
            margin: auto;
            width: 285px;
            padding: 10px;
            text-align: center;
        }
        #login_form {
            padding: 5px;
            border: 2px solid black;
            background: #8AD500 /*url(img01_TEST.jpg)*/ repeat-x left top;
        }
        #login_form table {
            width: 275px;
            color: black;
            font-weight: bold;
        }
        #login_error {
            color: red;
        }
    </style>
</head>
<body>
<div id="login_main">
    <br/>
    <br/>
    <br/>
    <img src="images/logo.png" />
    <div id="login_form">
        <form name="f" action="/JobCatalog/login" method="POST">
            <table cellpadding="5px">
                <tbody>
                <tr>
                    <td>Username:</td>
                    <td align="right">
                        <input type="text" name="username" value=""/>
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td align="right">
                        <input type="password" name="password"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="right">
                        <input name="submit" type="submit" value="Login"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
    <div id="login_error">
        <c:if test="${not empty param.error}">
            Login error.<br/>
            Reason: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
        </c:if>
    </div>
</div>
</body>
</html>