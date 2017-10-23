<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공조 || 선생님 나의 정보 수정</title>
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

	var name = document.getElementsByName("name")[0];
	var email = document.getElementsByName("email")[0];
	var phone = document.getElementsByName("phone")[0];
	
	createForm(formname,formaction,formmethod);
	
	var form = document.getElementsByName(formname)[0];
	
	form.appendChild(name);
	form.appendChild(email);
	form.appendChild(phone);
	
	form.submit();
	
}

</script>
<body>
<input type="button" value="로그아웃" onClick="" />
<input type="button" value="메인으로" onClick="" />
<table>
	<tr>
		<td>이름<input type="text" name="name" value="${name }" /></td>
	</tr>
	<tr>
		<td>이메일<input type="text" name="email" value="${email }" /></td>
	</tr>
	<tr>
		<td>핸드폰<input type="text" name="phone" value="${email }" /></td>
	</tr>
</table>
<input type="button" value="나의정보 수정" onClick="eventClick('teacherInfoUpdateform','teacherInfoUpdate','post')" />
<input type="button" value="취소" onClick="" />
</body>
</html>