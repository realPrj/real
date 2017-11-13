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
<script>
function createForm(formname, formaction, formmethod) {

	var form = document.createElement("form");

	form.name = formname;
	form.action = formaction;
	form.method = formmethod;

	document.body.appendChild(form);

}

//input 생성
function createinput(itype, iname, ivalue) {
	var input = document.createElement("input");
	input.type = itype;
	input.name = iname;
	input.value = ivalue;

	document.body.appendChild(input);
}

function deleteTask(boardDate){

	createinput("hidden", "boardDate", boardDate);


	createForm("deleteTaskForm", "deleteTask", "post");

	var form = document.getElementsByName("deleteTaskForm")[0];

	var boardDate = document.getElementsByName("boardDate")[0];


	form.appendChild(boardDate);


	form.submit();
}

function onload(){
	${message};
	${reload};
	${windowclose};
}
</script>
</head>

<body onLoad="onload()">
	<!-- <br />
	<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	<br /> -->



	<form name="datactx">
		
			<div class="title">
				제출한 과제 확인
			</div>
			<div class="content">
			이름 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${name }
			</div>
			<div class="content">
			제출일 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${date }
			</div>
			<div class="content">
			<c:forEach var="file" items="${list }">
			제출 파일	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="download.action?name=${file}">${file}</a>
			</c:forEach>
			</div>
			<div class="content">
			<input type="button" name="delete" value="삭제" class='btn'onClick="deleteTask('${date}')">
			</div>
			

				
	

	</form>

</body>
</html>