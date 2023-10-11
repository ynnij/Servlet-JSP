<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
div {
	height:100vh;
	display:flex;
	flex-direction: column;
	justify-content:center;
	align-item:center;
	text-align:center;
}

</style>
<body>
<%
String errmsg = (String)request.getAttribute("error");
%>
	<div>
	<h2>데이터 입력 실패</h2>
	<%
	if(errmsg==null){
	%>
		<p>모든 항목을 입력해주세요.</p>
	<%
	}
	else if(errmsg.substring(0,9).compareTo("Duplicate")==0){
	%>
		<p>중복된 아이디가 존재합니다.</p>
	<%
	}
	%>
	<a href="InsertForm.jsp">되돌아가기</a>
	</div>
</body>
</html>