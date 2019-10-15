<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page session="true" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages"/>

<div class="row">
    <div class="col-lg-6">
        <table width="320">
            <tr>
                <td><span style="font-weight: bold; font-size: 16px; color: #0083b6;">${internetSupportInfo.categoryName}</span></td>
                <td></td>
            </tr>
            <tr>
                <td rowspan="2" width="75"> <img src="img/icon-internet.png" /></td>
                <td><strong><fmt:message key="label.support.1" />:</strong> ${internetSupportInfo.phoneNumber}</td>
            </tr>
            <tr>
                <td><strong><fmt:message key="label.support.2" />:</strong> ${internetSupportInfo.email}</td>
            </tr>
        </table>
        <br/>
    </div>
    <!-- /.col-lg-6 -->
    <div class="col-lg-6">
        <table width="320">
            <tr>
                <td><span style="font-weight: bold; font-size: 16px; color: #0083b6;"> &nbsp;&nbsp;&nbsp; ${tvSupportInfo.categoryName} </span></td>
                <td></td>
            </tr>
            <tr>
                <td rowspan="2" width="75"> <img src="img/icon-tv.png" /></td>
                <td><strong><fmt:message key="label.support.1" />:</strong> ${tvSupportInfo.phoneNumber}</td>
            </tr>
            <tr>
                <td><strong><fmt:message key="label.support.2" />:</strong> ${tvSupportInfo.email}</td>
            </tr>
        </table>
        <br/>
    </div>
    <!-- /.col-lg-6 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-6">
        <table width="320">
            <tr>
                <td><span style="font-weight: bold; font-size: 16px; color: #0083b6;"> ${phoneSupportInfo.categoryName} </span></td>
                <td></td>
            </tr>
            <tr>
                <td rowspan="2" width="75"> <img src="img/icon-phone.png" /></td>
                <td><strong><fmt:message key="label.support.1" />:</strong> ${phoneSupportInfo.phoneNumber}</td>
            </tr>
            <tr>
                <td><strong><fmt:message key="label.support.2" />:</strong> ${phoneSupportInfo.email}</td>
            </tr>
        </table>
        <br/>
    </div>
    <!-- /.col-lg-6 -->
    <div class="col-lg-6">
        <table width="320">
            <tr>
                <td colspan="2"><span style="font-weight: bold; font-size: 16px; color: #0083b6;"> ${billingSupportInfo.categoryName} </span></td>
                <td></td>
            </tr>
            <tr>
                <td rowspan="2" width="75"> <img src="img/icon-billing.png" /></td>
                <td><strong><fmt:message key="label.support.1" />:</strong> ${billingSupportInfo.phoneNumber}</td>
            </tr>
            <tr>
                <td><strong><fmt:message key="label.support.2" />:</strong> ${billingSupportInfo.email}</td>
            </tr>
        </table>
        <br/>
    </div>
    <!-- /.col-lg-6 -->
</div>
<hr>
<div class="text-center">
    <a class="btn btn-social-icon btn-facebook" href="${facebookSupportInfo.link}"><i class="fa fa-facebook"></i></a>
    <a class="btn btn-social-icon btn-instagram" href="${vkSupportInfo.link}"><i class="fa fa-instagram"></i></a>
    <a class="btn btn-social-icon btn-twitter"><i class="fa fa-twitter"></i></a>
    <a class="btn btn-social-icon btn-vk"><i class="fa fa-vk"></i></a>
</div>