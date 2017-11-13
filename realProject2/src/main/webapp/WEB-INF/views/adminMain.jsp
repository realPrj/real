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

<title>공조 || 관리자 메인</title>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
</script>

<script>
	//form 생성
	function createForm(formname, formaction, formmethod) {

		var form = document.createElement("form");

		form.name = formname;
		form.action = formaction;
		form.method = formmethod;

		document.body.appendChild(form);

	}

	//input 생성
	function createinput(itype, iname, ivalue) {
		var input = document.createElement("input");
		input.type = itype;
		input.name = iname;
		input.value = ivalue;

		document.body.appendChild(input);
	}

	function eventClick(formname, formaction, formmethod) {

		createForm(formname, formaction, formmethod);

		var form = document.getElementsByName(formname)[0];

		form.submit();

	}
	//form 생성
	function createForm1(formname, formaction, ta) {

		var form = document.createElement("form");
		form.target = ta;
		form.name = formname;
		form.action = formaction;

		document.body.appendChild(form);

	}
	function cxt(studentCode) {
		createinput("hidden", "studentCode", studentCode);

		var studentCode = document.getElementsByName("studentCode")[0];

		createForm1("studentCXTForm", "studentCXT", "POP");

		var form = document.getElementsByName("studentCXTForm")[0];
		window.open('', 'POP',
				"width=330px, height=370px, resizable = no, scrollbars = no");
		form.appendChild(studentCode);

		form.submit();
		$("input[name = studentCode]").remove();
	}
	function teacherCxt(id) {

		createinput("hidden", "id", id);

		var id = document.getElementsByName("id")[0];

		createForm1("teacherCxtForm", "teacherCxt", "POP");

		var form = document.getElementsByName("teacherCxtForm")[0];
		window.open('', 'POP',
				"width=330px, height=430px, resizable = no, scrollbars = no");
		form.appendChild(id);

		form.submit();
		$("input[name = id]").remove();
	}
	function emailSend(email) {

		createinput("hidden", "email", email);

		var email = document.getElementsByName("email")[0];

		createForm1("emailForm", "email", "POP");

		var form = document.getElementsByName("emailForm")[0];
		window.open('', 'POP',
				"width=330px, height=550px, resizable = no, scrollbars = no");
		form.appendChild(email);

		form.submit();
		$("input[name = email]").remove();
	}
</script>
<style>
body {
	background-color: white;
	color: #353535;
	text-align: center;
	font-size: 15px;
}

.menu a {
	cursor: pointer;
}

.menu .hide {
	display: none;
}

button {
	background-color: transparent;
	border: 1px solid transparent;
}
</style>
</head>
<body onLoad="${message}">

	<br />
	<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	<br />
	<div class="row">
		<div class="col-md-6">
			<div class="card">
				<div class="header">

					<h4 class="title">
						<b>모든 선생님</b>&nbsp;&nbsp;총&nbsp;${teachercount }명
					</h4>
					<br /> <br /> ${ allTeacher}
				</div>
				<div class="content">

					<div id="chartActivity" class="ct-chart"></div>


				</div>
			</div>
		</div>

		<div class="col-md-6">
			<div class="card ">
				<div class="header">
					<h4 class="title">
						<b>모든 학생</b>&nbsp;&nbsp; 총&nbsp;${studentcount }명
					</h4>

					<br /> <br /> ${allstudent }
				</div>
				<div class="content">
					<div id="chartActivity" class="ct-chart"></div>

				</div>
			</div>
		</div>
	</div>



	&copy;
	<script>
		document.write(new Date().getFullYear())
	</script>
	, made with
	<i class="fa fa-heart heart"></i> by
	<a href="first_join.html">Creative 공조</a>



	<br />
	<br />
</body>
</html>