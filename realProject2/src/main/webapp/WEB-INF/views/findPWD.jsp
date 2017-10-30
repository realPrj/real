<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공조 || 비밀번호 찾기</title>
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

	var identity = document.getElementsByName("identity")[0];
	
	createForm(formname,formaction,formmethod);
	
	var form = document.getElementsByName(formname)[0];
	
	form.appendChild(identity);
	
	form.submit();
	
}
function findPwd(){
	 
	   createForm("findPwdForm", "findPwd", "post");
		
	   var form = document.getElementsByName("findPwdForm")[0];
	   var identity = document.getElementsByName("identity")[0];
	   var email = document.getElementsByName("email")[0];
	   var id = document.getElementsByName("id")[0];
		
	   form.appendChild(identity);
	   form.appendChild(email);
	   form.appendChild(id);


	   form.submit();
}

</script>
<body onLoad="${message}">
<h1>공조</h1>
<form>
<table>
<tr>
<td>아이디</td>
<td><input type="text" name="id"placeholder="아이디" /></td>
</tr>
<tr>
<td>이메일</td>
<td><input type="text" name="email" placeholder="비밀번호" /></td>
</tr>
<tr>
<td><input type="button" value="비밀번호 찾기" onClick="findPwd('id','email')" /></td>
</tr>
${message}
<tr>
<td><input type="button" value="회원가입" onClick="eventClick('joinPageform','joinPage','post')" /></td>
</tr>
</table>
</form>
<input type="hidden" name="identity" value="${identity }" />${identity }
</body>
</html>