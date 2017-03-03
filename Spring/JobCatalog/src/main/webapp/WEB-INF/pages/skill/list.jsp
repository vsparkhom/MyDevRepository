<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springform" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page session="false" %>--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Job Catalog</title>
    <link href='http://fonts.googleapis.com/css?family=Ubuntu+Condensed' rel='stylesheet' type='text/css'/>
    <link href='http://fonts.googleapis.com/css?family=Marvel' rel='stylesheet' type='text/css'/>
    <link href='http://fonts.googleapis.com/css?family=Marvel|Delius+Unicase' rel='stylesheet' type='text/css'/>
    <link href='http://fonts.googleapis.com/css?family=Arvo' rel='stylesheet' type='text/css'/>
    <link href="../style.css" rel="stylesheet" type="text/css" media="screen"/>
</head>
<body>
<div id="wrapper">
    <div id="wrapper2">
        <%@include file="../header.jsp" %>
        <%@include file="../banner.jsp" %>
        <!-- end #header -->
        <div id="page">
            <div id="content">

                <h1><spring:message code="skill.list.title" text="Skills List"/></h1>

                <h4><a href="../report/skill/list.xls"><spring:message code="skill.list.download" text="Download as spreadsheet"/></a></h4>

                <div>
                    <c:if test="${!empty listSkills}">
                        <table>
                            <tr>
                                <th width="80"><spring:message code="skill.list.table.head.id" text="ID"/></th>
                                <th width="150"><spring:message code="skill.list.table.head.name" text="Name"/></th>
                                <th width="130"><spring:message code="skill.list.table.head.action" text="Action"/></th>
                            </tr>
                            <c:forEach items="${listSkills}" var="skill">
                                <tr>
                                    <td>${skill.id}</td>
                                    <td>${skill.name}</td>
                                    <td>
                                        <a href="<c:url value='../skill/edit?id=${skill.id}'/>">
                                            <span style="color:blue;"><spring:message code="skill.list.table.edit" text="Edit" /></span>
                                        </a>/
                                        <a href="<c:url value='../skill/remove?id=${skill.id}'/>">
                                            <span style="color:red;"><spring:message code="skill.list.table.delete" text="Delete" /></span>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                </div>

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
