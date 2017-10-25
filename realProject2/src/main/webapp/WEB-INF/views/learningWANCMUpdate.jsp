<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 코멘트 수정</title>
</head>



<body>
<input type="hidden" value="${boardCode }" name="boardCode" />
<input type="hidden" value="${boardDate }" name="boardDate" />
<table>
	<tr>
		<td>내용</td>
		<td><input type="text" value="${boardContent }" name="boardContent" /></td>
	</tr>
	<tr>
		<td>파일첨부</td>
		<td><input type="text"  value="${boardRoute }" name="boardRoute" /></td>
	</tr>
	<tr>
		<td><input type="button" value="수정" onClick="commentInsert()" /></td>
	</tr>
</table>
</body>
</html>