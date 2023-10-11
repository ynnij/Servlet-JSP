<%@ page import="java.util.Date" %>
<%@ page import="common.Person" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 변수선언 -->
	<c:set var ="directVar" value="100" />
	<c:set var="elVar" value="${ directVar mod 5 }" />
	<c:set var="expVar" value="<%= new Date() %>" />
	<c:set var="betweenVar">변수값 요렇게 설정</c:set>
	
	<h4>EL을 이용해 변수 출력</h4>
	<ul>
		<li>directVar : ${ pageScope.directVar }</li>
		<li>elVar : ${ expVar }</li>
		<li>expVar : ${ expVar }</li>
		<li>betweenVar : ${ betweenVar }</li>
	</ul>
	
	<h4>자바빈즈 생성 1 - 생성자 사용</h4>
	<c:set var="personVal1" value='<%= new Person("박문수", 50) %>'
			scope="request" />
	<ul>
		<li>이름 : ${ requestScope.personVal1.name }</li>
		<li>나이 " ${ personVal1.age }</li>
	</ul>
	
	<h4>자바빈즈 생성2 - target, property 사용</h4>
	<c:set var="personVal2" value="<%= new Person() %>" scope="request" />
	<c:set target="${ personVal2 }" property="name" value="정약용" />
	<c:set target="${ personVal2 }" property="age" value="60" />
	<ul>
		<li>이름 : ${ requestScope.personVal2.name }</li>
		<li>나이 " ${ personVal2.age }</li>
	</ul>
</body>
</html>