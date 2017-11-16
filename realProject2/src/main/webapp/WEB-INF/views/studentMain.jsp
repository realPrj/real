<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="apple-touch-icon" sizes="76x76"
	href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>공조 || 학생 메인</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />
<link href="assets/css/css.css" rel="stylesheet" />

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

$(document).ready(function() {
	$("#attendance").hide();

	
 });

function attendance(){

	$("#attendance").show(1000);

}

function fold(){
	
	$("#attendance").hide(1000);
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

function eventClick(formname,formaction,formmethod){	

	createForm(formname,formaction,formmethod);

	var form = document.getElementsByName(formname)[0];
	
	form.submit();
	
}

function learningGo(learningCode){	
	
	createinput("hidden", "roomCode", learningCode);
	
	var roomCode = document.getElementsByName("roomCode")[0];
	
	createForm("studentLearningMainPageform","studentLearningMainPage","post");
	
	var form = document.getElementsByName("studentLearningMainPageform")[0];
	
	form.appendChild(roomCode);
	
	form.submit();
	
}
//메뉴선택
function menu(ivalue){
	
	createinput("hidden", "caCode", ivalue);
	
	var caCode = document.getElementsByName("caCode")[0];
	
	createForm("menuform","stmenu","post");
	

	var form = document.getElementsByName("menuform")[0];
	form.appendChild(caCode);
	
	form.submit();
	
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

</style>
<body onLoad="${message}">
	<!-- <br />
	<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	<br />

	<input type="button" class="btn" value="로그아웃" onClick="menu('14')" />

	<input type="button" value="나의정보" class="btn"
		onClick="eventClick('studentInfoPageform','studentInfoPage','post')" /> -->
		<div style="padding-top:30px;"class="card">
	<br />
	<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	<br /><br /><br /><br />


	<input type="button" value="나의정보" class="btn"
		onClick="eventClick('studentInfoPageform','studentInfoPage','post')" />&nbsp;&nbsp;
	<input type="button" class="btn" value="로그아웃" onClick="menu('14')" />&nbsp;&nbsp;
	<input type="button" value="출결"  class="btn" onClick="attendance()"  />
	<br/><br/><br/>
</div><br/>
	
	${attendance }



<%-- <div class="row">
		<div class="col-md-6">
			<div class="card">
				<div class="header">

					<h4 class="title">
						<b>학습 참여</b>
					</h4>
					<input type="button" value="+"  class="btn"onClick="eventClick('learningjoinform','learningJoinPage','post')" />
				</div>
				<div class="content">

					<div id="chartPreferences" class="ct-chart ct-perfect-fourth"></div>


				</div>
			</div>
		</div>
		<div class="col-md-6">
			<div class="card ">
				<div class="header">
					<h4 class="title">
						<b>참여 과목</b>
					</h4>
					${content }
				</div>
				<div class="content">
					<div id="chartActivity" class="ct-chart"></div>

				</div>
			</div>
		</div>
	</div> --%>
	<div style=" font-family: 'Nanum Gothic', sans-serif;margin:0 auto;width:20%; font-size:13pt; padding:3px"class="card">
					학습참여&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="+"  class="btn btn-sm"onClick="eventClick('learningjoinform','learningJoinPage','post')" />
				
				<!-- <div class="content">

					<div id="chartPreferences" class="ct-chart ct-perfect-fourth"></div>
				</div> -->
			</div>
			<br/>
			<div style="width:70%; margin:0 auto; font-size:14pt; padding:15px"class="card">
				
					<br/><p style="font-family: 'Nanum Gothic', sans-serif; font-size:15pt">참여과목</p><br/>
					${content }
				
				<!-- <div class="content">
					<div id="chartActivity" class="ct-chart"></div>

				</div> -->
			</div>
</body>
</html>