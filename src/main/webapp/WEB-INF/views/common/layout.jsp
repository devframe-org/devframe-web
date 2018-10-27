<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!doctype html>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title></title>

<c:import url="/WEB-INF/views/common/lib.jsp" />

</head>

<body>

<tiles:insertAttribute name="header" />
<tiles:insertAttribute name="left" />
<tiles:insertAttribute name="body" />
<tiles:insertAttribute name="footer" />

</body>

</html>