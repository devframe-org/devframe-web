Header<br/>
<sec:authorize access="isAnonymous()">
	<a href="/login">Login</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<a href="/logout">Logout</a>
</sec:authorize>
<br/><br/>