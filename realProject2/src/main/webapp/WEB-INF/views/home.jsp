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

<title>공조 || Home</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Paper Dashboard core CSS    -->
    <link href="assets/css/paper-dashboard.css" rel="stylesheet"/>


    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="assets/css/demo.css" rel="stylesheet" />
    <link rel="stylesheet" href="assets/css/login.css" type="text/css">


    <!--  Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="assets/css/themify-icons.css" rel="stylesheet">



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

	// 신분선택
	function identity(ivalue) {

		createinput("hidden", "identity", ivalue);

		var identity = document.getElementsByName("identity")[0];

		createForm("IdentificationCheckform", "IdentificationCheck", "post");

		var form = document.getElementsByName("IdentificationCheckform")[0];
		form.appendChild(identity);

		form.submit();

	}
	
</script>
<style>
		body	{background-color : white;
				 color : #353535;
				 text-align : center;
				 font-size : 15px;
				 }
		
		.btn	{width : 300px;
				 height : 80px;
				 background-color : white;
				 font-size : 20px;
				 color : gray;}
		
	</style>
</head>
<body onLoad="${message}">
	<br/>
	<img src="assets/img/gong_logo.png" alt="공조" width="300*250">
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	<br/>
	
	<div>
		<input type="button" value="관리자" class="btn" onClick="identity('0')" /> 
		  <br/>
		 <br/>
		<input type="button" value="선생님"class="btn" onClick="identity('1')" />
		 <br/>
		 <br/>
		 
		<input type="button" value="학생" class="btn" onClick="identity('2')" />
		 	
		
		 <br/>
		 <br/>
		 <br/>
		 <br/>
		 <br/>
		 <br/>
		 <br/>
		 <br/>
		 <br/>
		 <br/>
		 <br/>
		 
		 &copy; <script>document.write(new Date().getFullYear())</script>, made with <i class="fa fa-heart heart"></i> by <a href="first_join.html">Creative 공조</a>
		
		
		
	</div>
	
	
	
</body>
</html>