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

<title>공조</title>

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
	function createForm(formname, formaction, formmethod) {

		var form = document.createElement("form");

		form.name = formname;
		form.action = formaction;
		form.method = formmethod;

		document.body.appendChild(form);

	}

	// 회원가입
	function join() {

		var id = document.getElementsByName("id")[0];
		var pwd = document.getElementsByName("pwd")[0];
		var name = document.getElementsByName("name")[0];
		var email = document.getElementsByName("email")[0];
		var phone = document.getElementsByName("phone")[0];
		var stGrade = document.getElementsByName("stGrade")[0];
		var stclass = document.getElementsByName("stclass")[0];
		var stNumber = document.getElementsByName("stNumber")[0];
		var identity = document.getElementsByName("identity")[0];

		createForm("joinform", "join", "post");

		var form = document.getElementsByName("joinform")[0];

		form.appendChild(id);
		form.appendChild(pwd);
		form.appendChild(name);
		form.appendChild(email);
		form.appendChild(phone);
		form.appendChild(stGrade);
		form.appendChild(stclass);
		form.appendChild(stNumber);
		form.appendChild(identity);

		form.submit();

	}

	//아이디 중복 확인
	function idRedundancyCheck() {

		var id = document.getElementsByName("id")[0];
		var identity = document.getElementsByName("identity")[0];

		createForm("idRedundancyCheckform", "idRedundancyCheck", "post");

		var form = document.getElementsByName("idRedundancyCheckform")[0];

		form.appendChild(id);
		form.appendChild(identity);
		form.submit();

	}
