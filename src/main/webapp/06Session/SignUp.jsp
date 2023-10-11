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
    display: flex;
    flex-direction: column;
    text-align: center
}
</style>
<body>
<div>
		<h2>Member 데이터 입력</h2>
		<form action='SignUpProcess.jsp' method="post" style="margin: 0 auto">
			<table style="text-align: center">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="id" placeholder="아이디 입력">
						<br /></td>
				</tr>
				<tr>
					<td>패스워드</td>
					<td><input type="text" name="pw" placeholder="패스워드 입력"><br /></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" placeholder="이름 입력"><br /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" style="width: 100%"></td>
				</tr>
			</table>

		</form>

	</div>
</body>
</html>