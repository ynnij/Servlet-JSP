<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 = request</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String sex = request.getParameter("sex");
	String[] favo = request.getParameterValues("favo");
	String favoStr = "";
	if (favo != null) {
		for (int i = 0; i < favo.length; i++) {
			favoStr += favo[i] + "";
		}
	}
	String intro = request.getParameter("intro").replace("\r\n", "<br/>");
	%>
	<h2>2. 클라이언트의 요청 매개변수 읽기</h2>
	<ul>
		<li>아이디 : <%=id%></li>
		<li>성별 : <%=sex%></li>
		<li>관심사항 : <%=favoStr%></li>
		<!-- favoStr을 만들지 않고 favo를 그대로 출력하는 방법 -->
		<li>관심사항 : 
		<%
		for (int i=0; i<favo.length;i++){
		%>
			<%=favo[i] %>
		<%	
		}
		%>
		</li>
		<li>자기소개 : <%=intro%></li>

	</ul>
</body>
</html>