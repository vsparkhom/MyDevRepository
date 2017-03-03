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
                    <h1><spring:message code="company.id.not_edit_mode.title" text="Company details" /></h1>
                    <div>
                        <table>
                            <tr>
                                <th width="80"><spring:message code="company.id.not_edit_mode.table.head.id" text="ID" /></th>
                                <th width="150"><spring:message code="company.id.not_edit_mode.table.head.name" text="Name" /></th>
                                <th width="150"><spring:message code="company.id.not_edit_mode.table.head.descr" text="Description" /></th>
                                <th width="60"><spring:message code="company.id.not_edit_mode.table.head.emp_count" text="Employee count" /></th>
                                <th width="150"><spring:message code="company.id.not_edit_mode.table.head.address" text="Address" /></th>
                            </tr>
                            <tr>
                                <td>${company.id}</td>
                                <td>${company.name}</td>
                                <td>${company.description}</td>
                                <td>${company.employeeCount}</td>
                                <td>${company.address}</td>
                            </tr>
                        </table>
                    </div>
                    <div>
                        <br/>
                        <h3><spring:message code="company.id.not_edit_mode.listed_positions" text="Listed positions for this company" /></h3>
                        <c:if test="${empty listPositions}">
                            <p><spring:message code="company.id.not_edit_mode.no_positions" text="This company currently doesn't have open positions" /></p>
                        </c:if>
                        <c:if test="${!empty listPositions}">
                            <ul>
                                <c:forEach items="${listPositions}" var="currentPosition">
                                    <li>
                                        <a href="<c:url value='../position/get?id=${currentPosition.id}'/>">[${currentPosition.id}] ${currentPosition.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </div>
                </c:if>

                <c:if test="${editMode}">
                    <h1><spring:message code="company.id.edit_mode.title" text="Edit company details" /></h1>
                    <c:url var="editCompanyAction" value="../company/edit" />
                    <springform:form action="${editCompanyAction}" commandName="company">
                        <table>
                            <tr>
                                <td>
                                    <springform:label path="id">
                                        <spring:message code="company.id.edit_mode.table.id" text="ID"/>
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
                                        <spring:message code="company.id.edit_mode.table.name" text="Name" />
                                    </springform:label>
                                </td>
                                <td>
                                    <springform:input path="name" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <springform:label path="description">
                                        <spring:message code="company.id.edit_mode.table.descr" text="Description" />
                                    </springform:label>
                                </td>
                                <td>
                                    <springform:textarea path="description" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <springform:label path="employeeCount">
                                        <spring:message code="company.id.edit_mode.table.emp_count" text="Employee count" />
                                    </springform:label>
                                </td>
                                <td>
                                    <springform:input path="employeeCount" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <springform:label path="address">
                                        <spring:message code="company.id.edit_mode.table.address" text="Address" />
                                    </springform:label>
                                </td>
                                <td>
                                    <springform:input path="address" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" value="<spring:message code="company.id.edit_mode.table.submit_button" text="Update" />" />
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
            <p><spring:message code="footer.title" text="&copy; JobCatalog. All rights reserved. Design by Voland." /></p>
		</div>
	</div>
</div>
<!-- end #footer -->
</body>
</html>
