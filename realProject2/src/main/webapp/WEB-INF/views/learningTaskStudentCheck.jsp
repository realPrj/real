<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>공조 || 학생과제제출 확인</title>

<style>
@import url(//fonts.googleapis.com/earlyaccess/nanumgothic.css);
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);
.title{
font-family: 'Nanum Gothic', sans-serif; text-align:center; font-size:15pt; margin:10%
}
.content{
font-family: 'Noto Sans KR', sans-serif; font-size:12pt; margin:3%
}
</style>

</head>

<body onLoad="${message}">
	<!-- <br />
	<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	<br /> -->



	<form name="datactx">
		
			<div class="title">
				제출한 과제 확인
			</div>
			<div class="content">
			제출일 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${date }
			</div>
			<div class="content">
			<c:forEach var="file" items="${list }">
			제출 파일	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="download.action?name=${file}">${file}</a>
			</c:forEach>
			</div>

				
	

	</form>

</body>
</html>