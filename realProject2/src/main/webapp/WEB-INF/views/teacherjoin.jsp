<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>공조</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    
    <style>
		body		{background-color : white;
				 color : #353535;
				 text-align : center;
				 font-size : 15px;
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

//회원가입
function join(){	
	
	var id = document.getElementsByName("id")[0];
	var pwd = document.getElementsByName("pwd")[0];
	var name = document.getElementsByName("name")[0];
	var email = document.getElementsByName("email")[0];
	var phone = document.getElementsByName("phone")[0];
	var identity = document.getElementsByName("identity")[0];
	
	createForm("joinform","join","post");
	
	var form = document.getElementsByName("joinform")[0];
	
	form.appendChild(id);
	form.appendChild(pwd);
	form.appendChild(name);
	form.appendChild(email);
	form.appendChild(phone);
	form.appendChild(identity);
	
	form.submit();
	
}

/* // 아이디 중복 확인
function idRedundancyCheck(){
	
	var id = document.getElementsByName("id")[0];
	var identity = document.getElementsByName("identity")[0];
	
	createForm("idRedundancyCheckform","idRedundancyCheck","post");
	
	var form = document.getElementsByName("idRedundancyCheckform")[0];
	
	form.appendChild(id);
	form.appendChild(identity);
	form.submit();
	
} */

function idRedundancyCheck(){
	var id = $("[name=id]").val();
	$.ajax({
		type : "post",
		url : "idRedundancyCheck",
		data : {id : id},
		success : function(result){
			if(result == '"1"'){
				$('#idCheck').html("사용할 수 있는 아이디입니다.");
			}else{
				$('#idCheck').html("사용할 수 없는 아이디입니다.");
			}
		}
		
	});
}

</script>
</head>
<body onLoad="${message}">
<div id="xy"></div>
	
	<br/>
	<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	<br/>
	
	<br/>
	<h2> 회원가입  </h2>
	<br/>
	<div>
		<div class= "joinInput">
		

		
		아이디　 　　&nbsp;&nbsp; <input type="text" name="id" value="${id }"  class="box" placeholder="아이디 입력" >
		<input type="button" value="아이디 중복검사" class="btn" onClick="idRedundancyCheck()" style="float:right;"/>
		<br/>
		<div id="idCheck" style="color:red; margin-left:100px"></div>
		<br/>
		비밀번호 　　 &nbsp;&nbsp;<input type="password" name="pwd" class="box" placeholder="비밀번호"><br/>
		<br/>
			<div class= "sub3">
			비밀번호 확인 &nbsp;&nbsp;<input type="password" name="pwd" class="box" placeholder="비밀번호"><br/>
			<!-- <input type="button" value="비밀번호 확인" class="btn" onClick="pwCheck()" style="float:right;"/> -->
			</div>
		<br/>
		
		
		이름　　 　　 &nbsp;&nbsp;<input type="text"  name="name" value="${name }" class="box" placeholder="이름"><br/>
		<br/>
		이메일　 　　 &nbsp;&nbsp;<input type="text" name="email" value="${email }" class="box" placeholder="예)*****@naver.com"><br/>
	
		<br/>
			<div class= "sub3">
			연락처　 　　 &nbsp;&nbsp;<input type="text"  name="phone" value="${phone }" class="box" placeholder="예)010-0000-0000"><br/>
			</div>
		<br/>
		
		
		</div>
		<br>
		<input type="hidden" name="identity" value="${identity }" />
		<input type="button" value="회원가입" onClick="join()" />
	</div>
	<!--  
<table>
<tr>
<td>아이디</td>
<td><input type="text" name="id" value="${id }" placeholder="아이디" /></td>
<td><input type="button" value="아이디 중복 체크" onClick="idRedundancyCheck()" /></td>
</tr>
<tr>
<td>비밀번호</td>
<td><input type="text" name="pwd" placeholder="비밀번호" /></td>
</tr>
<tr>
<td>비밀번호 재입력</td>
<td><input type="text" name="pwd" placeholder="비밀번호 재입력"  /></td>
</tr>
<tr>
<td>이름</td>
<td><input type="text" name="name" value="${name }" placeholder="이름"  /></td>
</tr>
<tr>
<td>E-mail</td>
<td><input type="text" name="email" value="${email }" placeholder="예)*****@naver.com"  /></td>
</tr>
<tr>
<td>전화번호</td>
<td><input type="text" name="phone" value="${phone }" placeholder="예)010-0000-0000"  /></td>
</tr>
<tr>
	<td><input type="button" value="회원가입" onClick="join()" /></td>
</tr>
</table>
<input type="hidden" name="identity" value="${identity }" />-->
</body>
</html>