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

<title>공조 || 학생 나의정보 수정</title>

</head>
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
<style>
body {
	background-color: white;
	color: #353535;
	text-align: center;
	font-size: 15px;
}
</style>
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

	var studentCode = document.getElementsByName("studentCode")[0];
	var name = document.getElementsByName("name")[0];
	var email = document.getElementsByName("email")[0];
	var phone = document.getElementsByName("phone")[0];
	
	createForm(formname,formaction,formmethod);
	
	var form = document.getElementsByName(formname)[0];
	
	form.appendChild(studentCode);
	form.appendChild(name);
	form.appendChild(email);
	form.appendChild(phone);
	
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
//메뉴선택
function menu(ivalue) {

   createinput("hidden", "caCode", ivalue);

   var caCode = document.getElementsByName("caCode")[0];

   createForm("menuform", "stmenu", "post");

   var form = document.getElementsByName("menuform")[0];
   form.appendChild(caCode);
   
   form.submit();

}

</script>
<body>
<input type="hidden" name="studentCode" value="${studentCode }" />
	<div id="xy"></div>

	<br />
	<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	<br />
	<br />
	<br />
	<input type="button" value="메인으로" class="btn" onClick="menu('15')" />
	&nbsp;&nbsp;
	<input type="button" value="로그아웃" class="btn"
		onClick="menu('14')" />
	<br />
	<h2>
		<b>내정보</b>
	</h2>
	<br />
	<div class="joinInput" style="height: 250px; width:450px">
		이름 &nbsp;&nbsp; <input type="text" name="name" class="box"
			value="${name }" placeholder=""> <br /> <br /> 이메일
		&nbsp;&nbsp;<input type="text" name="email" class="box"
			value="${email }" placeholder=""><br /> <br /> 핸드폰
		&nbsp;&nbsp;<input type="text" name="phone" class="box"
			value="${phone }" placeholder=""><br /> <br />
	</div>

	<div></div>

	<br />
	<br />


	<input type="button" value="나의정보 수정" class="btn"
		onClick="eventClick('studentInfoUpdateform','studentInfoUpdate','post')" />
	<input type="button" value="취소" class="btn" onClick="menu('15')" />
	<input type="hidden" name="id" value="${id }" />
</body>
</html>