<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="common.JDBConnect" %>
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
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	
	JDBConnect jdbc = new JDBConnect();
	Connection con = jdbc.getConnection();
	
	try{
		if(id=="" || pw==""|| name=="")
			throw new Exception();
		
		String sql = "insert into member(id,pass,name) values(?,?,?)";
		PreparedStatement psmt = con.prepareStatement(sql);
		psmt.setString(1,id);
		psmt.setString(2,pw);
		psmt.setString(3,name);
		psmt.executeUpdate();
		jdbc.close();
		
		response.sendRedirect("InsertSuccess.jsp");
		
	} catch(Exception e){
		request.setAttribute("error", e.getMessage());
		request.getRequestDispatcher("InsertFail.jsp").forward(request,response);
		//response.sendRedirect("InsertFail.jsp");
	}
	
	%>
</body>
</html>