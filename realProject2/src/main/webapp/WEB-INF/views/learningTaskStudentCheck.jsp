<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>공조 || 학생 과제 제출</title>
</head>

<body onLoad="${message}">
	<br />
	<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	<br />



	<form name="datactx">
		<table>
			
			<c:forEach var="file" items="${list }">
				<tr>
				올린 파일:	<td><a href="download.action?name=${file}">${file}</a></td>
				</tr>
			</c:forEach>

			<tr>
				<td>날짜:${date }</td>
			</tr>

		</table>

	</form>






</body>
</html>