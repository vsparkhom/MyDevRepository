<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <strong>My Bill</strong>
            </div>
            <div class="panel-body">
                <p>Your current balance due is <strong>$${currentBallance}</strong></p>

                <c:set var="isPaymentRequired" value="${dueDate ne null}"/>
                <c:choose>
                    <c:when test="${isPaymentRequired}">
                        <p>Your next payment is due <strong>${dueDate}</strong></p>
                    </c:when>
                    <c:otherwise>
                        <p>The payment is not required yet.</p>
                    </c:otherwise>
                </c:choose>

                <p>Electronic Bill is active. Bills will be sent to
                    <a href="mailto:${authorizedAccount.email}">${authorizedAccount.email}</a>
                </p>
            </div>
            <!--div class="panel-footer">
                Panel Footer
            </div-->
        </div>
    </div>
    <!-- /.col-lg-4 -->
</div>