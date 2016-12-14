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

    <!-- TODO: move to separate js file -->
    <script>
        function selectIngredient(select)
        {
            var option = select.options[select.selectedIndex];
            var ul = select.parentNode.getElementsByTagName('ul')[0];

            var choices = ul.getElementsByTagName('input');
            for (var i = 0; i < choices.length; i++)
                if (choices[i].value == option.value)
                    return;

            var li = document.createElement('li');
            var input = document.createElement('input');
            var text = document.createTextNode(option.firstChild.data);

            input.type = 'hidden';
            input.name = 'updatedSkills';
            input.value = option.value;

            li.appendChild(input);
            li.appendChild(text);
            li.setAttribute('onclick', 'this.parentNode.removeChild(this);');

            ul.appendChild(li);
        }
    </script>
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
                        <table class="show-id-form">
                            <tr>
                                <td class="title">ID</td>
                                <td>${position.id}</td>
                            </tr>
                            <tr>
                                <td class="title">Name</td>
                                <td>${position.name}</td>
                            </tr>
                            <tr>
                                <td class="title">Description</td>
                                <td>${position.description}</td>
                            </tr>
                            <tr>
                                <td class="title">Company</td>
                                <td>${position.company.name}</td>
                            </tr>
                            <tr>
                                <td class="title">Skills</td>
                                <td>
                                    <ul class="no-padding">
                                        <c:forEach var="currentSkill" items="${position.skills}">
                                            <li>${currentSkill.name}</li>
                                        </c:forEach>
                                    </ul>
                                </td>
                            </tr>
                        </table>
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
                                    <springform:label path="company" >
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
                                                <input type="hidden" name="updatedSkills" value="${currentSkill.id}" />
                                                ${currentSkill.name}
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <select onchange="selectIngredient(this);" class="skill-list-options">
                                        <c:forEach var="currentSkill" items="${listSkills}">
                                            <option value="${currentSkill.id}">${currentSkill.name}</option>
                                        </c:forEach>
                                    </select>
                                    <img src="../images/add.png" alt="Add" style="vertical-align: middle;">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" value="<spring:message text="Update Position" />" />
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
