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
	int dan = Integer.parseInt(request.getParameter("col"));
	int col = dan;

	for (int i = 2; i <= 9; i += col) {
	%>
	<table>
		<%
		for (int j = 1; j <= 9; j++) {
		%>
		<tr>
			<%
			for (int k = 0; k < col; k++) {
				if ((i + k) > 9)
					break;
			%>
			<td><%=i + k%> * <%=j%> = <%=(i + k) * j%></td>
			<td>&nbsp;&nbsp;</td>
			<%
			}
			%>
		</tr>
		<%
		}
		%>
	</table>
	<br/>
	<%
	}
	%>
</body>
</html>