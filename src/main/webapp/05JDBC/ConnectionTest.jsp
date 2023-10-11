<%@ page import="common.JDBConnect" %>
<%@ page import="java.sql.Connection" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC</title>
</head>
<body>
	<h2>JDBC 테스트 1</h2>
	<%
	JDBConnect jdbc1 = new JDBConnect(); // 하드코딩
	jdbc1.close();
	%>
	<h2>JDBC 테스트 2</h2>
	<%
	String driver = application.getInitParameter("MySqlDriver");
	String url = application.getInitParameter("MySqlURL");
	String id = application.getInitParameter("MySqlId");
	String pwd = application.getInitParameter("MySqlPwd");
	
	JDBConnect jdbc2 = new JDBConnect(driver,url,id,pwd);
	jdbc2.close();
	%>
	<h2>JDBC 테스트 3</h2>
	<%
	JDBConnect jdbc3 = new JDBConnect(application);
	
	Connection con = jdbc3.getConnection();
	
	jdbc3.close();
	%>
</body>
</html>