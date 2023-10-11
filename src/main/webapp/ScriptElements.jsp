<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 1. 선언부 -->
<%!public int add(int num1, int num2) {
		return num1 + num2;
	}

	String test = "abcd";%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립트 요소</title>
</head>
<body>
	<!-- 2.자바 코드 -->
	<%
	int result = add(10, 20);
	%>

	덧셈 결과 1 :
	<%=result%>
	<br />
	<!-- 3.표현식(변수) -->
	덧셈 결과 2 :
	<%=add(30, 40)%>
	<!-- 3.표현식(메서드 호출) -->

</body>
</html>