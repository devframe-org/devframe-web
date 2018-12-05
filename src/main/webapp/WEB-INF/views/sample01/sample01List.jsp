<h1>Sample01</h1><br/>
<%
	session.setAttribute("aaa", "111");
%>

aaa : <%= session.getAttribute("aaa") %><br/>
foo : <%= session.getAttribute("foo") %>
