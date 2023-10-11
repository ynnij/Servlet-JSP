<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session</title>
<style>
div {
	margin: 0 auto;
	display: flex;
	flex-direction: column;
	justify-content: center;
	text-align: center;
}
form {
	margin: 0 auto;
}
form #sign-up {
	float:right;
}
</style>
</head>
<body>
	<jsp:include page="../Common/Link.jsp" />
	<div>
	<h2>로그인 페이지</h2>
	<span style="color:red; font-size:1.2rem">
		<%=
		request.getAttribute("LoginErrMsg") == null?
				"" : request.getAttribute("LoginErrMsg")
		%>
	</span>
	<%
	if (session.getAttribute("UserId")==null){
	%>
	<script>
		function validation(form) {
			if(!form.user_id.value) {
				alert("아이디를 입력하세요.");
				return false;
			}		
			if(form.user_pw.value==""){
				alert("패스워드를 입력하세요.");
				return false;
			}
			
		}
	</script>
	<form action="LoginProcess.jsp" method="post" name="loginFrm"
		onsubmit="return validationForm(this);">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="user_id" /></td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td><input type="password" name="user_pw" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="로그인하기" style="width:100%"/></td>
			</tr>
		</table>
		<a href="SignUp.jsp" id="sign-up">회원가입</a>

		
	</form>
	<%
	} else {
	%>
	<%= session.getAttribute("UserName") %> 회원님, 로그인하셨습니다.<br/>
	<a href="Logout.jsp">[로그아웃]</a> 
	<%
	}
	%>
	</div>
	
</body>
</html>