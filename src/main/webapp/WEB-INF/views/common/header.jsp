<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

Header<br/>
<sec:authorize access="isAnonymous()">
	<a href="/login">Login</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<a href="/logout">Logout</a>
</sec:authorize>
<br/><br/>