<script>

$(document).ready(function() {
	$("#submitBtn").click(function() {
		$("#loginForm").submit();
	});
});

</script>

<h1>Login</h1>

<form id="loginForm" action="/loginProc" method="post">
	Id : <input type="text" id="userId" name="userId" value="admin01" /><br/>
	Password : <input type="password" id="passwd" name="passwd" value="1234" /><br/>
	<input type="button" id="submitBtn" value="Login" />
</form>
