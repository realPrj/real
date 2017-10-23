<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공조 || 선생님 메인</title>
</head>
<script>
//form 생성
function createForm(formname,formaction,formmethod){

	var form = document.createElement("form");

	form.name = formname;
	form.action = formaction;
	form.method = formmethod;

	document.body.appendChild(form);

}

//input 생성
function createinput(itype, iname, ivalue){
	var input = document.createElement("input");
	input.type = itype;
	input.name = iname;
	input.value = ivalue;

	document.body.appendChild(input);
}

function eventClick(formname,formaction,formmethod){	
	
	createForm(formname,formaction,formmethod);
	
	var form = document.getElementsByName(formname)[0];
	
	form.submit();
	
}

function learningGo(learningCode){	
	
	createinput("hidden", "roomCode", learningCode);
	
	var roomCode = document.getElementsByName("roomCode")[0];
	
	createForm("teacherLearningMainPageform","teacherLearningMainPage","post");
	
	var form = document.getElementsByName("teacherLearningMainPageform")[0];
	
	form.appendChild(roomCode);
	
	form.submit();
	
}

</script>
<body onLoad="${message}">
<h1>공조</h1>
<table>
	<tr>
		<td><input type="button" value="로그아웃" onClick="eventClick('logoutform','logout','post')"  /></td>
	</tr>
	<tr>
		<td><input type="button" value="나의정보" onClick="eventClick('teacherInfoPageform','teacherInfoPage','post')"  /></td>
	</tr>
</table>
<div>
	<div>학습 개설<br/><input type="button" value="+" onClick="eventClick('learningopenform','learningOpenPage','post')" /></div>
	<div>개설한 과목<br/>${content }</div>
</div>
</body>
</html>