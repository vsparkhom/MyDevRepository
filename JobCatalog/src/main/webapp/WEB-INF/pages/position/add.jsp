<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page session="false" %>--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Job Catalog</title>
    <link href='http://fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css' />
    <link href='http://fonts.googleapis.com/css?family=Marvel' rel='stylesheet' type='text/css' />
    <link href='http://fonts.googleapis.com/css?family=Marvel|Delius+Unicase' rel='stylesheet' type='text/css' />
    <link href='http://fonts.googleapis.com/css?family=Arvo' rel='stylesheet' type='text/css' />
    <link href="../style.css" rel="stylesheet" type="text/css" media="screen" />

    <script src="../custom.js" type="text/javascript"></script>

</head>
<body>
<div id="wrapper">
	<div id="wrapper2">
        <%@include file="../header.jsp" %>
        <%@include file="../banner.jsp" %>
		<!-- end #header -->
		<div id="page">
			<div id="content">

                <h1>Add Position</h1>

                <!-- Position add form -->

                <c:url var="addPositionAction" value="/position/add" />

                <springform:form action="${addPositionAction}" commandName="position">
                    <table>
                        <tr>
                            <td>
                                <springform:label path="name">
                                    <spring:message text="Name" />
                                </springform:label>
                            </td>
                            <td>
                                <springform:input path="name" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <springform:label path="description">
                                    <spring:message text="Description" />
                                </springform:label>
                            </td>
                            <td>
                                <springform:textarea path="description" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <springform:label path="company">
                                    <spring:message text="Company ID" />
                                </springform:label>
                            </td>
                            <td>
                                <springform:select path="company" items="${listCompanies}" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Skills
                            </td>
                            <td>
                                <ul class="skill-list-options">
                                    <c:forEach var="currentSkill" items="${position.skills}">

                                        <li onclick="this.parentNode.removeChild(this);">
                                            <input type="hidden" name="skillsToAdd" value="${currentSkill.id}" />
                                                ${currentSkill.name}
                                        </li>
                                    </c:forEach>
                                </ul>
                                <select onchange="selectIngredient(this, 'skillsToAdd');" class="skill-list-options">
                                    <c:forEach var="currentSkill" items="${listSkills}">
                                        <option value="${currentSkill.id}">${currentSkill.name}</option>
                                    </c:forEach>
                                </select>
                                <img src="../images/add.png" alt="Add" style="vertical-align: middle;">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" value="<spring:message text="Add Position" />" />
                            </td>
                        </tr>
                    </table>
                </springform:form>


				<div style="clear: both;">&nbsp;</div>
			</div>
			<!-- end #content -->
            <%@include file="../sidebar.jsp" %>
			<!-- end #sidebar -->
			<div style="clear: both;">&nbsp;</div>
		</div>
		<!-- end #page -->
		<div id="footer">
			<p>&copy; JobCatalog. All rights reserved. Design by Voland.</p>
		</div>
	</div>
</div>
<!-- end #footer -->
</body>
</html>
