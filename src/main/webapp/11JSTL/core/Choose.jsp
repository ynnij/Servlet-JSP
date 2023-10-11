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
	<c:set var="number" value="100" />
	
	<h4>Choose 태그로 홀짝 판단하기</h4>
	<c:choose>
		<c:when test="${ number mod 2 eq 0 }">
			${ number }는 짝수입니다.
		</c:when>
		<c:otherwise>
			${ number }는 홀수입니다.
		</c:otherwise>
	</c:choose>
	
	<h4>국,영,수 점수를 입력하면 평균을 내어 학점 출력</h4>
	<form>
		국어 : <input type="text" name="kor" /><br />
		영어 : <input type="text" name="eng" /><br />
		수학 : <input type="text" name="math" /><br />
		<input type="submit" value="학점 구하기" />
	</form>
<%-- 	<c:if test="${ (empty param.kor or empty param.eng or empty param.math) }"> --%>
<!-- 		점수를 다 입력해주세요. -->
<%-- 	</c:if> --%>
	<c:if test="${ not (empty param.kor or empty param.eng or empty param.math) }" var="result">
		<c:set var="avg" value="${ (param.kor+param.eng+param.math)/3 }" />
		평균 점수는 ${ avg }으로
		<c:choose>
			<c:when test="${ avg >=90 }">A학점</c:when>
			<c:when test="${ avg >=80 }">B학점</c:when>
			<c:when test="${ avg ge 70 }">C학점</c:when>
			<c:when test="${ avg ge 60 }">D학점</c:when>
			<c:otherwise>F학점</c:otherwise>
		</c:choose>
		입니다.
	</c:if>
	<c:if test="${ !result }">
		점수를 다 입력하세요.
	</c:if>
</body>
</html>