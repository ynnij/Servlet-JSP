<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 - response</title>
</head>
<body>
<%
String id = request.getParameter("user_id");
String pwd = request.getParameter("user_pwd");

if(id.equalsIgnoreCase("must") && pwd.equalsIgnoreCase("1234")){
	response.sendRedirect("ResponseWelcome.jsp"); // 페이지 호출
} else {
	request.getRequestDispatcher("ResponseMain.jsp?loginErr=1") // 쿼리스트링 붙인 것
	.forward(request, response); // getRequestDispatcher : forward 사용할 때 공식처럼 사용하는 메서드
}
%>
</body>
</html>