<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 학습방 수정</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$("#roomUpdate").hide();
});
function learningRoomUpdate(content, introduce){
	$("#title").attr("value",content);
	$("#introduce").html(introduce);
	$("#roomUpdate").show();
}
function update(){
	
	createForm("learningRoomUpdate","LearningRoomUpdate","post");
	
	var f = document.getElementsByName("learningRoomUpdate")[0];
	var title = document.getElementsByName("roomName")[0];
	f.appendChild(title);
	var intro = document.getElementsByName("roomIntroduction")[0];
	f.appendChild(intro);

	document.learningRoomUpdate.submit();

}
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
</script>
</head>
<body>
${roomList }
	<!-- <form name="learningRoomUpdate" action="LearningRoomUpdate" method="post"> -->
<div id="roomUpdate">
<input id="title" name="roomName" type="text"/>
<textarea id="introduce" name="roomIntroduction"></textarea>
<input type="button" value="수정하기" onClick="update()"/>
</div>
<!-- </form> -->
</body>
</html>