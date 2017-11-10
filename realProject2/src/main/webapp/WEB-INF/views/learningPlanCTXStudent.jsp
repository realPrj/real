<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 강의 계획서 보기</title>
</head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
	$("#tableShow").hide(); 
	if(${check } != 0){
		$("#tableShow").show();
	}
	
});



</script>
<body>
	<br />
	<div style="margin-left: 100px">
		<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	</div>
	<br />
	<div style="margin-left: 100px">
		<table id="tableShow">
			<tr>
				<td>제목: ${title }</td>
				
			</tr>
			<tr>
				<td>내용: ${content }</td>

			</tr>
			<c:forEach var="file" items="${list }">
				<tr>
					
					<td>올린 파일:<a href="download.action?name=${file}">${file}</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>