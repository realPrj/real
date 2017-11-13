<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>공조 || 학습개설</title>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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

// 방 개설
function open(formname,formaction,formmethod){	
	var roomName = document.getElementsByName("roomName")[0];
	var roomIntroduction = document.getElementsByName("roomIntroduction")[0];
	var roomSB = document.getElementsByName("roomSB")[0];

	createForm(formname,formaction,formmethod);
	
	var form = document.getElementsByName(formname)[0];
	
	form.appendChild(roomName);
	form.appendChild(roomIntroduction);
	form.appendChild(roomSB);
	
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

.container {
	margin: auto;
	text-align: center;
}

.form-group {
	margin: auto;
	text-align: center;
}

#a {
	margin: auto;
	text-align: center-block;
}
</style>
<style>
@import url(https://fonts.googleapis.com/css?family=Montserrat:400,700);

/* html{    background:url(http://thekitemap.com/images/feedback-img.jpg) no-repeat;
  background-size: cover;
  height:100%;
}
 */
#feedback-page {
	text-align: center;
}

#form-main {
	width: 100%;
	float: left;
	padding-top: 0px;
}

#form-div {
	background-color: rgba(72, 72, 72, 0.4);
	padding-left: 35px;
	padding-right: 35px;
	padding-top: 35px;
	padding-bottom: 50px;
	width: 550px;
	float: left;
	left: 50%;
	position: absolute;
	margin-top: 30px;
	margin-left: -260px;
	-moz-border-radius: 7px;
	-webkit-border-radius: 7px;
}

.feedback-input {
	color: #3c3c3c;
	font-family: Helvetica, Arial, sans-serif;
	font-weight: 500;
	font-size: 18px;
	border-radius: 0;
	line-height: 22px;
	background-color: #fbfbfb;
	padding: 13px 13px 13px 54px;
	margin-bottom: 10px;
	width: 100%;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	-ms-box-sizing: border-box;
	box-sizing: border-box;
	border: 3px solid rgba(0, 0, 0, 0);
}

.feedback-input:focus {
	background: #fff;
	box-shadow: 0;
	border: 3px solid #3498db;
	color: #3498db;
	outline: none;
	padding: 13px 13px 13px 54px;
}

.focused {
	color: #30aed6;
	border: #30aed6 solid 3px;
}

/* Icons ---------------------------------- */
#name {
	background-image:
		url(http://images.vectorhq.com/images/previews/9ee/book-icon-100926.png);
	background-size: 30px 30px;
	background-position: 11px 8px;
	background-repeat: no-repeat;
}

#name:focus {
	background-image:
		url(http://images.vectorhq.com/images/previews/9ee/book-icon-100926.png);
	background-size: 30px 30px;
	background-position: 8px 5px;
	background-position: 11px 8px;
	background-repeat: no-repeat;
}

/* #email{
   background-image: url(http://rexkirby.com/kirbyandson/images/email.svg);
   background-size: 30px 30px;
   background-position: 11px 8px;
   background-repeat: no-repeat;
} */
#grade {
	background-image: url(http://rexkirby.com/kirbyandson/images/email.svg);
	background-size: 30px 30px;
	background-position: 11px 8px;
	background-repeat: no-repeat;
}

/* #email:focus{
   background-image: url(http://rexkirby.com/kirbyandson/images/email.svg);
   background-size: 30px 30px;
  background-position: 11px 8px;
   background-repeat: no-repeat;
}
 */
#comment {
	background-image:
		url(http://rexkirby.com/kirbyandson/images/comment.svg);
	background-size: 30px 30px;
	background-position: 11px 8px;
	background-repeat: no-repeat;
}

#grade {
	background-image:
		url(http://rexkirby.com/kirbyandson/images/comment.svg);
	background-size: 30px 30px;
	background-position: 11px 8px;
	background-repeat: no-repeat;
}

textarea {
	width: 100%;
	height: 250px;
	line-height: 150%;
	resize: vertical;
}

input:hover, textarea:hover, input:focus, textarea:focus {
	background-color: white;
}

#button-blue {
	font-family: 'Montserrat', Arial, Helvetica, sans-serif;
	float: left;
	width: 100%;
	border: #fbfbfb solid 4px;
	cursor: pointer;
	background-color: #3498db;
	color: white;
	font-size: 24px;
	padding-top: 22px;
	padding-bottom: 22px;
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	transition: all 0.3s;
	margin-top: -4px;
	font-weight: 700;
}

#button-blue:hover {
	background-color: rgba(0, 0, 0, 0);
	color: #0493bd;
}

.submit:hover {
	color: #3498db;
}

.ease {
	width: 0px;
	height: 74px;
	background-color: #fbfbfb;
	-webkit-transition: .3s ease;
	-moz-transition: .3s ease;
	-o-transition: .3s ease;
	-ms-transition: .3s ease;
	transition: .3s ease;
}

.submit:hover .ease {
	width: 100%;
	background-color: white;
}

@media only screen and (max-width: 580px) {
	#form-div {
		left: 3%;
		margin-right: 3%;
		width: 88%;
		margin-left: 0;
		padding-left: 3%;
		padding-right: 3%;
	}
}

/* font-face */
@import url(https://fonts.googleapis.com/css?family=Roboto);
/* variables */
/* default style */
body {
	background-color: #fff;
	color: #333;
	font-family: "Roboto", arial, sans-serif;
	font-size: 16px;
}
/* common style */
* {
	margin: 0;
	padding: 0;
}

*, *:after, *:before {
	box-sizing: border-box;
}

* {
	outline: none;
}

::-webkit-scrollbar {
	display: none;
}
/* */
.container {
	margin: 0 auto;
	max-width: 400px;
	text-align: center;
}

.selected-item {
	margin: 20px 0;
}
/* custom select style */
.cusSelBlock {
	height: 50px;
	min-width: 250px;
}

#cusSelectbox {
	height: 50px;
	width: 100%;
}

