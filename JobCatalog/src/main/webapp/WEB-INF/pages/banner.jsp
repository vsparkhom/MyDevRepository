<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript" src="../jquery-3.1.0.min.js"></script>
<script type="text/javascript">
    function getIdFromLocation(lang){
        var loc = location.href;
        var id="";
        if (loc.indexOf('id=') != -1) {
            id = loc.match(/id=(.*?)(&|$)/)[1];
            window.location.href = "?id=" + id + "&lang=" + lang;
        } else {
            window.location.href = "?lang=" + lang;
        }
    }
</script>

<div id="banner">
    <div class="banner_right">
        <div id="login">
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.authenticated}">
                    <security:authentication property="principal.username" />
                    | <a href="../logout"><spring:message code="login.exit"/></a>
                </c:when>
                <c:otherwise>
                    <a href="../login"><spring:message code="login.enter"/></a>
                </c:otherwise>
            </c:choose>
        </div>
        <div id="lang">
            <c:set var="currentLang">
                <spring:message code="global.current.locale"/>
            </c:set>
            <%--<c:out value="${currentLang}"/>--%>
            <c:if test="${currentLang eq 'RU'}">
                <a href="#" onclick="getIdFromLocation('en_EN')" title="<spring:message code="global.flag.alt"/>"><img src="<spring:message code="global.flag.en"/>"/></a>
            </c:if>
            <c:if test="${currentLang eq 'EN'}">
                <a href="#" onclick="getIdFromLocation('ru_RU')" title="<spring:message code="global.flag.alt"/>"><img src="<spring:message code="global.flag.ru"/>"/></a>
            </c:if>
        </div>
    </div>
</div>
