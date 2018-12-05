<script>

$(document).ready(function() {

	$("#saveBtn1").click(function() {
		$.ajax({
			type: "POST"
			, dataType: "json"
			//@RequestBody 동작을 위해 반드시 설정해야 함, 안 하면 415 응답 에러 발생
			, contentType: "application/json;charset=UTF-8"
			, url: "/sample01/save1"
			, data: JSON.stringify({nm:"<1", ctx:'2'})
			, success: function(data) {
				alert("success");
			}, error: function(data) {
				alert("error");
			}
		});
	});

	$("#saveBtn2").click(function() {
		$.ajax({
			type: "POST"
			, dataType: "json"
			//@RequestBody 동작을 위해 반드시 설정해야 함, 안 하면 415 응답 에러 발생
			, contentType: "application/json;charset=UTF-8"
			, url: "/sample01/save2"
			, data: JSON.stringify({nm:"<1", ctx:'2'})
			, success: function(data) {
				alert("success");
			}, error: function(data) {
				alert("error");
			}
		});
	});

});

</script>

<h1>Sample01</h1><br/>

<%
	session.setAttribute("aaa", "111");
%>

aaa : <%= session.getAttribute("aaa") %><br/>
foo : <%= session.getAttribute("foo") %><br/>

<button id="saveBtn1">Save1</button>
<button id="saveBtn2">Save2</button>

