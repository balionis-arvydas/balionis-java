<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title><tiles:getAsString name="title" ignore="false"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="<c:url value="/resources/app.css"/>" type="text/css"  media="all" />    
</head>
<body>
<div id="header-wrapper" class="container">
    <!-- header (+) -->
    <tiles:insertAttribute name="header"/>
    <!-- header (-) -->
</div>
<div id="wrapper" class="container">
	<!-- body (+) -->
	<tiles:insertAttribute name="body"/>
	<!-- body (-) -->
</div>
<div id="footer-wrapper" class="container">
    <!-- footer (+) -->
    <tiles:insertAttribute name="footer"/>
    <!-- footer (-) -->
	</div>
</div>
</body>
</html>