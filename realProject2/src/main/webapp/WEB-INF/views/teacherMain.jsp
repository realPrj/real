<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>공조 || 선생님 메인</title>
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
// 자료실 form
//메뉴선택
function menu(ivalue){
	
	createinput("hidden", "caCode", ivalue);
	
	var caCode = document.getElementsByName("caCode")[0];
	
	createForm("menuform","tcmenu","post");
	

	var form = document.getElementsByName("menuform")[0];
	form.appendChild(caCode);
	
	form.submit();
	
}

</script>
<style>
body {
	background-color: white;
	color: #353535;
	text-align: center;
	font-size: 15px;
}

</style>
</head>
<body onLoad="${message}">

	<br />
	<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	<br />


	<input type="button" class="btn" value="로그아웃" onClick="menu('14')" />

	<input type="button" value="나의정보" class="btn"
		onClick="eventClick('teacherInfoPageform','teacherInfoPage','post')" />

	<div class="row">
		<div class="col-md-6">
			<div class="card">
				<div class="header">

					<h4 class="title">
						<b>학습개설</b>
					</h4>
					<input type="button" value="+" class="btn"
						onClick="eventClick('learningopenform','learningOpenPage','post')" />
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
						<b>개설한 과목</b>
					</h4>
					${content }
				</div>
				<div class="content">
					<div id="chartActivity" class="ct-chart"></div>

				</div>
			</div>
		</div>
	</div>
	
	<br />
	<br />
</body>
</html>