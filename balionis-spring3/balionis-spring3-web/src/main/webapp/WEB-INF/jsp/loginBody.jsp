<%@ include file="/WEB-INF/jsp/include.jsp" %>

<h1>Login Form:</h1>

<c:if test="${not empty error}">
  <p>Invalid credentials. Try myuser/mypass.</p>
</c:if>

<form name="f" action="<c:url value='j_spring_security_check'/>" method="POST">

<p>
Username<br/>
<input type='text' name='j_username' /><br/>
Password<br/>
<input type='password' name='j_password'><br/>
<br/>
<input type="submit" class="button" value="Login"/>

</p>

</form>