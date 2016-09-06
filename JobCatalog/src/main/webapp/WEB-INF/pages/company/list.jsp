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

                <h1>Companies List</h1>

                <div>
                    <c:if test="${!empty listCompanies}">
                        <table>
                            <tr>
                                <th width="80">ID</th>
                                <th width="150">Name</th>
                                <th width="150">Description</th>
                                <th width="60">Action</th>
                            </tr>
                            <c:forEach items="${listCompanies}" var="company">
                                <tr>
                                    <td>${company.id}</td>
                                    <td><a href="<c:url value='../company/get/${company.id}'/>">${company.name}</a></td>
                                    <td>${company.description}</td>
                                    <td><a href="<c:url value='../company/edit/${company.id}'/>"><span style="color:blue;">Edit</span></a>/<a href="<c:url value='../company/remove/${company.id}'/>"><span style="color:red;">Delete</span></a></td>
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
				</div>
				<div class="post">
					<h2 class="title"><a href="#">Lorem ipsum sed aliquam</a></h2>
					<p class="meta"><span class="date">December 24, 2011</span><span class="posted">Posted by <a href="#">Someone</a></span></p>
					<div style="clear: both;">&nbsp;</div>
					<div class="entry">
						<p>Sed lacus. Donec lectus. Nullam pretium nibh ut turpis. Nam bibendum. In nulla tortor, elementum vel, tempor at, varius non, purus. Mauris vitae nisl nec metus placerat consectetuer. Donec ipsum. Proin imperdiet est. Phasellus <a href="#">dapibus semper urna</a>. Pellentesque ornare, orci in consectetuer hendrerit, urna elit eleifend nunc, ut consectetuer nisl felis ac diam. Etiam non felis. Donec ut ante. In id eros. Suspendisse lacus turpis, cursus egestas at sem.  Mauris quam enim, molestie in, rhoncus.</p>
						<p class="links"><a href="#" class="more">Read More</a><a href="#" title="b0x" class="comments">Comments</a></p>
					</div>
				</div>
				<div class="post">
					<h2 class="title"><a href="#">Consecteteur hendrerit </a></h2>
					<p class="meta"><span class="date">December 20, 2011</span><span class="posted">Posted by <a href="#">Someone</a></span></p>
					<div style="clear: both;">&nbsp;</div>
					<div class="entry">
						<p>Sed lacus. Donec lectus. Nullam pretium nibh ut turpis. Nam bibendum. In nulla tortor, elementum vel, tempor at, varius non, purus. Mauris vitae nisl nec metus placerat consectetuer. Donec ipsum. Proin imperdiet est. Phasellus <a href="#">dapibus semper urna</a>. Pellentesque ornare, orci in consectetuer hendrerit, urna elit eleifend nunc, ut consectetuer nisl felis ac diam. Etiam non felis. Donec ut ante. In id eros. Suspendisse lacus turpis, cursus egestas at sem.  Mauris quam enim, molestie in, rhoncus.</p>
						<p class="links"><a href="#" class="more">Read More</a><a href="#" title="b0x" class="comments">Comments</a></p>
					</div>
				</div>-->
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
