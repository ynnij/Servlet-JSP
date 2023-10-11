<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String sel = request.getParameter("sel");
	String dan = request.getParameter("dan");
	
	if(dan.isEmpty()) {
		response.sendRedirect("Gugudan3.jsp");
		return;
	}
	
	if(sel.compareTo("type1")==0){
		response.sendRedirect("Gugudan1.jsp?dan="+dan);
	}
	else if(sel.compareTo("type2")==0){
		response.sendRedirect("Gugudan2.jsp?col="+dan);
	}
		
	%>
</body>
</html>