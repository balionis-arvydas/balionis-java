<%@ include file="/WEB-INF/jsp/include.jsp" %>

<c:set var="action1"><c:url value="/model"/></c:set>

<h1>Model Form:</h1>

<form:form id="form1" method="post" action="${action1}" modelAttribute="modelForm">

<p>
Name<br/>
<form:input path="name" cssClass="entry" cssErrorClass="entry error"/><br/>
Type<br/>
<form:select path="type" cssClass="entry" cssErrorClass="entry error">
    <form:option value="">- Please select -</form:option>
    <form:options items="${typeOptions}"/>  
</form:select><br/>
<input type="submit" class="button" value="Save"/>
</p>

</form:form>

<form:errors cssClass="error" element="p"/>

<c:choose>
<c:when test="${empty models}">
  <p>No models found.</p>
</c:when>
<c:otherwise>
<p>
<table border="1">
  <tr>
    <td>Key</td>
    <td>Name</td>
    <td>Type</td>
  </tr>
<c:forEach var="model" items="${models}" varStatus="forStatus">
  <tr class="${forStatus.index % 2 == 0 ? 'even' : 'odd'}">
    <td><c:out value="${model.id.key}"/></td>
    <td><c:out value="${model.name}"/></td>
    <td><c:out value="${model.type}"/></td>
  </tr>
</c:forEach>
</table>
</p>
</c:otherwise>
</c:choose>