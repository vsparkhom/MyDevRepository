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
</head>
<body>
<div id="wrapper">
	<div id="wrapper2">
        <%@include file="../header.jsp" %>
        <%@include file="../banner.jsp" %>
		<!-- end #header -->
		<div id="page">
			<div id="content">

                <c:if test="${!editMode}">
                    <h1>Position Details</h1>
                    <div>
                            <%--<c:if test="${!empty position.name}">--%>
                        <table>
                            <tr>
                                <th width="80">ID</th>
                                <th width="150">Name</th>
                                <th width="150">Description</th>
                                <th width="150">Company</th>
                            </tr>
                            <tr>
                                <td>${position.id}</td>
                                <td>${position.name}</td>
                                <td>${position.description}</td>
                                <td>${position.company.name}</td>
                            </tr>
                        </table>
                            <%--</c:if>--%>
                    </div>
                </c:if>

                <c:if test="${editMode}">
                    <h1>Edit Position Details</h1>
                    <c:url var="editPositionAction" value="../position/edit" />
                    <springform:form action="${editPositionAction}" commandName="position">
                        <table>
                            <tr>
                                <td>
                                    <springform:label path="id">
                                        <spring:message text="ID"/>
                                    </springform:label>
                                </td>
                                <td>
                                    <springform:input path="id" readonly="true" size="8" disabled="true"/>
                                    <springform:hidden path="id"/>
                                </td>
                            </tr>
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
                                <td colspan="2">
                                    <input type="submit" value="<spring:message text="Edit Position" />" />
                                </td>
                            </tr>
                        </table>
                    </springform:form>
                </c:if>

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
