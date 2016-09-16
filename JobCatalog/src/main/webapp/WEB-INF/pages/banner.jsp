<div id="banner">
    <div id="lang">
        <c:set var="currentLang">
            <spring:message code="global.current.locale"/>
        </c:set>
        <%--<c:out value="${currentLang}"/>--%>
        <c:if test="${currentLang eq 'RU'}">
            <a href="?lang=en_US" title="<spring:message code="global.flag.alt"/>"><img src="<spring:message code="global.flag.en"/>"/></a>
        </c:if>
        <c:if test="${currentLang eq 'EN'}">
            <a href="?lang=ru_RU" title="<spring:message code="global.flag.alt"/>"><img src="<spring:message code="global.flag.ru"/>"/></a>
        </c:if>
    </div>
</div>
