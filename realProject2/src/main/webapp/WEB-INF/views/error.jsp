<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>에러페이지</title>
<style>
body {
	margin: 0;
	padding: 0;
	background: #CCC;
}

.container {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	min-width: 500px;
	margin: 0px;
}

.head {
	width: 100%;
	text-align: center;
	font-family: 'Lato', sans-serif;
	padding-bottom: 20px;
	font-weight: normal;
	color: #222;
	letter-spacing: 1px
}

.component {
	float: left;
	margin: 10px;
	position: relative;
}

.PC {
	height: 100px;
	width: 40%;
	border: 5px solid #333;
	border-radius: 5px;
	position: relative;
}

.PC:before {
	content: '';
	width: 5px;
	height: 20px;
	border: 5px solid #333;
	position: relative;
	left: 50%;
	transform: translate(-50%);
	top: 100px;
	display: block;
	background: #333;
}

.PC:after {
	content: '';
	width: 40px;
	height: 0px;
	border: 4px solid #333;
	position: relative;
	left: 50%;
	transform: translate(-50%);
	top: 95px;
	display: block;
	border-radius: 3px;
}

.PC .flare {
	width: 50px;
	height: 1px;
	background: #AAA;
	position: absolute;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%) rotate(-45deg);
}

.PC .flare:after, .PC .flare:before {
	content: '';
	width: 30px;
	height: 1px;
	background: inherit;
	position: absolute;
	top: -5px;
	left: 50%;
	transform: translate(-50%, -50%);
}

.PC .flare:after {
	top: 6px;
}

.signals {
	width: 30%;
	height: 100px;
}

.signals .dot {
	width: 10px;
	height: 10px;
	background: #444;
	display: inline-block;
	border-radius: 50%;
	position: absolute;
	left: 30%;
	top: 50%;
	opacity: 0;
	transform: translate(-50%, -50%);
	animation: blink 0.3s ease-in infinite alternate;
}

.signals .first {
	margin-left: 0px;
}

.signals .second {
	margin-left: 30px;
	animation-delay: 0.1s;
}

.signals .third {
	margin-left: 60px;
	animation-delay: 0.2s;
}

.server {
	width: 12%;
	height: 130px;
	border: 5px solid #333;
	border-radius: 5px;
	position: relative
}

.server .slot {
	display: block;
	background: #444;
	width: 90%;
	height: 8%;
	margin: 10% 5%;
	position: relative;
	top: 55%;
	border-radius: 2px;
}

.server .button {
	width: 6px;
	height: 6px;
	background: #444;
	display: inline-block;
	border-radius: 50%;
	position: absolute;
	right: 20%;
	bottom: 5%;
}

.server .button:last-of-type {
	height: 8px;
	width: 8px;
	right: 5%;
}

@
-webkit-keyframes blink {from { opacity:0
	
}

to {
	opacity: 1
}

}
@media only screen and (max-width: 500px) {
	.container {
		width: 100%;
		min-width: 100%
	}
	.component {
		display: block;
		float: left;
		clear: both;
		left: 50%;
		transform: translateX(-50%);
	}
	.signals {
		transform: translateX(-50%) rotate(90deg);
	}
	.server {
		width: 17%;
	}
}
</style>
</head>
<body>
	<div class="container">
		<h3 class="head">Connecting to server</h3>
		<div class="component PC">
			<div class="flare"></div>
		</div>
		<div class="component signals">
			<div class="dot first"></div>
			<div class="dot second"></div>
			<div class="dot third"></div>
		</div>
		<div class="component server">
			<div class="slot"></div>
			<div class="slot"></div>
			<div class="button"></div>
			<div class="button"></div>
		</div>
	</div>
</body>
</html>