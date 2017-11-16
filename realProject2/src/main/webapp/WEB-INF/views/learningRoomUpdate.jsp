<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>공조 || 학습방 수정</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />


<!-- Bootstrap core CSS     -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />

<!-- Animation library for notifications   -->
<link href="assets/css/animate.min.css" rel="stylesheet" />

<!--  Paper Dashboard core CSS    -->
<link href="assets/css/paper-dashboard.css" rel="stylesheet" />


<!--  CSS for Demo Purpose, don't include it in your project     -->
<link href="assets/css/demo.css" rel="stylesheet" />
<link rel="stylesheet" href="assets/css/login.css" type="text/css">


<!--  Fonts and icons     -->
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"
	rel="stylesheet">
<link href='https://fonts.googleapis.com/css?family=Muli:400,300'
	rel='stylesheet' type='text/css'>
<link href="assets/css/themify-icons.css" rel="stylesheet">
<script>
$(document).ready(function(){
	$("#roomUpdate").hide();
});


function learningRoomUpdate(content, introduce){
	$("#title").attr("value",content);
	$("#introduce").html(introduce.split("<br/>").join('\r\n')); 
	$("#roomUpdate").show();
}
function update(){
	
	createForm("learningRoomUpdate","LearningRoomUpdate","post");
	
	var f = document.getElementsByName("learningRoomUpdate")[0];
	var title = document.getElementsByName("roomName")[0];
	f.appendChild(title);
	var intro = document.getElementsByName("roomIntroduction")[0];
	f.appendChild(intro);
	
	alert(intro.value);
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
 function resize(obj){ // 글의 높이에 맞게 textarea조정
	obj.style.height="1px";
	obj.style.height=(20+obj.scrollHeight)+"px";
} 
 function eventClick(formname,formaction,formmethod){	
		
		createForm(formname,formaction,formmethod);
		
		var form = document.getElementsByName(formname)[0];
		
		form.submit();
		
	}
//메뉴선택
 function menu(ivalue){
 	
 	createinput("hidden", "caCode", ivalue);
 	
 	var caCode = document.getElementsByName("caCode")[0];
 	
 	createForm("menuform","tcmenu","post");
 	

 	var form = document.getElementsByName("menuform")[0];
 	form.appendChild(caCode);
 	
 	form.submit();
 	
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
<style>
@import url(//fonts.googleapis.com/earlyaccess/nanumgothic.css);
@import url(//fonts.googleapis.com/earlyaccess/notosanskr.css);
body {
	background-color: #f4f3ef;
	color: #353535;
	text-align: center;
	font-size: 15px;
}
.room{
border:none; background:#FFEBC6; padding:15px; margin:10px; font-size:11pt; border-radius:10px; font-family: 'Noto Sans KR', sans-serif;
}
.room:hover{
background:#FFD9B4;
}
.title{
border:none; border-bottom:1px solid orange; background:transparent; width:30%; 
}
.title:hover{
background:#FFEBC6
}
.introduce{
border:none; border-bottom:1px solid orange;  background:transparent; width:30%; resize:none; height:250px
}
.introduce:hover{
background:#FFEBC6
}
.top{
width:60px; margin-left:31%
}
</style>
</head>
<body>
<div style="padding-top:30px; "class="card">
	<br />
	<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	<br /><br /><br /><br />


	<input type="button" value="나의정보" class="btn"
		onClick="eventClick('teacherInfoPageform','teacherInfoPage','post')" />&nbsp;&nbsp;
	<input type="button" class="btn" value="로그아웃" onClick="menu('14')" />
	<br/><br/><br/>
</div><br/>
<div style="width:70%; margin:0 auto; font-size:14pt; padding:15px"class="card">
<br/><p style="font-family: 'Nanum Gothic', sans-serif; font-size:15pt">*수정할 과목을 선택해주세요.</p><br/>
${roomList }<br/><br/><br/><br/>
<div id="roomUpdate" class="updatebox" style="font-size:12pt">
<p class="top">제목</p>   <input class="title" id="title" name="roomName" type="text"/><br/><br/><br/>
<p class="top">소개글</p> <textarea onkeydown="resize(this)" class="introduce" id="introduce" name="roomIntroduction"></textarea><br/><br/><br/>
<input type="button" class="btn btn-md"  value="수정하기" onClick="update()"/>
</div>
</div>

	<!-- <form name="learningRoomUpdate" action="LearningRoomUpdate" method="post"> -->
<!-- <div id="roomUpdate">
<input class="title" id="title" name="roomName" type="text"/><br/>
<textarea class="introduce" id="introduce" name="roomIntroduction"></textarea><br/>
<input type="button" value="수정하기" onClick="update()"/>
</div> -->
<!-- </form> -->
</body>
</html>