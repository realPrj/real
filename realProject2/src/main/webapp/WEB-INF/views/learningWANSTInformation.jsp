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
		<td>총 물어본 문제수</td>
	</tr>
	<tr>
		<td>${studentName }</td>
		<td>${stHalf }</td>
		<td>${stNumber }</td>
		<td>${allSum }</td>
	</tr>
</table>

<!-- 여기는 유형별 총합 -->


<!-- 		끝			-->


<!-- 여기는 전국적 평균 -->


<!-- 		끝			-->


<!-- 여기는 내가 올린 글 리스트 -->


<!-- 		끝			-->

</body>
</html>