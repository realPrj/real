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

<title>공조 || Home</title>
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
body {
	background-color: white;
	color: #353535;
	text-align: center;
	font-size: 15px;
}

.btn {
	width: 300px;
	height: 80px;
	background-color: white;
	font-size: 20px;
	color: gray;
}
</style>
<style>
body {
	background-color: white;
	margin: 20px;
	font-family: Arial, Tahoma;
	font-size: 20px;
	color: #666666;
	text-align: pull-left;
}

p {
	color: #FFFFFF;
}

/*-=-=-=-=-=-=-=-=-=-*/
/* Column Grids */
/*-=-=-=-=-=-=-=-=-= */
.col_half {
	width: 49%;
}

.col_third {
	width: 32%;
}

.col_fourth {
	width: 23.5%;
}

.col_fifth {
	width: 18.4%;
}

.col_sixth {
	width: 15%;
}

.col_three_fourth {
	width: 74.5%;
}

.col_twothird {
	width: 66%;
}

.col_half, .col_third, .col_twothird, .col_fourth, .col_three_fourth,
	.col_fifth {
	position: relative;
	display: inline;
	display: inline-block;
	float: left;
	margin-right: 2%;
	margin-bottom: 20px;
}

.end {
	margin-right: 0 !important;
}

/*-=-=-=-=-=-=-=-=-=-=- */
/* Flip Panel */
/*-=-=-=-=-=-=-=-=-=-=- */

/* .wrapper{ width: 980px; margin: 0 auto;  background-color: #bdd3de; hoverflow: hidden;}
 */
.panel {
	margin: 0 auto;
	height: 130px;
	position: relative;
	-webkit-perspective: 600px;
	-moz-perspective: 600px;
	cursor:pointer
}

.panel .front, .panel .back {
	text-align: center;
}

.panel .front {
	height: inherit;
	position: absolute;
	top: 0;
	z-index: 900;
	text-align: center;
	-webkit-transform: rotateX(0deg) rotateY(0deg);
	-moz-transform: rotateX(0deg) rotateY(0deg);
	-webkit-transform-style: preserve-3d;
	-moz-transform-style: preserve-3d;
	-webkit-backface-visibility: hidden;
	-moz-backface-visibility: hidden;
	-webkit-transition: all .4s ease-in-out;
	-moz-transition: all .4s ease-in-out;
	-ms-transition: all .4s ease-in-out;
	-o-transition: all .4s ease-in-out;
	transition: all .4s ease-in-out;
}

.panel .back {
	height: inherit;
	position: absolute;
	top: 0;
	z-index: 1000;
	-webkit-transform: rotateY(-180deg);
	-moz-transform: rotateY(-180deg);
	-webkit-transform-style: preserve-3d;
	-moz-transform-style: preserve-3d;
	-webkit-backface-visibility: hidden;
	-moz-backface-visibility: hidden;
	-webkit-transition: all .4s ease-in-out;
	-moz-transition: all .4s ease-in-out;
	-ms-transition: all .4s ease-in-out;
	-o-transition: all .4s ease-in-out;
	transition: all .4s ease-in-out;
}

.panel.flip .front {
	z-index: 900;
	-webkit-transform: rotateY(180deg);
	-moz-transform: rotateY(180deg);
}

.panel.flip .back {
	z-index: 1000;
	-webkit-transform: rotateX(0deg) rotateY(0deg);
	-moz-transform: rotateX(0deg) rotateY(0deg);
}

.box1 {
	background-color: #B0E7FB;
	width: 350px;
	height: 150px;
	margin: 0 auto;
	padding: 20px;
	border-radius: 10px;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
}

.box2 {
	background-color: #FFE4C4;
	width: 350px;
	height: 150px;
	margin: 0 auto;
	padding: 20px;
	border-radius: 10px;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
}
</style>

<!--   Core JS Files   -->
<script src="assets/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

<!--  Checkbox, Radio & Switch Plugins -->
<script src="assets/js/bootstrap-checkbox-radio.js"></script>

<!--  Charts Plugin -->
<script src="assets/js/chartist.min.js"></script>

<!--  Notifications Plugin    -->
<script src="assets/js/bootstrap-notify.js"></script>

<!--  Google Maps Plugin    -->
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js"></script>

<!-- Paper Dashboard Core javascript and methods for Demo purpose -->
<script src="assets/js/paper-dashboard.js"></script>

<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
<script src="assets/js/demo.js"></script>
<script>
	$(document).ready(function() {
		// set up hover panels
		// although this can be done without JavaScript, we've attached these events
		// because it causes the hover to be triggered when the element is tapped on a touch device
		$('.hover').hover(function() {
			$(this).addClass('flip');
		}, function() {
			$(this).removeClass('flip');
		});
	});
</script>

</head>
<body onLoad="${message}">
	<div id="xy"></div>

	<br />
	<img src="assets/img/gong_logo.png" alt="공조" width="300*250">
	<br />


	<br />
	<br />
	<br />
	<br />
	<br />
	<div class="wrapper">

		<div class="container" style="margin-left: 35%">

			<div class="hover panel">
				<div class="front">
					<div class="box1">
						<br>
						<h5>
							<b>관리자</b>
						</h5>
					</div>
				</div>
				<div class="back">
					<div class="box2">
						<br>
						<h5 onClick="identity('0')">
							<b>관리자로그인</b>
						</h5>
					</div>
				</div>
			</div>
			<br> <br>
			<div class="hover panel">
				<div class="front">
					<div class="box1">
						<br>
						<h5>
							<b>선생님</b>
						</h5>
					</div>
				</div>
				<div class="back">
					<div class="box2">
						<br>
						<h5 onClick="identity('1')">
							<b>선생님로그인</b>
						</h5>
					</div>
				</div>
			</div>
			<br> <br>
			<div class="hover panel">
				<div class="front">
					<div class="box1">
						<br>

						<h5>
							<b>학생</b>
						</h5>
					</div>
				</div>
				<div class="back">
					<div class="box2">
						<br>
						<h5 onClick="identity('2')">
							<b>학생로그인</b>
						</h5>
					</div>
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




</body>
</html>