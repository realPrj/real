<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공조 || 회원탈퇴 비밀번호 확인</title>
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

// 비밀번호 확인하기
function WithdrawalPage(){	
	
	var pwd = document.getElementsByName("pwd")[0];
	
	alert(pwd.value);
	
	
	createForm("checkPwdform","checkPwd","post");
	
	var form = document.getElementsByName("checkPwdform")[0];
	
	form.appendChild(pwd);

	
	form.submit();
	
}

</script>
<body>
<h1>공조</h1>
<input type="hidden" name="identity" value="${identity }" />
<table>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="pwd" placeholder="비밀번호 입력해주세여" /></td>
	</tr>
	<tr>
		<td><input type="button" value="회원탈퇴" onClick="WithdrawalPage()" /></td>
	</tr>
</table>
</body>
</html>