<%@ page import="membership.MemberDTO" %>
<%@ page import="membership.MemberDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String userId = request.getParameter("id");
String userPwd = request.getParameter("pw");
String userName = request.getParameter("name");

String mysqlDriver = application.getInitParameter("MySqlDriver");
String mysqlURL = application.getInitParameter("MySqlURL");
String mysqlId = application.getInitParameter("MySqlId");
String mysqlPwd = application.getInitParameter("MySqlPwd");

MemberDAO dao = new MemberDAO(mysqlDriver, mysqlURL, mysqlId, mysqlPwd);
if(dao.insertMember(userId, userPwd, userName)){
	response.sendRedirect("SignUpSuccess.jsp");
}
else {
	request.setAttribute("LoginErrMsg","회원가입 실패");
	request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
}

%>