<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page session="false" %>--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by TEMPLATED
http://templated.co
Released for free under the Creative Commons Attribution License
Name       : Captive Green
Description: A two-column, fixed-width design with dark color scheme.
Version    : 1.0
Released   : 20111225
-->

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
		<div id="header" class="container">
			<div id="logo">
				<h1><a href="#">Job <span>Catalog</span></a></h1>
			</div>
			<div id="menu">
                <ul>
                    <li class="current_page_item"><a href="#"><spring:message code="menu.top.homepage"/></a></li>
                    <li><a href="#"><spring:message code="menu.top.topcompanies"/></a></li>
                    <li><a href="#"><spring:message code="menu.top.toppositions"/></a></li>
                    <li><a href="#"><spring:message code="menu.top.topskills"/></a></li>
                </ul>
			</div>
		</div>
        <div id="banner">
            <div id="lang">
                <c:set var="currentLang">
                    <spring:message code="global.current.locale"/>
                </c:set>
                <%--<c:out value="${currentLang}"/>--%>
                <c:if test="${currentLang eq 'RU'}">
                    <a href="../company/list?lang=en_US" title="<spring:message code="global.flag.alt"/>"><img src="<spring:message code="global.flag.en"/>"/></a>
                </c:if>
                <c:if test="${currentLang eq 'EN'}">
                    <a href="../company/list?lang=ru_RU" title="<spring:message code="global.flag.alt"/>"><img src="<spring:message code="global.flag.ru"/>"/></a>
                </c:if>
            </div>
        </div>
		<!-- end #header -->
		<div id="page">
			<div id="content">

                <h1>Add Company</h1>

                <!-- Company add form -->

                <c:url var="addCompanyAction" value="/company/add" />

                <springform:form action="${addCompanyAction}" commandName="company">
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
                                <springform:label path="employeeCount">
                                    <spring:message text="Employee Count" />
                                </springform:label>
                            </td>
                            <td>
                                <springform:input path="employeeCount" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <springform:label path="address">
                                    <spring:message text="Address" />
                                </springform:label>
                            </td>
                            <td>
                                <springform:input path="address" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" value="<spring:message text="Add Company" />" />
                            </td>
                        </tr>
                    </table>
                </springform:form>


				<div style="clear: both;">&nbsp;</div>
			</div>
			<!-- end #content -->
			<div id="sidebar">
				<ul>
					<!--<li>
						<div id="search" >
							<form method="get" action="#">
								<div>
									<input type="text" name="s" id="search-text" value="" />
									<input type="submit" id="search-submit" value="GO" />
								</div>
							</form>
						</div>
						<div style="clear: both;">&nbsp;</div>
					</li>-->
                    <li>
                        <ul>
                            <li>Current locale: <spring:message code="test.current.lang"/></li>
                            <li><a href="../test">Test</a></li>
                        </ul>
                    </li>
                    <li>
                        <h2><spring:message code="menu.side.company"/></h2>
                        <ul>
                            <li><a href="../company/add"><spring:message code="menu.side.company.create"/></a></li>
                            <li><a href="../company/list"><spring:message code="menu.side.company.list"/></a></li>
                        </ul>
                    </li>
                    <li>
                        <h2><spring:message code="menu.side.position"/></h2>
                        <ul>
                            <li><a href="#"><spring:message code="menu.side.position.create"/></a></li>
                            <li><a href="#"><spring:message code="menu.side.position.list"/></a></li>
                            <li><a href="#"><spring:message code="menu.side.position.find"/></a></li>
                        </ul>
                    </li>
                    <li>
                        <h2><spring:message code="menu.side.skills"/></h2>
                        <ul>
                            <li><a href="#"><spring:message code="menu.side.skills.create"/></a></li>
                            <li><a href="#"><spring:message code="menu.side.skills.list"/></a></li>
                        </ul>
                    </li>
				</ul>
			</div>
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
