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
                <li><c:out value="${currentLang}"/></li>
                <li>Current locale: <spring:message code="test.current.lang"/></li>
                <li><a href="../test">Test</a></li>
            </ul>
        </li>
        <li>
            <h2><spring:message code="menu.side.company"/></h2>
            <ul>
                <li><a href="../company/list"><spring:message code="menu.side.company.list"/></a></li>
                <li><a href="../company/add"><spring:message code="menu.side.company.create"/></a></li>
                <li><a href="../company/find"><spring:message code="menu.side.company.find"/></a></li>
            </ul>
        </li>
        <li>
            <h2><spring:message code="menu.side.position"/></h2>
            <ul>
                <li><a href="../position/list"><spring:message code="menu.side.position.list"/></a></li>
                <li><a href="../position/add"><spring:message code="menu.side.position.create"/></a></li>
                <li><a href="#"><spring:message code="menu.side.position.find"/></a></li>
            </ul>
        </li>
        <li>
            <h2><spring:message code="menu.side.skills"/></h2>
            <ul>
                <li><a href="../skill/list"><spring:message code="menu.side.skills.list"/></a></li>
                <li><a href="../skill/add"><spring:message code="menu.side.skills.create"/></a></li>
                <li><a href="#"><spring:message code="menu.side.skills.5mostrequired"/></a></li>
            </ul>
        </li>
    </ul>
</div>