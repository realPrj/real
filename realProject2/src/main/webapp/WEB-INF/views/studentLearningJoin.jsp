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

<title>공조 || 학습참여</title>
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

<script>
//form 생성
function createForm(formname,formaction,formmethod){

	var form = document.createElement("form");

	form.name = formname;
	form.action = formaction;
	form.method = formmethod;

	document.body.appendChild(form);

}

// input 생성
function createinput(itype, iname, ivalue){
	var input = document.createElement("input");
	input.type = itype;
	input.name = iname;
	input.value = ivalue;

	document.body.appendChild(input);
}

// 학습방 조회
function inquiry(formname,formaction,formmethod){	
	
	var id = document.getElementsByName("id")[0];

	createForm(formname,formaction,formmethod);
	
	var form = document.getElementsByName(formname)[0];
	
	form.appendChild(id);
	
	form.submit();
	
}

// 학습방 참여
function learningJoin(ivalue){
	
	createinput("hidden", "roomCode", ivalue)
	
	var roomCode = document.getElementsByName("roomCode")[0];

	createForm("learningJoinform","learningJoin","post");
	
	var form = document.getElementsByName("learningJoinform")[0];
	
	form.appendChild(roomCode);
	
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


<div id="xy"></div>

	<br />
	<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	<br />

	<!-- 로그아웃,나의프로필 버튼 -->
	<br />
	<br />
	<button type="button" class="btn">로그아웃</button>
	<button type="button" class="btn">메인으로</button>
	<br />
	<br />


	<br />
	<br />
	<!-- 과목참여 -->

			<div class="container">
				<h3 class="container">학습방 참여</h3>
				<br /> <br />
				
				<p>담임 선생님 아이디를 입력하세요</p>
				<form>
				<!-- 담임아이디검색창 -->
					<div class="input-group input-group-lg">
						<input type="text" style="background:#FAE0D4; margin-left:45%; width:600px; color:#6B66FF; text-align:center;" class="form-control" placeholder="Search"  name="id" value="${id }">
						<div class="input-group-btn">
						</div>
					</div>
					<button type="button" class="btn"  onClick="inquiry('learningJoinform','learningJoin','post')" >조회</button>
				</form>
				<br>
			
	
		</div>



<div id="content">
${content }
</div>

</body>
</html>