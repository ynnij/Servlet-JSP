<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="common.JDBConnect" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC</title>
</head>
<body>
	<h2>회원 추가 테스트(executeUpdate() 사용)</h2>
	<%
	JDBConnect jdbc = new JDBConnect(application);
	
	Connection con = jdbc.getConnection();
	String ids[] = {"musthave","willhave","dohave"};
	String pwds[] = {"1234","1234","1234"};
	String names[] ={"머스트해브","윌해브","두해브"};
	
	String sql = "insert into member(id,pass,name) values(?,?,?)";
	PreparedStatement psmt = con.prepareStatement(sql);
	
	int memCnt = 0;
	for(int i=0;i<ids.length;i++){
		psmt.setString(1,ids[i]);
		psmt.setString(2,pwds[i]);
		psmt.setString(3,names[i]);
		memCnt += psmt.executeUpdate();
	}
	out.print("member 테이블에 "+memCnt+"행이 입력되었습니다.<br/>");
	
	sql = "insert into board (title, content, id) values (?,?,?)";
	psmt = con.prepareStatement(sql);
	
	
	
	for(int i=0;i<ids.length;i++){
		int boardCnt =0;
		for(int j=1;j<=10;j++){
			psmt.setString(1,"제목"+j+"입니다.");
			psmt.setString(2,"내용"+j+"입니다.");
			psmt.setString(3,ids[i]);
			boardCnt +=psmt.executeUpdate();
		}
		out.println("board 테이블 사용자 id["+ids[i]+"]에 "+boardCnt+"행이 입력되었습니다.<br/>");
	}
	
	%>
</body>
</html>