.s-hidden {
	visibility: hidden;
}

.cusSelBlock {
	display: inline-block;
	position: relative;
	-webkit-perspective: 800px;
	perspective: 800px;
}

.selectLabel {
	position: absolute;
	left: 0;
	top: 0;
	z-index: 9999;
	background-color: #fff;
	border: 1px solid #333;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
	color: #333;
	cursor: pointer;
	display: block;
	height: 100%;
	width: 100%;
	letter-spacing: 2px;
	line-height: 50px;
	padding: 0 50px 0 20px;
	text-align: left;
	-webkit-transform-style: preserve-3d;
	transform-style: preserve-3d;
	-webkit-transform-origin: 50% 0%;
	transform-origin: 50% 0%;
	-webkit-transition: -webkit-transform 300ms;
	transition: -webkit-transform 300ms;
	transition: transform 300ms;
	transition: transform 300ms, -webkit-transform 300ms;
	-webkit-backface-visibility: hidden;
	-webkit-touch-callout: none;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

.selectLabel:after {
	content: '\25BC';
	border-left: 1px solid #333;
	color: #333;
	font-size: 12px;
	line-height: 17px;
	padding: 10px;
	text-align: center;
	position: absolute;
	right: 0px;
	top: 15%;
	height: 70%;
	width: 50px;
}

.selectLabel:active {
	-webkit-transform: rotateX(30deg);
	transform: rotateX(30deg);
}

.selectLabel:active:after {
	content: '\25B2';
}

.selectLabel.active:after {
	content: '\25B2';
}

::-webkit-scrollbar {
	display: none;
}

.options {
	position: absolute;
	top: 50px;
	height: 1px;
	width: 100%;
}

.options li {
	background-color: #ffffff;
	border-left: 1px solid #333;
	border-right: 1px solid #333;
	border-bottom: 1px solid #333;
	cursor: pointer;
	display: block;
	line-height: 50px;
	list-style: none;
	opacity: 1;
	padding: 0 50px 0 20px;
	text-align: left;
	-webkit-transition: -webkit-transform 300ms ease;
	transition: -webkit-transform 300ms ease;
	transition: transform 300ms ease;
	transition: transform 300ms ease, -webkit-transform 300ms ease;
	position: absolute;
	top: -50px;
	left: 0;
	z-index: 0;
	height: 50px;
	width: 100%;
}

.options li:hover, .options li.active {
	background-color: #333;
	color: #fff;
}

.options li:nth-child(1) {
	-webkit-transform: translateY(2px);
	transform: translateY(2px);
	z-index: 6;
}

.options li:nth-child(2) {
	-webkit-transform: translateY(4px);
	transform: translateY(4px);
	z-index: 5;
}

.options li:nth-child(3) {
	z-index: 4;
}

.options li:nth-child(4) {
	z-index: 3;
}

.options li:nth-child(5) {
	z-index: 2;
}

.options li:nth-child(6) {
	z-index: 1;
}
/**/
.feaBlock {
	margin: 20px 0;
	text-align: left;
}
</style>


<body>
	<div id="xy"></div>

	<br />
	<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	<br />

	<!-- 로그아웃,나의프로필 버튼 -->
	<br />
	<br />
	<button type="button" class="btn">로그아웃</button>
	<button type="button" class="btn"
		OnClick="location.href='http://localhost/real/student_info.jsp'">나의프로필</button>
	<br />
	<br />


	<br />
	<br />
	<!-- 과목참여 -->
	<div class="col-lg-20">
		<div class="container ">
			<div class="container"">
				<h2 class="container">
					<b>과목개설</b>
				</h2>
				<br /> <br />
				<div id="form-main">
					<div id="form-div">
						<form class="form" id="form1">

							<p class="방이름">
								<input name="roomName" type="text"
									class="validate[required,custom[onlyLetter],length[0,100]] feedback-input"
									placeholder="Name" id="name" />
							</p>



							<p class="text">
								<textarea name="roomIntroduction"
									class="validate[required,length[6,300]] feedback-input"
									id="comment" placeholder="Comment"></textarea>
							</p>

							<select name="roomSB" id="cusSelectbox">
								<option>과목 선택</option>
								<option value="1">언어</option>
								<option value="2">수리-가</option>
								<option value="3">수리-나</option>
								<option value="4">영어</option>
							</select> <br>
							<br>
							<div>
								<input class="btn" id="button-blue" type="button" value="개설하기"
									onClick="open('learningOpenform','learningOpen','post')" />
								<div class="ease"></div>
							</div>
						</form>
					</div>




				</div>

			</div>
		</div>




		<!-- <table>
			<tr>
				<td>방이름</td>
				<td><input type="text" name="roomName" placeholder="학습방 이름" /></td>
			</tr>
			<tr>
				<td>방소개</td>
				<td><input type="text" name="roomIntroduction"
					placeholder="학습방 소개" /></td>
			</tr>
			<tr>
				<td>과목선택</td>
				<td><select name="roomSB"><option value="1">언어</option>
						<option value="2">수리-가</option>
						<option value="3">수리-나</option>
						<option value="4">영어</option>
				</select></td>
			</tr>
			<tr>
				<td><input class="btn" id="button-blue" type="button"
					value="개설하기"
					onClick="open('learningOpenform','learningOpen','post')" /></td>
			</tr>
		</table> -->
</body>
</html>