</script>
<body onLoad="${message}">
	<div id="xy"></div>

	<br />
	<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	<br />
	
	<br/>
	<h2> 회원가입  </h2>
	<br/>
	
	
	<div>
		<div class= "joinInput">
		
	<!-- 	<div class="sort-group" style=" text-align : center;">
		<input type="radio" name="sort" value="회원" checked>회원
		 &nbsp;&nbsp;&nbsp;&nbsp;
		<input type="radio" name="sort" value="샵">샵
		</div>
		<br/> -->
		
		
		아이디　 　　&nbsp;&nbsp; <input type="text" name="id" class="box" value="${id }"  placeholder="아이디 입력해주세여" >
		<input type="button" value="아이디 중복검사" class="btn" onClick="idRedundancyCheck()" style="float:right;"/>
		<br/>
		<br/>
		비밀번호 　　 &nbsp;&nbsp;<input type="password" name="pwd" class="box" placeholder="비밀번호"><br/>
		<br/>
			<div class= "sub3">
			비밀번호 확인 &nbsp;&nbsp;<input type="password" name="pwd" class="box" placeholder="재입력"><br/>
			<!-- <input type="button" value="비밀번호 확인" class="btn" onClick="pwCheck()" style="float:right;"/> -->
			</div>
		<br/>
		
		
		이름　　 　　 &nbsp;&nbsp;<input type="text"  name="name" value="${name }" class="box" placeholder="이름입력해주세여"><br/>
		<br/>
		이메일　 　　 &nbsp;&nbsp;<input type="text"name="email" value="${email }" class="box" placeholder="이메일 입력해주세여"><br/>
		<!-- <input type="button" value="인증메일 보내기" class="btn" onClick="emailCheck()"style="float:right;"/> -->
		<br/>
			<div class= "sub3">
			연락처　 　　 &nbsp;&nbsp;<input type="text" name="phone" value="${phone }" class="box" placeholder="핸드폰 입력해주세여"><br/>
			</div>
		<br/>
		
		학년　  <select id="grade"  name="stGrade" style="width:100px; height:35px; border:#F5F5F5;">
					<option value="학년" label="　">학년</option>
					<option value="학년" label="1학년">1학년</option>
					<option value="학년" label="2학년">2학년</option>
					<option value="학년" label="3학년">3학년</option>
					<option value="학년" label="4학년">4학년</option>
					<option value="학년" label="5학년">5학년</option>
					<option value="학년" label="6학년">6학년</option>
			</select>
		　　반　　  <select id="class" name="stclass" style="width:100px; height:35px; border:#F5F5F5;">
					<option value="반" label="　">반</option>
					<option value="반" label="1반">1반</option>
					<option value="반" label="2반">2반</option>
					<option value="반" label="3반">3반</option>
					<option value="반" label="4반">4반</option>
					<option value="반" label="5반">5반</option>
					<option value="반" label="6반">6반</option>
					<option value="반" label="7반">7반</option>
					<option value="반" label="8반">8반</option>
					<option value="반" label="9반">9반</option>
					<option value="반" label="10반">10반</option>
					<option value="반" label="11반">11반</option>
					<option value="반" label="12반">12반</option>
			</select>
		　　번호　  <select id="number" name="stNumber" style="width:100px; height:35px; border:#F5F5F5;">
					<option value="번호" label="　">번호</option>
					<option value="번호" label="1">1</option>
					<option value="번호" label="2">2</option>
					<option value="번호" label="3">3</option>
					<option value="번호" label="4">4</option>
					<option value="번호" label="5">5</option>
					<option value="번호" label="6">6</option>
					<option value="번호" label="7">7</option>
					<option value="번호" label="8">8</option>
					<option value="번호" label="9">9</option>
					<option value="번호" label="10">10</option>
					<option value="번호" label="11">11</option>
					<option value="번호" label="12">12</option>
					<option value="번호" label="13">12</option>
					<option value="번호" label="14">12</option>
					<option value="번호" label="15">12</option>
					<option value="번호" label="16">12</option>
					
				</select>
		
	<input type="hidden" name="identity" value="${identity }" />
		</div>
		<br>
		<input type="button" value="회원가입" class="btn" onClick="join()" />
	</div>
	
	<!--
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id" value="${id }"
				placeholder="아이디" /></td>
			<td><input type="button" value="아이디 중복 체크"
				onClick="idRedundancyCheck()" /></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="pwd" placeholder="비밀번호" /></td>
		</tr>
		<tr>
			<td>비밀번호 재입력</td>
			<td><input type="text" name="pwd" placeholder="비밀번호 재입력" /></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" value="${name }"
				placeholder="이름" /></td>
		</tr>
		<tr>
			<td>E-mail</td>
			<td><input type="text" name="email" value="${email }"
				placeholder="예)*****@naver.com" /></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="phone" value="${phone }"
				placeholder="예)010-0000-0000" /></td>
		</tr>
		<tr>
			<td>학년/반/번호</td>
			<td>학년</td>
			<td><select name="stGrade">
					<option value="01">1</option>
					<option value="02">2</option>
					<option value="03">3</option>
					<option value="04">4</option>
					<option value="05">5</option>
					<option value="06">6</option>
			</select></td>
			<td>반</td>
			<td><select name="stclass">
					<option value="01">1</option>
					<option value="02">2</option>
					<option value="03">3</option>
					<option value="04">4</option>
					<option value="05">5</option>
					<option value="06">6</option>
					<option value="07">7</option>
					<option value="08">8</option>
					<option value="09">9</option>
					<option value="10">10</option>
			</select></td>
			<td>번호</td>
			<td><select name="stNumber">
					<option value="01">1</option>
					<option value="02">2</option>
					<option value="03">3</option>
					<option value="04">4</option>
					<option value="05">5</option>
					<option value="06">6</option>
					<option value="07">7</option>
					<option value="08">8</option>
					<option value="09">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
					<option value="13">13</option>
					<option value="14">14</option>
					<option value="15">15</option>
					<option value="16">16</option>
					<option value="17">17</option>
					<option value="18">18</option>
					<option value="19">19</option>
					<option value="20">20</option>
			</select></td>
		</tr>
		<tr>
			<td><input type="button" value="회원가입" onClick="join()" /></td>
		</tr>
	</table>
	<input type="hidden" name="identity" value="${identity }" />-->
</body>
</html>