<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공조 || 자료실 글 등록</title>
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


// 자료실 form


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
	<input type="button" value="자료실" onClick="eventClick('dataform','Datamain','post')"  />
	<input type="button" value="자료실 글쓰러가기" onClick="eventClick('dataform','DataInsert','post')"  />
</div>
	<form name="fileForm" action="requestupload2" method="post"
		enctype="multipart/form-data">
		<br> 제목 : <input type="text" name="subject" size=50 maxlength=70>
		<br> 내용 :
		<textarea name="body" cols=50 rows=20 maxlength=500></textarea>




		<br> <input multiple="multiple" type="file" name="file" /> <input
			type="hidden" name="src" /> <br>
		<BUTTON type="submit">보내기</BUTTON>
	</form>

</body>
</html>