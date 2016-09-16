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

                <h1><spring:message code="exception.duplicate.company.name.title" /></h1>

                <div class="post">
                    <%--<h2 class="title"><a href="#">Duplicate Company Name Exception! </a></h2>--%>
                    <%--<p class="meta"><span class="date"><spring:message code="${exception.msgCode}" />${exception.companyName}</span></p>--%>
                    <%--<div style="clear: both;">&nbsp;</div>--%>
                    <div class="entry">
                        <p><span class="date"><spring:message code="exception.duplicate.company.name.text" /><b>${exception.companyName}</b></p>
                    </div>
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
