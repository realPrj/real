<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76"
	href="assets/img/apple-icon.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="assets/img/favicon.png">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Insert title here</title>
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
<style>
body {
	background-color: white;
	color: #353535;
	text-align: center;
	font-size: 15px;
}
</style>
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

function eventClick(formname,formaction,formmethod){	

	var pwd = document.getElementsByName("pwd")[0];
	
	createForm(formname,formaction,formmethod);
	
	var form = document.getElementsByName(formname)[0];

	form.appendChild(pwd);
	
	form.submit();
	
}
//메뉴선택
function menu(ivalue) {

   createinput("hidden", "caCode", ivalue);

   var caCode = document.getElementsByName("caCode")[0];

   createForm("menuform", "stmenu", "post");

   var form = document.getElementsByName("menuform")[0];
   form.appendChild(caCode);
   
   form.submit();

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
<body>
	<div id="xy"></div>

	<br />
	<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	<br />
	<br />
	<br />
	<input type="button" value="메인으로" class="btn" onClick="menu('15')" />
	&nbsp;&nbsp;
	<input type="button" value="로그아웃" class="btn"
		onClick="a onClick=menu('14')" />
	<br />
	<h2>
		<b>비밀번호 재설정</b>
	</h2>
	<br />
	<div id="loginInput">
		비밀번호 &nbsp;&nbsp; <input type="password" name="pwd" class="box"
			placeholder="비밀번호 입력해세요"> <br /> <br /> 재입력
		&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" name="pwd" class="box"
			placeholder="비밀번호 입력해세요"><br /> <br />
	</div>
	<div></div>

	<br />
	<br />


	<input type="button" value="비밀번호 수정" class="btn"
		onClick="eventClick('studentInfoPWDUpdateform','studentInfoPWDUpdate','post')" />
	<input type="button" value="취소" class="btn" onClick="" />
	<input type="hidden" value="${id }" name="id" />


</body>
</html>