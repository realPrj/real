<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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

	var pwd = document.getElementsByName("pwd")[0];
	
	createForm(formname,formaction,formmethod);
	
	var form = document.getElementsByName(formname)[0];

	form.appendChild(pwd);
	
	form.submit();
	
}

</script>
<body>
<input type="button" value="로그아웃" onClick="" />
<input type="button" value="메인으로" onClick="" />
<table>
	<tr>
		<td>비밀번호<input type="text" name="pwd" /></td>
	</tr>
	<tr>
		<td>비밀번호 재입력<input type="text" name="pwd" /></td>
	</tr>
</table>
<input type="button" value="비밀번호 수정" onClick="eventClick('teacherInfoPWDUpdateform','teacherInfoPWDUpdate','post')" />
<input type="button" value="취소" onClick="" />
<input type="hidden" value="${id }" name="id" />
</body>
</html>