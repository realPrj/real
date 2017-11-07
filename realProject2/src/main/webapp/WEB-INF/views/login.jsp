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
		<br/>
	<h2> 로그인 </h2>
	<br/>
	
	<div>
		 <div id= "loginInput">
		 
		 아이디　 &nbsp;&nbsp;<input type="text" name="id"value="${id }" class="box" placeholder="아이디 입력" ><br/>
		 <br/>
		 비밀번호 &nbsp;&nbsp;<input type="password" name="pwd" class="box" placeholder="비밀번호 입력해주세여"><br/>
		 <br/>
		 
		 <div id= "sub" style="padding-left:50px;">
			 <input type="button" value="아이디 찾기"
				onClick="eventClick('idFindPageform','idFindPage','post')" />
				 <div id= "sub2" style="float:right; padding-right:50px;">
			 	 	<input type="button" value="비밀번호 찾기"
				onClick="eventClick('idPwdPageform','idPwdPage','post')" />
				 </div>
			 </div>
		 </div>
	</div>
	
		<br/>
		<br/>
		<input type="button" value="로그인" class="btn"  onClick="login()"/>
		&nbsp;&nbsp;
		<input type="button" value="회원가입" class="btn" onClick="eventClick('joinPageform','joinPage','post')"/>
	
	<input type="hidden" name="identity" value="${identity }" />${identity }
</body>
</html>