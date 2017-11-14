<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

<title>공조 || 아이디 찾기</title>

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
function createForm(formname,formaction,formmethod){

	var form = document.createElement("form");

	form.name = formname;
	form.action = formaction;
	form.method = formmethod;

	document.body.appendChild(form);

}


function eventClick(formname,formaction,formmethod){	

	var identity = document.getElementsByName("identity")[0];
	
	createForm(formname,formaction,formmethod);
	
	var form = document.getElementsByName(formname)[0];
	
	form.appendChild(identity);
	
	form.submit();
	
}

// 아이디 찾기
function idfind(){	
	
	var email = document.getElementsByName("email")[0];
	var identity = document.getElementsByName("identity")[0];

	createForm("idFindform","idFind","post");
	
	var form = document.getElementsByName("idFindform")[0];
	
	form.appendChild(email);
	form.appendChild(identity);
	
	form.submit();
	
}

</script>
  <style>
		body		{background-color : white;
				 color : #353535;
				 text-align : center;
				 font-size : 15px;
				 }
		
		
	</style>
	</head>
<body>

<input type="hidden" name="identity" value="${identity }" />
<div id="xy"></div>
	
	<br/>
	<img src="assets/img/gong_logo.png" alt="공조" width="200*150">
	<br/>
	
	<br/>
	<h2> 아이디 찾기 </h2>
	<br/>
	
	<div>
		 <div id= "loginInput">
		 
		 이메일 &nbsp;&nbsp; <input type="text" name="email" class="box" placeholder="예)****@naver.com" ><br/>
		 <br/>
		
		 
		 <div id= "sub" style="padding-left:50px;">
			 <a onClick="eventClick('idPwdPageform','idPwdPage','post')">비밀번호 찾기</a>
			 <div id= "sub2" style="float:right; padding-right:50px;">
			 	 	<a onClick="eventClick('IdentificationCheckform','IdentificationCheck','post')">로그인 하기</a>
				 </div>
			 </div>
		 </div>
	</div>
	
		<br/>
		<br/>
		<input type="button" value="아이디 찾기" onClick="idfind()" />

</body>



</body>
</html>