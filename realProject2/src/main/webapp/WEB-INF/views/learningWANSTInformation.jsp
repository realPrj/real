<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 학생별 정보</title>
</head>
<body>

<table id="stInformation">
	<tr>
		<td>이름</td>
		<td>반</td>
		<td>번호</td>
	</tr>
	<tr>
		<td>${studentName }</td>
		<td>${stHalf }</td>
		<td>${stNumber }</td>
	</tr>
</table>

</body>
</html>