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

<title>공조 || 로그인</title>
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

// 로그인
function login(){	
	
	var id = document.getElementsByName("id")[0];
	var pwd = document.getElementsByName("pwd")[0];
	var identity = document.getElementsByName("identity")[0];
	
	createForm("loginform","login","post");
	
	var form = document.getElementsByName("loginform")[0];
	
	form.appendChild(id);
	form.appendChild(pwd);
	form.appendChild(identity);
	
	form.submit();
	
}

function eventClick(formname,formaction,formmethod){	

	var identity = document.getElementsByName("identity")[0];
	
	createForm(formname,formaction,formmethod);
	
	var form = document.getElementsByName(formname)[0];
	
	form.appendChild(identity);
	
	form.submit();
	
}

</script>
<style>
@import url(https://fonts.googleapis.com/css?family=Karla);
@import url(https://fonts.googleapis.com/css?family=Ubuntu:300);

.wrap{
  padding:120px 0;
  font-size:62px;
  color:#888;
  width:400px;
  font-family:'Karla';
  margin:0 auto;
  text-align:center;
}

input{
  font-family:'Ubuntu';
  font-weight:300;
  border:0;
  border-bottom:1px solid #ff5407;
  width:100%;
  height:36px;
  font-size:26px;
}

input:focus{
  outline:none;
  box-shadow:none;
  background:#ffeae2;
}

button{
  border:0;
  background:transparent;
  padding:5px;
  margin-top:50px;
  position:relative;
  outline:0;
}
.buttonafter:after{
content:'';
display:block;
position:absolute;
top:8px;
left:100%;
width:0;
height:0;
border-color: transparent transparent transparent #14a03d;
border-style: solid;
border-width: 5px;

}

.forgot{
  background:#a0a0a0;
  color:#fff;
  float:left;
}

.login{
  background:#a0a0a0;
  color:#fff;
  float:right;
}

body {
	background-color: white;
	color: #353535;
	text-align: center;
	font-size: 15px;
}
</style>
<body onLoad="${message}">
	<br />
	<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	<br />

	<div>
	<div class='wrap'>
  로그인
        <input type='text' name="id" id='username' placeholder='Username'>
        <input type='password' name="pwd" id='password' placeholder='Password'>
        <input type="button" value="로그인" class="btn"  onClick="login()"/>
		<input type="button" value="회원가입" class="btn" onClick="eventClick('joinPageform','joinPage','post')"/>
		<input type="button" value="메인으로" style="width:90pt;height:30pt;" class="btn" size="10px" onClick="eventClick('homeform','home','post')"/>
		<input type="button" value="아이디 찾기" style="width:90pt;height:30pt;" class="btn" size="10px" onClick="eventClick('idFindPageform','idFindPage','post')"/>
		<input type="button" value="비밀번호 찾기" style="width:90pt;height:30pt;" class="btn" onClick="eventClick('idPwdPageform','idPwdPage','post')"/>
</div>
	</div>
	<input type="hidden" name="identity" value="${identity }" />
</body>
</html>