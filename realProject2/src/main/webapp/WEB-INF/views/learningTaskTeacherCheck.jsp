<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>공조 || 선생님</title>
<script>
function close(){
	self.close();
}
</script>
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
	<center> <br />
	<img src="assets/img/gong_logo.png" alt="공조" width="150" height="70">
	<br /> </center>



	<form name="datactx">
		<%-- <table>
			<tr>
				<td>학생:${name }</td>
			</tr>
			<c:forEach var="file" items="${list }">
				<tr>
				올린 파일:	<td><a href="download.action?name=${file}">${file}</a></td>
				</tr>
			</c:forEach>

			<tr>
				<td>날짜:${date }</td>
			</tr>

		</table> --%>
		<div class="title">
				제출한 과제 확인
			</div>
			<div class="content">
			학생 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${name }
			</div>
			<div class="content">
			제출일 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${date }
			</div>
			<div class="content">
			<c:forEach var="file" items="${list }">
			제출 파일	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="download.action?name=${file}">${file}</a>
			</c:forEach>
			</div>
			<input type="button" value="닫기" onClick="close()"/>
	</form>	
	






</body>
</html>