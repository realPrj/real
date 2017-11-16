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
       #map {
        height: 400px;
        width: 100%;
       }
    </style>
    
    
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
</head>
<body onLoad="${message}">

<div style="padding-top:30px; "class="card">
	<br />
	<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	<br /><br /><br /><br />


	<input type="button" value="나의정보" class="btn"
		onClick="eventClick('teacherInfoPageform','teacherInfoPage','post')" />&nbsp;&nbsp;
	<input type="button" class="btn" value="로그아웃" onClick="menu('14')" />
	<br/><br/><br/>
</div><br/>

	<!-- <div class="row"> -->
		<!-- <div class="col-md-6"> -->
			
			<div style="margin-left:28%;float:left;font-family: 'Nanum Gothic', sans-serif;width:20%; font-size:13pt; padding:3px"class="card">
					학습개설&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="+" class="btn btn-sm" onClick="eventClick('learningopenform','learningOpenPage','post')" />
				
				<!-- <div class="content">

					<div id="chartPreferences" class="ct-chart ct-perfect-fourth"></div>
				</div> -->
			</div>
			<div style="font-family: 'Nanum Gothic', sans-serif;margin-left:52%;width:20%; font-size:13pt; padding:3px"class="card">
					학습방 수정&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="+" class="btn btn-sm" onClick="eventClick('learningRoomUpdate','LearningRoomUpdatePage','post')" />
				
				<!-- <div class="content">

					<div id="chartPreferences" class="ct-chart ct-perfect-fourth"></div>
				</div> -->
			</div>
			
			<br/>
		<!-- </div> -->
		
			<div style="width:70%; margin:0 auto; font-size:14pt; padding:15px"class="card">
				
					<br/><p style="font-family: 'Nanum Gothic', sans-serif; font-size:15pt">개설과목</p><br/>
					${content }
				
				<!-- <div class="content">
					<div id="chartActivity" class="ct-chart"></div>

				</div> -->
			</div>
		
	<!-- </div> -->
	
    <div id="map"></div>
    
    <script>
      function initMap() {
        var uluru = {lat: 37.438861, lng: 126.675119};
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 17,
          center: uluru
        });
        var marker = new google.maps.Marker({
          position: uluru,
          map: map
        });
      }
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDPNCTGJ6MsYHU8qJ3VzevY4ZrYYILMxm8&callback=initMap">
    </script>
	<br />
	<br />

</body>
</html>