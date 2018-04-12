<%@ include file="/WEB-INF/jsp/include.jsp" %>

<p>Your request can not be performed at the moment. Please wait a few moments and try again.<br/>
Please <a href="#">Contact Us</a> if the error shows up again.</p>
<p>
<c:if test="${not empty exceptionCode}">
Reason: <c:out value="${exceptionMessage}"/><br/>
(<c:out value="${exceptionCode}"/>)
</c:if>
</p>