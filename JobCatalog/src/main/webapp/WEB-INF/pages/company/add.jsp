<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springform" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>

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
					<li class="current_page_item"><a href="#">Homepage</a></li>
					<li><a href="#">Top Companies</a></li>
					<<li><a href="#">Top Positions</a></li>
					<li><a href="#">Top skills</a></li>
				</ul>
			</div>
		</div>
		<div id="banner"></div>
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
					<!--<li>
						<h2>Aliquam tempus</h2>
						<p>Mauris vitae nisl nec metus placerat perdiet est. Phasellus dapibus semper consectetuer hendrerit.</p>
					</li>-->
					<li>
						<h2>Company</h2>
						<ul>
							<li><a href="../company/add">Create company account</a></li>
                            <li><a href="../company/list">List all companies</a></li>
							<li><a href="#">Edit/Remove company(TODO)</a></li>
						</ul>
					</li>
					<li>
						<h2>Position</h2>
						<ul>
							<li><a href="#">Create position</a></li>
                            <li><a href="#">List all positions</a></li>
							<li><a href="#">Find positions by criteria</a></li>
						</ul>
					</li>
					<li>
						<h2>Skills</h2>
						<ul>
							<li><a href="#">Create skill</a></li>
							<li><a href="#">List all skills</a></li>
							<li><a href="#">Edit/Remove skill (TODO)</a></li>
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
