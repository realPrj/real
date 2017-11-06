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
<title>공조 || 회원탈퇴 비밀번호 확인</title>

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

// 비밀번호 확인하기
function WithdrawalPage(){	
	
	var pwd = document.getElementsByName("pwd")[0];
	
	alert(pwd.value);
	
	
	createForm("checkPwdform","checkPwd","post");
	
	var form = document.getElementsByName("checkPwdform")[0];
	
	form.appendChild(pwd);

	
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
<body>

	<input type="hidden" name="identity" value="${identity }" />
	<div id="xy"></div>

	<br />
	<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	<br />

	<br />
	<h2>회원탈퇴</h2>
	<br />

	<div>
		<div id="loginInput">

			비밀번호 &nbsp;&nbsp; <input type="text" name="pwd" class="box"
				placeholder="비밀번호 입력해주세여"><br /> <br />

		</div>

		<br /> <br /> <input type="button" value="회원탈퇴"
			class="btn"onClick="WithdrawalPage()" />
			</div>
</body>
</html>