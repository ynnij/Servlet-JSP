<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>자유 낙하 물체의 위치 구하기</h2>
	<form action='./Mission' method='post'>
		초기 위치 <input type="text" name='heightStr' placeholder='m'/>
		시간 <input type="text" name='timeStr' placeholder='초' />
		<input type='submit' value="계산하기" />
	</form>
	<p>
		<p>${ result }</p>
	</p>
</body>
</html>