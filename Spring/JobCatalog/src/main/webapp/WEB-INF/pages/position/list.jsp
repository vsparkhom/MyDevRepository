<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springform" %>

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

                <h1><spring:message code="position.list.title" text="Positions List" /></h1>

                <h4><a href="../report/position/list.xls"><spring:message code="position.list.download" text="Download as spreadsheet" /></a></h4>

                <div>
                    <c:if test="${!empty listPositions}">
                        <table>
                            <tr>
                                <th width="80"><spring:message code="position.list.table.head.id" text="ID" /></th>
                                <th width="150"><spring:message code="position.list.table.head.name" text="Name" /></th>
                                <th width="130"><spring:message code="position.list.table.head.action" text="Action" /></th>
                            </tr>
                            <c:forEach items="${listPositions}" var="position">
                                <tr>
                                    <td>${position.id}</td>
                                    <td><a href="<c:url value='../position/get?id=${position.id}'/>">${position.name}</a></td>
                                    <td>
                                        <a href="<c:url value='../position/edit?id=${position.id}'/>">
                                            <span style="color:blue;"><spring:message code="position.list.table.edit" text="Edit" /></span>
                                        </a>/
                                        <a href="<c:url value='../position/remove?id=${position.id}'/>">
                                            <span style="color:red;"><spring:message code="position.list.table.delete" text="Delete" /></span>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                </div>

				<!--<div class="post">
					<h2 class="title"><a href="#">Welcome to Captive Green </a></h2>
					<p class="meta"><span class="date">December 26, 2011</span><span class="posted">Posted by <a href="#">Someone</a></span></p>
					<div style="clear: both;">&nbsp;</div>
					<div class="entry">
						<p>This is <strong>Captive Green </strong>, a free, fully standards-compliant CSS template designed by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>. The photo used in this template is form <a href="http://pdphoto.org/">pdphoto.org</a>. This free template is released under the <a href="http://templated.co/license">Creative Commons Attribution</a> license, so you’re pretty much free to do whatever you want with it (even use it commercially) provided you give us credit for it. Have fun :)</p>
						<p>Sed lacus. Donec lectus. Nullam pretium nibh ut turpis. Nam bibendum. In nulla tortor, elementum ipsum. Proin imperdiet est. Phasellus dapibus semper urna. Pellentesque ornare, orci in felis. Donec ut ante. In id eros. Suspendisse lacus turpis, cursus egestas at sem.</p>
						<p class="links"><a href="#" class="more">Read More</a><a href="#" title="b0x" class="comments">Comments</a></p>
					</div>
				</div>-->

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
