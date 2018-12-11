<script>

$(document).ready(function() {

	//Json 문자열 파라미터
	//public @ResponseBody Map<String, Object> save1(@RequestBody Sample01VO sample01VO) throws Exception
	//RequestWrapper
	//String contentType = request.getContentType();	contentType : application/json;charset=UTF-8
	//성공
	$("#saveBtn1").click(function() {
		$.ajax({
			type: "POST"
			, dataType: "json"
			//@RequestBody 동작을 위해 반드시 설정해야 함, 안 하면 415 응답 에러 발생
			, contentType: "application/json;charset=UTF-8"
			, url: "/sample01/save1"
			, data: JSON.stringify({nm:"<1", ctx:'2'})
			, success: function(data) {
				alert("success\n" + JSON.stringify(data));
			}, error: function(data) {
				alert("error");
			}
		});
	});

	//Json 문자열 파라미터
	//public @ResponseBody Map<String, Object> save2(@RequestBody Map<String, Object> paramMap) throws Exception
	//RequestWrapper
	//String contentType = request.getContentType();	contentType : application/json;charset=UTF-8
	//성공
	$("#saveBtn2").click(function() {
		$.ajax({
			type: "POST"
			, dataType: "json"
			//@RequestBody 동작을 위해 반드시 설정해야 함, 안 하면 415 응답 에러 발생
			, contentType: "application/json;charset=UTF-8"
			, url: "/sample01/save2"
			, data: JSON.stringify({nm:"<1", ctx:'2'})
			, success: function(data) {
				alert("success\n" + JSON.stringify(data));
			}, error: function(data) {
				alert("error");
			}
		});
	});

	//Json 객체 파라미터
	//public @ResponseBody Map<String, Object> save3(Sample01VO sample01VO) throws Exception
	//RequestWrapper2
	//String contentType = request.getContentType();	contentType : application/x-www-form-urlencoded; charset=UTF-8
	//성공
	$("#saveBtn3").click(function() {
		$.ajax({
			type: "POST"
			, dataType: "json"
			//파라미터를 받기 위해 반드시 제거해야 함
			//, contentType: "application/json;charset=UTF-8"
			, url: "/sample01/save3"
			, data: {nm:"<1", ctx:'2'}
			, success: function(data) {
				alert("success\n" + JSON.stringify(data));
			}, error: function(data) {
				alert("error");
			}
		});
	});

	//Json 객체 파라미터
	//public @ResponseBody Map<String, Object> save4(Map<String, Object> paramMap) throws Exception
	//Map 형태의 파라미터 바인딩은 파라미터를 받지 못함
	//RequestWrapper2, RequestWrapper
	//실패
	$("#saveBtn4").click(function() {
		$.ajax({
			type: "POST"
			, dataType: "json"
			//@RequestBody 동작을 위해 반드시 설정해야 함, 안 하면 415 응답 에러 발생
			, contentType: "application/json;charset=UTF-8"
			, url: "/sample01/save4"
			, data: {nm:"<1", ctx:'2'}
			, success: function(data) {
				alert("success\n" + JSON.stringify(data));
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
|
<button id="saveBtn3">Save3</button>
<button id="saveBtn4">Save4</button>
