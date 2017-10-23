<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공조 || 선생님 나의 정보</title>
</head>
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
<input type="button" value="로그아웃" onClick="" />
<input type="button" value="메인으로" onClick="" />
${content }
<input type="button" value="비밀번호 수정" onClick="pwdUP('teacherInfoPWDUpdatePageform','teacherInfoPWDUpdatePage','post')" />
<input type="button" value="나의정보 수정" onClick="eventClick('teacherInfoUpdatePageform','teacherInfoUpdatePage','post')" />
<input type="button" value="회원탈퇴" onClick="" />
</body>
</html>