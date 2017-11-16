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

<title>공조 || 선생님 나의 정보</title>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
$(document).ready(function() {
	 $("#re").hide();
	/* 복사 */
	$("tbody[name *='tbody']").hide();
	var tableList = $("#tableList");
	tableList.append($("#tbody0").show());
	
	
	 $("#but").click(function(){
	        $("#re").animate({
	            height: 'toggle'
	        });
	    });
});

/*복사  */
function pageNumber(value) {
	$("tbody[name *='tbody']").hide();
	var tableList = $("#tableList");
	tableList.append($("#tbody" + value).show());
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
//메뉴선택
function menu(ivalue){
	createinput("hidden", "caCode", ivalue);
	
	var caCode = document.getElementsByName("caCode")[0];
	
	createForm("menuform","tcmenu","post");
	

	var form = document.getElementsByName("menuform")[0];
	form.appendChild(caCode);
	
	form.submit();
	
}

function eventClick(formname,formaction,formmethod){	

	var id = document.getElementsByName("id")[0];
	var name = document.getElementsByName("name")[0];
	var email = document.getElementsByName("email")[0];
	var phone = document.getElementsByName("phone")[0];
	
	createForm(formname,formaction,formmethod);
	
	var form = document.getElementsByName(formname)[0];
	
	form.appendChild(id);
	form.appendChild(name);
	form.appendChild(email);
	form.appendChild(phone);
	
	form.submit();
	
}

function pwdUP(formname,formaction,formmethod){	
	
	var id = document.getElementsByName("id")[0];

	createForm(formname,formaction,formmethod);
	
	var form = document.getElementsByName(formname)[0];
	
	form.appendChild(id);
	
	form.submit();
	
}

</script>
<body onLoad="${message}">
	<div id="xy"></div>

	<br />
	<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	<br />
	<br />
	<br />
	<input type="button" value="메인으로" class="btn" onClick="menu('15')" />
	&nbsp;&nbsp;
	<input type="button" value="로그아웃" class="btn" onClick="menu('14')" />
	<br />
	<h2>
		<b>내정보</b>
	</h2>
	<br />


	<div class="joinInput" style="height: 250px">${content }</div>

	<br />
	<br />


	<input type="button" value="비밀번호 수정" class="btn"
		onClick="pwdUP('teacherInfoPWDUpdatePageform','teacherInfoPWDUpdatePage','post')" />
	<input type="button" value="나의정보 수정" class="btn"
		onClick="eventClick('teacherInfoUpdatePageform','teacherInfoUpdatePage','post')" />
	<input type="button" value="회원탈퇴" class="btn"
		onClick="eventClick('Withdrawalform','WithdrawalTeacherPage','post')" />

	<button class="btn" id="but">출결</button>
	
	<div id="re">
		</br> </br> </br>${attendance }${content2 }
	</div>



</body>
</html>